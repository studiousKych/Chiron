# Generated by Django 2.0 on 2017-12-15 16:02

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('food_manager', '0001_initial'),
    ]

    operations = [
        migrations.AddField(
            model_name='dietprofile',
            name='data_type',
            field=models.CharField(default='diet', max_length=4),
        ),
    ]
