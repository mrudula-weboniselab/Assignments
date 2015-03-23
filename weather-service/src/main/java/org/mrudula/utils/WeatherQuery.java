package org.mrudula.utils;

import org.mrudula.client.WeatherHttpClient;
import org.mrudula.region.MapRegion;
import org.mrudula.region.UIRegion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by webonise on 23-03-2015.
 */

@Component
public class WeatherQuery {
    private static final Logger LOG = LoggerFactory.getLogger(WeatherQuery.class);

    @Autowired
    MapRegion mapRegion;

   /* @Autowired
    WeatherHttpClient weatherHttpClient;

    @Autowired
    ParseJasonString parseJasonString;*/

    public static final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather";
    public LinkedHashMap weatherData = new LinkedHashMap();
    private WeatherHttpClient weatherHttpClient = new WeatherHttpClient();
    private ParseJasonString parseJasonString = new ParseJasonString();

    public void findWeatherByLocation(String cityRegion ,String unitType) {
        String city = cityRegion;
        String units = "f".equalsIgnoreCase(unitType) ? "imperial": "metric";
        String weatherRequest = WEATHER_URL +"?q=" + city +"&" + "units=" + units +"&" + "mode=json";
        getWeatherDataFromHttpClient(weatherRequest, unitType);
    }

    public void findWeatherByLatLng(String lon,String lats, String unitType) {
        String lng = lon;
        String lat = lats;
        String units = "f".equalsIgnoreCase(unitType) ? "imperial": "metric";
        String weatherRequest = WEATHER_URL +"?lat=" + lat +"&lon=" + lng +"&" + "units=" + units +"&" + "mode=json";
        getWeatherDataFromHttpClient(weatherRequest, unitType);
    }

    public void findWeatherFromMap(String lon,String lats, String unitType) {
        String lng = lon;
        String lat = lats;
        String units = "f".equalsIgnoreCase(unitType) ? "imperial": "metric";
        String weatherRequest = WEATHER_URL +"?lat=" + lat +"&lon=" + lng +"&" + "units=" + units +"&" + "mode=json";
        getWeatherDataUsingMapFromHttpClient(weatherRequest, unitType);
        UIRegion uiRegion = new UIRegion();
        uiRegion.weatherDataFromMap(lng,lat);
    }

    public void getWeatherDataUsingMapFromHttpClient(String weatherRequest, String unitType) {
        String json = weatherHttpClient.performGet(weatherRequest,null);
        mapRegion.setJSObject(json);
        weatherData = parseJasonString.ParseJsonStringToWeatherData(json);
    }

    public void getWeatherDataFromHttpClient(String weatherRequest, String unitType) {
        String json = weatherHttpClient.performGet(weatherRequest,null);
        weatherData = parseJasonString.ParseJsonStringToWeatherData(json);
    }

    public HashMap getWeatherData() {
        return weatherData;
    }
}
