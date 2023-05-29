# COPILOT-JAVA-FORECASTAPP-SHA15TA
Weather forecast application created with Github Co-pilot enabled<br>

Query Format 1 : CityName<br>
Query Format 2 : CityName,ForecastCount<br><br>

Inputline will be split by "," trimmed for additional spaces.<br>
The integer forecast count will be verofied of it's a valid number.<br>
In case of null/empty or invalid count value be set to '1'.<br>

Pressing Enter without typing in any input will exit the application. 

As of now, the API response is parsed to have the select fields in the following format. 
<br>For different metrics either the available static HashMap can be updated or can be configured as class members to be updated as per the user inputs<br><br>

Forecast Time : 2023-05-29 00:00:00<br>
Weather : Clear<br>
Temperatue (celsius) : 24.91<br>
Feels like (celsius) : 25.41<br>
Atmospheric pressure on the sea level (hPa) : 1010<br>
Atmospheric pressure on the ground level (hPa) : 949<br>
Humidity (%)  : 75<br>
Wind speed (meter/sec) : 2.55<br>
Wind direction (degrees) : 273<br>
Cloudiness (%) : 3<br>
