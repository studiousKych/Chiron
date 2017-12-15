# Generated by Django 2.0 on 2017-12-15 15:42

from django.conf import settings
from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    initial = True

    dependencies = [
        migrations.swappable_dependency(settings.AUTH_USER_MODEL),
    ]

    operations = [
        migrations.CreateModel(
            name='DietProfile',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('name', models.CharField(max_length=100)),
                ('suggested_calories', models.DecimalField(decimal_places=2, max_digits=7)),
                ('suggested_fats', models.DecimalField(decimal_places=2, max_digits=7)),
                ('suggested_carbs', models.DecimalField(decimal_places=2, max_digits=7)),
                ('suggested_proteins', models.DecimalField(decimal_places=2, max_digits=7)),
            ],
        ),
        migrations.CreateModel(
            name='FoodItem',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('calories', models.DecimalField(decimal_places=2, default=0, editable=False, max_digits=7)),
                ('fats', models.DecimalField(decimal_places=2, default=0, editable=False, max_digits=7)),
                ('carbs', models.DecimalField(decimal_places=2, default=0, editable=False, max_digits=7)),
                ('protein', models.DecimalField(decimal_places=2, default=0, editable=False, max_digits=7)),
                ('name', models.CharField(max_length=100)),
                ('food_type', models.CharField(choices=[('Snack', 'Snack'), ('Drink', 'Drink'), ('Entree', 'Entree'), ('Side', 'Side'), ('Dessert', 'Dessert')], default='Entree', max_length=7)),
                ('uri', models.CharField(blank=True, max_length=100, null=True)),
                ('total_servings', models.PositiveSmallIntegerField(blank=True, default=1)),
                ('custom_recipe', models.TextField(blank=True, null=True)),
            ],
            options={
                'abstract': False,
            },
        ),
        migrations.CreateModel(
            name='SetCourse',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('servings', models.PositiveSmallIntegerField(default=1)),
                ('food_item', models.ForeignKey(on_delete=django.db.models.deletion.PROTECT, to='food_manager.FoodItem')),
            ],
        ),
        migrations.CreateModel(
            name='StagedMeal',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('day', models.CharField(choices=[('DEF', 'Default'), ('MON', 'Monday'), ('TUE', 'Tuesday'), ('WED', 'Wednesday'), ('THU', 'Thursday'), ('FRI', 'Friday'), ('SAT', 'Saturday'), ('SUN', 'Sunday')], default='DEF', max_length=3)),
                ('meal_num', models.PositiveSmallIntegerField()),
                ('meal_type', models.CharField(choices=[('Breakfast', 'Breakfast'), ('Lunch', 'Lunch'), ('Dinner', 'Dinner'), ('Snack', 'Snack')], default='Snack', max_length=9)),
                ('meal_time', models.TimeField(blank=True, null=True)),
                ('items', models.ManyToManyField(to='food_manager.SetCourse')),
            ],
        ),
        migrations.AddField(
            model_name='dietprofile',
            name='staged_meals',
            field=models.ManyToManyField(to='food_manager.StagedMeal'),
        ),
        migrations.AddField(
            model_name='dietprofile',
            name='user',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='dietprofiles', to=settings.AUTH_USER_MODEL),
        ),
    ]
