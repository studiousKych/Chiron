from django.conf.urls import url, include
from rest_framework.urlpatterns import format_suffix_patterns
from rest_framework.authtoken.views import obtain_auth_token
from .views import DietProfileView

urlpatterns = {
    url(r'^diets/$', DietProfileView.as_view(), name='viewdiets'),
    url(r'^get-token/', obtain_auth_token)
}

urlpatterns = format_suffix_patterns(urlpatterns)
