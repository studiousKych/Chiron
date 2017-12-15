from django.shortcuts import render
from rest_framework import generics, permissions
from .serializers import WorkoutProfileSerializers
from .models import WorkoutProfle
from .permissions import IsUser

class WorkoutProfileView(generics.RetrieveAPIView):
	
	serializer_class = WorkoutProfileSerializers
	permission_classes = (permissions.IsAuthenticatedOrReadOnly,)
	
	def get_object(self):
		user = self.request.user
		
		return WorkoutProfle.objects.first()