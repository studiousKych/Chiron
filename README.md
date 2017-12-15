# Chiron - Training App for College & College bound athletes

## Setup
- Navigate into `chiron_server` and run the following
    `pip install -r requirements.txt`

- Modify the `ALLOWED_HOSTS` property in ```chiron_server/settings.py``` and
    replace the first string with your server's local ip address for on device testing.

- from `chiron_server` run `python manage.py runserver 0:PORT` where
    `PORT` is the port you wish to run on (optional). This will start the
    server and it will accept requests from any IP that accesses it through it's
    `ALLOWED_HOSTS` setting.

- In the MainActivity of the android project change DIET_URL and WORKOUT_URL to
    point to your own server.

- Run the app

#### Note - December 15, 2017 @ 14:28

 - Due to issues in getting the json data to cleanly save to the database, the app is not complete. We should be able to get it done in the next day or so and are going to take the 10% penalty.
