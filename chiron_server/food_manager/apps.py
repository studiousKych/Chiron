from django.apps import AppConfig


class FoodManagerConfig(AppConfig):
    name = 'food_manager'

    def ready(self):
        import food_manager.permissions
