# Chiron - Training App for College & College bound athletes

## Setup
- Navigate into `chiron_server` and run the following
    `pip install -r requirements.txt`

- Modify the ALLOWED_HOSTS property in ```chiron_server/settings.py``` and
    add your server's local ip address for on device testing.

- In the MainActivity of the android project change DIET_URL and WORKOUT_URL to
    point to your own server.

- Run the app

#### Note - December 15, 2017 @ 14:28

 - Due to issues in getting the json data to cleanly save to the database, the app is not complete. We should be able to get it done in the next day or so and are going to take the 10% penalty.
