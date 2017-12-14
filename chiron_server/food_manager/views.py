from django.shortcuts import render
from rest_framework import generics
from .serializers import StagedMealSerializer
from .models import StagedMeal


# Create your views here.
class CreateView(generics.ListAPIView):
    queryset = StagedMeal.objects.all()
    serializer_class = StagedMealSerializer
