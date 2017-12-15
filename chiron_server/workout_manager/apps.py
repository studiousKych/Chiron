from django.apps import AppConfig


class WorkoutManagerConfig(AppConfig):
    name = 'workout_manager'

    def ready(self):
        import workout_manager.permissions
