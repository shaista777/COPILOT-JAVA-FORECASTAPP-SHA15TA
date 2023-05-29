# COPILOT-JAVA-FORECASTAPP-SHA15TA
Weather forecast application created with Github Co-pilot enabled

Query Format 1 : CityName
Query Format 2 : CityName,ForecastCount

Inputline will be split by "," trimmed for additional spaces.
The integer forecast count will be verofied of it's a valid number.
In case of null/empty or invalid count value be set to '1'.

Pressing Enter without typing in any input will exit the application. 

As of now, the API response is parsed to have the select fields in the following format. 
For different metrics either the available static HashMap can be updated or can be configured as class members to be updated as per the user inputs

Forecast Time : 2023-05-29 00:00:00
Weather : Clear
Temperatue (celsius) : 24.91
Feels like (celsius) : 25.41
Atmospheric pressure on the sea level (hPa) : 1010
Atmospheric pressure on the ground level (hPa) : 949
Humidity (%)  : 75
Wind speed (meter/sec) : 2.55
Wind direction (degrees) : 273
Cloudiness (%) : 3
