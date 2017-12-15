import requests
import json
from django.db import models
from django.contrib.auth.models import User
from django.core.exceptions import ValidationError
from django.dispatch import receiver

EDAMAM_RECIPE_APP_ID = "c8b0c0f5"
EDAMAM_RECIPE_KEY = "05eb202b6be1ef6b5bb68bbaadb7de34"

EDAMAM_NUTRITION_APP_ID = "10d985a5"
EDAMAM_NUTRITION_KEY = "63b7111c9dd4341300de882bdf911f86"


def get_recipe(recipe_identifier):
    recipe_search_string = "https://api.edamam.com/search"
    recipe_uri = "http://www.edamam.com/ontologies/edamam.owl#recipe_" + recipe_identifier

    payload = {
        'r': recipe_uri,
        'app_id': EDAMAM_RECIPE_APP_ID,
        'app_key': EDAMAM_RECIPE_KEY
    }

    r = requests.get(recipe_search_string, params=payload)

    return r

def get_parsed_nutrition(post_content):
    nutrition_post_string = "https://api.edamam.com/api/nutrition-details"
    headers = {'Content-Type': 'application/json'}

    payload = {
        'app_id': EDAMAM_NUTRITION_APP_ID,
        'app_key': EDAMAM_NUTRITION_KEY
    }

    r = requests.post(
        nutrition_post_string,
        params=payload,
        headers=headers,
        data=json.dumps(post_content)
    )

    return r


class CommonFoodInfo(models.Model):
    calories = models.DecimalField(max_digits=7, decimal_places=2, default=0, editable=False)
    fats = models.DecimalField(max_digits=7, decimal_places=2, default=0, editable=False)
    carbs = models.DecimalField(max_digits=7, decimal_places=2, default=0, editable=False)
    protein = models.DecimalField(max_digits=7, decimal_places=2, default=0, editable=False)

    class Meta:
        abstract = True

class FoodItem(CommonFoodInfo):
    FOOD_TYPES = (
        ('Snack', 'Snack'),
        ('Drink', 'Drink'),
        ('Entree', 'Entree'),
        ('Side', 'Side'),
        ('Dessert', 'Dessert')
    )

    name = models.CharField(max_length=100)
    food_type = models.CharField(max_length=7, choices=FOOD_TYPES, default='Entree')
    uri = models.CharField(max_length=100, null=True, blank=True)
    total_servings = models.PositiveSmallIntegerField(blank=True, default=1)
    custom_recipe = models.TextField(null=True, blank=True)

    def save(self, *args, **kwargs):
        if self.uri is not None:
            response = get_recipe(self.uri)
            response_dict = response.json()[0]
            self.total_servings = response_dict.get('yield')

        elif self.custom_recipe is not None:
            post_dict = {
                'title': self.name,
                'ingr': self.custom_recipe.splitlines()
            }

            print(post_dict)

            response = get_parsed_nutrition(post_dict)
            response_dict = response.json()

        else:
            return

        if (response.json()).get('error'):
            print(response.text)
            return

        nutrient_dict = response_dict.get('totalNutrients')

        self.calories = response_dict.get('calories')
        self.fats = nutrient_dict.get('FAT').get('quantity')
        self.carbs = nutrient_dict.get('CHOCDF').get('quantity')
        self.protein = nutrient_dict.get('PROCNT').get('quantity')

        super().save(*args, **kwargs)

    def __str__(self):
        return "{} - calories:{}".format(self.name, self.calories)


class SetCourse(models.Model):
    food_item = models.ForeignKey(FoodItem, on_delete=models.PROTECT)
    servings = models.PositiveSmallIntegerField(default=1, blank=False)

    def __str__(self):
        return "{}: calories: {}".format(
            self.food_item.name,
            self.food_item.calories / self.servings
        )


class StagedMeal(models.Model):
    MEAL_TYPES = (
        ('Breakfast', "Breakfast"),
        ('Lunch', 'Lunch'),
        ('Dinner', 'Dinner'),
        ('Snack', 'Snack')
    )

    WEEKDAY_CHOICES = (
        ("DEF", 'Default'),
        ("MON", 'Monday'),
        ("TUE", 'Tuesday'),
        ("WED", 'Wednesday'),
        ("THU", 'Thursday'),
        ("FRI", 'Friday'),
        ("SAT", 'Saturday'),
        ("SUN", 'Sunday')
    )

    day = models.CharField(
        max_length=3,
        choices=WEEKDAY_CHOICES,
        default="DEF",
    )

    num = models.PositiveSmallIntegerField(null=False)
    meal_type = models.CharField(max_length=9, choices=MEAL_TYPES, default='Snack')
    meal_time = models.TimeField(null=True, blank=True)

    items = models.ManyToManyField(SetCourse)


    def __str__(self):
        return "{} - ({}) {}".format(
            self.day,
            self.num,
            self.meal_type,
        )

class DietProfile(models.Model):
    user = models.ForeignKey(
        'auth.User',
        related_name='dietprofiles',
        on_delete=models.CASCADE
    )
    name = models.CharField(max_length=100)
    suggested_calories = models.DecimalField(max_digits=7, decimal_places=2)
    suggested_fats = models.DecimalField(max_digits=7, decimal_places=2)
    suggested_carbs = models.DecimalField(max_digits=7, decimal_places=2)
    suggested_proteins = models.DecimalField(max_digits=7, decimal_places=2)

    staged_meals = models.ManyToManyField(StagedMeal)

    def __str__(self):
        return self.user.email
