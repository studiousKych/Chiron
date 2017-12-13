import requests
import json
from django.db import models

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
    calories = models.DecimalField(max_digits=7, decimal_places=2, null=True)
    fats = models.DecimalField(max_digits=7, decimal_places=2, null=True)
    carbs = models.DecimalField(max_digits=7, decimal_places=2, null=True)
    protein = models.DecimalField(max_digits=7, decimal_places=2, null=True)

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
    food_type = models.CharField(max_length=7, choices=FOOD_TYPES)
    total_servings = models.PositiveSmallIntegerField()
    uri = models.CharField(max_length=100, null=True, blank=True)
    custom_recipe = models.TextField(null=True, blank=True)

class StagedMeal(CommonFoodInfo):
    MEAL_TYPES = (
        ('Breakfast', "Breakfast"),
        ('Lunch', 'Lunch'),
        ('Dinner', 'Dinner'),
        ('Snack', 'Snack')
    )

    num = models.PositiveSmallIntegerField()
    meal_type = models.CharField(max_length=9, choices=MEAL_TYPES)
    meal_time = models.TimeField(null=True, blank=True)

    items = models.ManyToManyField(FoodItem, through='SetCourse')

class SetCourse(models.Model):
    staged_meal = models.ForeignKey(StagedMeal, on_delete=models.CASCADE)
    food_item = models.ForeignKey(FoodItem, on_delete=models.PROTECT)
    servings = models.PositiveSmallIntegerField(null=True, blank=True)

if __name__ == '__main__':
    # print(get_recipe('57d41c954296c7332ee57e3f6bc6f99a'))

    print(
        get_parsed_nutrition(
            {
                "title": "Basic Bacon and Eggs",
                "yield": 1,
                "ingr": [
                    "1 tablespoon olive oil",
                    "2 eggs over easy",
                    "2 strips bacon"
                ]
            }
        ).json()
    )
