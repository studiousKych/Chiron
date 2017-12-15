from django.shortcuts import render
from rest_framework import generics, permissions
from .serializers import DietProfileSerializer
from .models import DietProfile
from .permissions import IsUser


# Create your views here.
class DietProfileView(generics.RetrieveAPIView):

    queryset = DietProfile.objects.first()
    serializer_class = DietProfileSerializer
    # permission_classes = (permissions.IsAuthenticated,)

    # def get_object(self):
    #     user = self.request.user
    #
    #     return DietProfile.objects.get(user=user)
