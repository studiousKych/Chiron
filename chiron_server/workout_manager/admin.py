from django.contrib import admin

# Register your models here.
from .models import Set, Workout, WorkoutProfile

admin.site.register(Set)
admin.site.register(Workout),
admin.site.register(WorkoutProfile)