from rest_framework.permissions import BasePermission
from .models import StagedMeal

class IsUser(BasePermission):

    def has_object_permission(self, request, view, obj):
        print(obj)
        if isinstance(obj, StagedMeal):
            print(obj)
            return obj.user == request.user
        return obj.diet_profile.user == request.user
