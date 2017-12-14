from django.db import models
from django.dispatch import receiver
from .models import SetCourse, StagedMeal



@receiver([models.signals.post_save, models.signals.post_delete], sender=SetCourse)
def update_food_info(sender, instance, **kwargs):
    staged_meal = instance.staged_meal

    staged_meal.calories = 0
    staged_meal.carbs = 0
    staged_meal.fats = 0
    staged_meal.protein = 0

    for item in staged_meal.items.all():
        staged_meal.calories += (item.calories / item.total_servings) * instance.servings
        staged_meal.carbs += (item.carbs / item.total_servings) * instance.servings
        staged_meal.fats += (item.fats / item.total_servings) * instance.servings
        staged_meal.protein += (item.protein / item.total_servings) * instance.servings

    # staged_meal.calories = (staged_meal.items.all().aggregate(models.Sum('calories'))).get('calories__sum')
    # staged_meal.carbs = (staged_meal.items.all().aggregate(models.Sum('carbs'))).get('carbs__sum')
    # staged_meal.fats = (staged_meal.items.all().aggregate(models.Sum('fats'))).get('fats__sum')
    # staged_meal.protein = (staged_meal.items.all().aggregate(models.Sum('protein'))).get('protein__sum')

    staged_meal.save()
