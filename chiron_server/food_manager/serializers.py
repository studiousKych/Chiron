from rest_framework import serializers
from .models import StagedMeal

class StagedMealSerializer(serializers.ModelSerializer):

    class Meta:
        model = StagedMeal
        read_onlyfields = ('diet_profile', 'day', 'num', 'meal_type', 'items')
