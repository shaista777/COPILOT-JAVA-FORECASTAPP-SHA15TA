package githubcopilot.weatherforecastapp;

import java.io.IOException;
import java.util.Scanner;

public class App {
     
    public void startForecastApp(){

        System.out.println("\n\n==================================================================================\n");
    	System.out.println("Welcome to 'Weather Forecast' by OpenWeatherMap!");
        System.out.println("Please enter the city name and optionally the number of forecasts (default is 1) separated by comma, or press Enter to exit:\n\n");

    }
    
    public void exitForecastApp(){
        
    	System.out.println("\nExitting the application Thank you!\n");
    }

    public String getWeatherForecast(String city,String nextCount) throws IOException {
    
        return  new WeatherForeCastClient(city,nextCount).getWeatherForecast();
    }

 
	public static void main(String[] args) throws IOException {

        new App().startForecastApp();
        
        @SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
        String userInput=null;
        String cityName=null;
        String forecastCount=null;
        System.out.println("City,Forecast Count : ");
        
    	while(!(userInput=sc.nextLine()).isEmpty())
        {
            String input[] = userInput.split(",");
            cityName = input[0].trim();

            if(input.length>1 && input[1].trim().matches("[0-9]+"))
            	forecastCount=input[1].trim();
            else 
            	forecastCount="1";        

        	System.out.println(new App().getWeatherForecast(cityName,forecastCount));
            System.out.println("City, next forecast Count : ");

        }
        
    	new App().exitForecastApp();

    }
}
