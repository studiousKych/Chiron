from django.apps import AppConfig


class WorkoutManageConfig(AppConfig):
    name = 'workout_manager'

    def ready(self):
        import workout_manager.permissions
