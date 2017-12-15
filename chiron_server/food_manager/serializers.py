from rest_framework import serializers
from .models import DietProfile


class DietProfileSerializer(serializers.ModelSerializer):

    user = serializers.ReadOnlyField(source='user.email')

    class Meta:
        model = DietProfile
        fields = ('user', 'name', 'staged_meals')
        depth=3
