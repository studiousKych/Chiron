from django.shortcuts import render
from rest_framework import generics, permissions
from .serializers import WorkoutProfileSerializer
from .models import WorkoutProfile
from .permissions import IsUser

class WorkoutProfileView(generics.RetrieveAPIView):

	serializer_class = WorkoutProfileSerializer
	# permission_classes = (permissions.IsAuthenticatedOrReadOnly,)

	def get_object(self):
		user = self.request.user

		return WorkoutProfile.objects.first()
