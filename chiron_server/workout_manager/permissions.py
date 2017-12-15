from rest_framework.permissions import BasePermission
from .models import Workout

class IsUser(BasePermission):

    def has_object_permission(self, request, view, obj):
        print(obj)
        if isinstance(obj, Workout):
            print(obj)
            return obj.user == request.user
        return obj.workout_profile.user == request.user