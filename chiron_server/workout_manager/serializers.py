from rest_framework import serializers
from models import WorkoutProfile

class WorkoutProfileSerializer(serializers.ModelSerializers):

	# user = serializers.ReadOnlyField(source='user.email')
	
	class Meta:
		model = WorkoutProfile
		fields = ('name', 'workout')
		# Why?
		depth = 3