from rest_framework import serializers
from rest_framework.renderers import JSONRenderer
from rest_framework_xml.renderers import XMLRenderer
from rest_framework.views import APIView
from .models import DietProfile


class DietProfileSerializer(serializers.ModelSerializer):

    class Meta:
        model = DietProfile
        fields = ('name', 'data_type', 'staged_meals')
        depth=3
