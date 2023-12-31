
Description 2-6 sentences:

1. The Android Application that the project team seeks to develop targets the weather API from the openweathermap.org, gets data and parses the data.

2. SearchFragment: The user of the application can type in the Location/ town.

3. SearchFragment: Then the user presses the button to get the weather data.

4. SearchFragment: the data gets saved into the local storage database.

5. SearchFragment: Then the user is navigated to the WeatherFragment.

6. WeatherFragment: There the weather data is retrieved from the database.That data is presented in the Layout. Furthermore, the weather picture is retrieved from
the folder which fits the description, weather description = rain --> picture = rain picture.

7. MainFragment: Then, in the start menu of the appliaction the user is provided with his recent searches in a list (local storage).
The row shows the name of the town that was searched for. Liverpool, San Francisco..

8. MainFragment: So if the user clicks a row, she is navigated to the WeatherFragment.

9. WeatherFragment: There the data for the weather is presented.

10. MainFragment: the user wants to delete a row, he can do that with the click on the Trash-Picture, right side.

Team: Sarah Föll, Clemens Feth.

two categories that we implement in the app:

- Data Storage	   ---> Store app local data using SQL, Key/Value.
- Networking	     ---> Consume API, Parse JSON, XML, GraphQL.
- little bit of the Media cause we retrieve the pictures.

side categories:

- Media and Camera	      ---> Take picture, load image from gallery.
- Location and Sensors	  ---> GPS, Gyroscope, Motion, Barometer, Altimeter.
- Connectivity	          ---> Bluetooth, Wifi, Beacons.
- Animation and Graphics	---> Implement charts, complex animations.

Deadline: 31.01.2024 23:59

Reminder:
- Android Material Design muss genutzt werden.
- No external libraries except what we discussed in the lecture, implement it yourself.
- No cloud databases.
- Focus on code structure and patterns, not functionality.
- Keep it simple, the semester is short.

Presenation:

- Date: 01.02.2024, 10:00-13:15.
- project should be presented. In the presentation each team member should have the same amount of time to talk.
- Unexcused absence will make you fail the course.
- Show a quick overview of your app on the emulator or device.
- Present code parts which you are proud of or tests.
- team has 8 minutes time per project and then a 2 minutes break.

