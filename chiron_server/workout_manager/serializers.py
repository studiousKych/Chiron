from rest_framework import serializers
from .models import WorkoutProfile

class WorkoutProfileSerializer(serializers.ModelSerializer):

	# user = serializers.ReadOnlyField(source='user.email')

	class Meta:
		model = WorkoutProfile
		fields = ('name', 'data_type', 'workout')
		# Why?
		depth = 3
