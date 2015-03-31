package org.mrudula.client;

import org.mrudula.utils.WeatherResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;

/**
 * Created by webonise on 24-03-2015.
 */
@Component
public class RestClient {
    private static final Logger LOG = LoggerFactory.getLogger(RestClient.class);

    public LinkedHashMap weatherData = new LinkedHashMap();
    final String DEGREE  = "\u00b0";

    protected enum RequestMethod {GET, POST}

    public void performGet(String url ){
         performRequest(url, RequestMethod.GET);
    }

    public void performPost(String url) {
         performRequest(url,RequestMethod.POST);
    }

    protected void performRequest(String url, RequestMethod requestMethod) {
        RestTemplate restTemplate = new RestTemplate();
            switch (requestMethod) {
                case GET:
                    WeatherResult weatherResultResponse = restTemplate.getForObject(url, WeatherResult.class);
                    setWeatherData(weatherResultResponse);
                    break;
                case POST:
                    WeatherResult weatherResultResponsePost = restTemplate.postForObject(url,new WeatherResult(),WeatherResult.class);
                    setWeatherData(weatherResultResponsePost);
                    break;
            }
    }

    public void setWeatherData(WeatherResult weatherResult){
        int resultSunrise = Integer.parseInt(weatherResult.getSys().getSunrise());
        int resultSunset = Integer.parseInt(weatherResult.getSys().getSunset());
        SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss a", Locale.ENGLISH);
        String sunrise = df.format(new Date(resultSunrise * 1000).getTime());
        String sunset = df.format(new Date(resultSunset * 1000).getTime());

        weatherData.put("City Name : ", weatherResult.getName());
        weatherData.put("Country : ", weatherResult.getSys().getCountry());
        weatherData.put("Latitude : ", weatherResult.getCoord().getLat());
        weatherData.put("Longitude : ", weatherResult.getCoord().getLon());
        weatherData.put("Date : ",weatherResult.getDt());
        weatherData.put("Temp : ", weatherResult.getMain().getTemp() + " " + DEGREE + "C");
        weatherData.put("maxTemp : ",weatherResult.getMain().getTemp_max() + " " + DEGREE + "C");
        weatherData.put("minTemp : ",weatherResult.getMain().getTemp_min() + " " + DEGREE + "C");
        weatherData.put("pressure : ", weatherResult.getMain().getPressure());
        weatherData.put("humidity : ", weatherResult.getMain().getHumidity());
        weatherData.put("sunrise : ", sunrise);
        weatherData.put("sunset : ", sunset);
    }

    public LinkedHashMap getWeatherData() {
        return weatherData;
    }

}
