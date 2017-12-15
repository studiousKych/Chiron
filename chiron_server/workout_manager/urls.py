from django.conf.urls import url, include
from rest_framework.urlpatterns import format_suffix_patterns
from rest_framework.authtoken.views import obtain_auth_token
from .views import WorkoutProfileView

urlpatterns = {
    url(r'^auth/', include('rest_framework.urls', namespace='rest_framework')),
    url(r'^workouts/$', WorkoutProfileView.as_view(), name='viewworkouts'),
    url(r'^get-token/', obtain_auth_token)
}

urlpatterns = format_suffix_patterns(urlpatterns)