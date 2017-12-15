from rest_framework import serializers
from .models import DietProfile


class DietProfileSerializer(serializers.ModelSerializer):

    # user = serializers.ReadOnlyField(source='user.email')

    class Meta:
        model = DietProfile
        fields = ('name', 'data_type', 'staged_meals')
        depth=3
