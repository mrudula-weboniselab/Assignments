package org.mrudula.utils;

import javafx.scene.layout.Region;
import netscape.javascript.JSObject;
import org.mrudula.client.RestClient;
import org.mrudula.region.UIRegion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by webonise on 23-03-2015.
 */
@Component
public class WeatherQuery extends Region {
    private static final Logger LOG = LoggerFactory.getLogger(WeatherQuery.class);

    public static final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather";
    public LinkedHashMap weatherData = new LinkedHashMap();
    public LinkedHashMap weatherDataMap = new LinkedHashMap();
    RestClient restClient = new RestClient();

    public void findWeatherByLocation(String cityRegion, String unitType) {
        String city = cityRegion;
        String units = "f".equalsIgnoreCase(unitType) ? "imperial" : "metric";
        String weatherRequest = WEATHER_URL + "?q=" + city + "&" + "units=" + units + "&" + "mode=json";
        restClient.performGet(weatherRequest);
        weatherData = restClient.getWeatherData();
    }

    public void findWeatherByLatLng(String lon, String lats, String unitType) {
        String lng = lon;
        String lat = lats;
        String units = "f".equalsIgnoreCase(unitType) ? "imperial" : "metric";
        String weatherRequest = WEATHER_URL + "?lat=" + lat + "&lon=" + lng + "&" + "units=" + units + "&" + "mode=json";
        restClient.performGet(weatherRequest);
        weatherData = restClient.getWeatherData();
    }

    public void findWeatherFromMap(String lon, String lats, String unitType, JSObject jsObject) {
        String lng = lon;
        String lat = lats;
        String units = "f".equalsIgnoreCase(unitType) ? "imperial" : "metric";
        String weatherRequest = WEATHER_URL + "?lat=" + lat + "&lon=" + lng + "&" + "units=" + units + "&" + "mode=json";
        restClient.performGet(weatherRequest);
        weatherData = restClient.getWeatherData();
        jsObject.setMember("temp", (String) weatherData.get("Temp : "));
        UIRegion uiRegion = new UIRegion();
        uiRegion.weatherDataFromMap(lon,lat);
    }

    public HashMap getWeatherData() {
        return weatherData;
    }

    public HashMap getWeatherDataMap() { return weatherDataMap; }
}