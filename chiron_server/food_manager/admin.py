from django.contrib import admin

# Register your models here.
from .models import FoodItem, StagedMeal, SetCourse, DietProfile, MealPlan

admin.site.register(FoodItem)
admin.site.register(StagedMeal)
admin.site.register(SetCourse)
admin.site.register(MealPlan)
admin.site.register(DietProfile)
