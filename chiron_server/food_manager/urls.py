from django.conf.urls import url, include
from rest_framework.urlpatterns import format_suffix_patterns
from .views import DietProfileView

urlpatterns = {
    url(r'^auth/', include('rest_framework.urls', namespace='rest_framework')),
    url(r'^diets/$', DietProfileView.as_view(), name='viewdiets')
    # url(r'^stagedmeals/$', StagedMealView.as_view(), name='create'),
    # url(r'^setcourses/$', SetCourseView.as_view(), name='viewsetcourse')
}

urlpatterns = format_suffix_patterns(urlpatterns)
