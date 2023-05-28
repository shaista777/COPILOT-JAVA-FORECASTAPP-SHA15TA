package githubcopilot.weatherforecastapp;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import com.jayway.jsonpath.JsonPath;

public class WeatherForeCastClient {

	private static final String APP_STRING = "a2aa9441a1bffb03736556f7328a8b92";
	private static final String URLFORECAST_STRING = "https://api.openweathermap.org/data/2.5/forecast";
	private static final String CONST_QUERY_PARAM_STRING = "&units=metric&mode=json&lang=en&type=accurate";
	private static final Map<String, String> FORECAST_JQ_MAP;
	static {
        Map<String, String> aMap = new LinkedHashMap<String, String>();
        aMap.put("Forecast Time", "$.list[{i}].dt_txt");
        aMap.put("Weather", "$.list[{i}].weather[0].main");
		aMap.put("Temperatue (celsius)", "$.list[{i}].main.temp");
        aMap.put("Feels like (celsius)", "$.list[{i}].main.feels_like");
		aMap.put("Atmospheric pressure on the sea level (hPa)", "$.list[{i}].main.sea_level");
        aMap.put("Atmospheric pressure on the ground level (hPa)", "$.list[{i}].main.grnd_level");
		aMap.put("Humidity (%) ", "$.list[{i}].main.humidity");
		aMap.put("Wind speed (meter/sec)", "$.list[{i}].wind.speed");
		aMap.put("Wind direction (degrees)", "$.list[{i}].wind.deg");
		aMap.put("Cloudiness (%)", "$.list[{i}].clouds.all");

        FORECAST_JQ_MAP = Collections.unmodifiableMap(aMap);
    }

	private URL forecastURL;
	private HttpURLConnection forecastConnection;

	public WeatherForeCastClient(String city, String nextCount) throws UnsupportedEncodingException, IOException {

		
		forecastURL = new URL(URLFORECAST_STRING + "?appid=" + APP_STRING + "&q=" + city + "&cnt=" + nextCount + CONST_QUERY_PARAM_STRING);
		forecastConnection = (HttpURLConnection) forecastURL.openConnection();
	
		forecastConnection.setDoInput(true);
		forecastConnection.setRequestMethod("GET");
		forecastConnection.setRequestProperty("Content-Type", "application/json");
		

	}

	public String getWeatherForecast() throws IOException {
     

		switch(forecastConnection.getResponseCode()){
			case 200:
			    break;
			case 401:
				return("Invalid API key\n");
			case 404:
				return("City not found, try another\n");
			case 429:
				return("Too many requests, try again later\n");
			case 400:
				return("Invalid request\n");
			case 500:
				return("Internal server error\n");
			case 503:
			return("Service unavailable, try again later\n");
			default:
			return("Something went wrong, try again later\n");
		}

		BufferedReader in = new BufferedReader(new InputStreamReader(forecastConnection.getInputStream()));
		String inputLine = null;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}

		in.close();
		forecastConnection.disconnect();
		return parseAndPrintForecast(response.toString());

		
	}

	public String parseAndPrintForecast(String response){

		StringBuilder fornattedResponse = new StringBuilder();
		int countInResponse=Integer.parseInt(JsonPath.read(response,"$.cnt").toString().replaceAll("[\\[\\]]",""));

		while(countInResponse-->0)
		{
			for(Map.Entry<String, String> entry : FORECAST_JQ_MAP.entrySet()){

				String jsonKey=entry.getKey();
				String jsonPathValue=entry.getValue().replace("{i}", (countInResponse-1)+"");
				String jsonPathResponse=JsonPath.read(response,jsonPathValue).toString().replaceAll("[\"\\[\\]]", "");
				fornattedResponse
				.append(jsonKey)
				.append(" : ")
				.append(jsonPathResponse)
				.append("\n");
		
			}
			fornattedResponse.append("\n");
		}
		return fornattedResponse.toString();

	}

}
