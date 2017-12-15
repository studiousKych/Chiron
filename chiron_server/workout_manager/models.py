# -*- coding: utf-8 -*-
from __future__ import unicode_literals

import requests
import json
from django.db import models
from django.db.models.signals import post_save
from django.contrib.auth.models import User
from rest_framework.authtoken.models import Token
from django.core.exceptions import ValidationError
from django.dispatch import receiver

class Set(models.Model):
	LIFT_CHOICES = {
		{"BENCH", 'Bench Press'),
		("PRESS", 'Shoulder Press'),
		("ROW", 'Bent Over Row'),
		("PULL", 'Pull-up'),
		("CURL", 'Bicep Curl'),
		("EXTEN", 'Tricep Extension'),
		("DEADS", 'Deadlift'),
		("SQUAT", 'Back Squat'),
		("RAISE", 'Calf Raises')
	}
	
	LIFT_CHOICES = {
		{"BENCH", ''),
		("PRESS", ''),
		("ROW", ''),
		("PULL", ''),
		("CURL", ''),
		("EXTEN", ''),
		("DEADS", ''),
		("SQUAT", ''),
		("RAISE", '')
	}
	
	name = models.CharField(
		max_length=5,
		choices=LIFT_CHOICES,
		default="BENCH",
	)
	load = models.IntegerField(default=0)
	reps = models.IntegerField(default=0)
	rest = models.IntegerField(default=0)
	url = mdoels.CharField(
		max_length=5,
		choices=
	)
	order = models.IntegerField(default=0)
	
class Workout(models.Model):
	WEEKDAY_CHOICES = (
        ("DEF", 'Default'),
        ("ONE", '1'),
        ("TWO", '2'),
        ("THR", '3'),
        ("FOU", '4'),
        ("FIV", '5'),
        ("SIX", '6'),
        ("SEV", '7')
    )

    day = models.CharField(
        max_length=3,
        choices=WEEKDAY_CHOICES,
        default="DEF",
    )
	
	workout_name = models.CharField(max_length=40)
	
	sets = models.ManyToManyField(Set)
	
	def __str__*self):
		return "{} - {}".format(
			self.day,
			self.workout_name,
		)
	
class WorkoutProfile(models.Model):
	user = models.ForeignKey(
        'auth.User',
        related_name='dietprofiles',
        on_delete=models.CASCADE
    )
    name = models.CharField(max_length=100)
	type = models.CharField(max_length=7, default="workout");
	workout = models.ManyToManyField(Workout)
	
	def __str__(self):
		return self.user.email

# Is this for edamam?		
@receiver(post_save, sender=User)
def create_auth_token(sender, instance=None, created=False, **kwargs):
    if created:
        Token.objects.create(user=instance)
