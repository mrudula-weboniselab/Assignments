package org.mrudula.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;

/**
 * Created by webonise on 20-03-2015.
 */
@Component
public class ParseJasonString {
    private static final Logger LOG = LoggerFactory.getLogger(ParseJasonString.class);

    LinkedHashMap weatherData = new LinkedHashMap<>();

    final String DEGREE  = "\u00b0";

    public LinkedHashMap ParseJsonStringToWeatherData(String json) {
           try {

               LOG.debug("Json String : "+json);

                JSONObject jsonObject = new JSONObject(json);

           /* coord */

                JSONObject jsonObjectCoord = jsonObject.getJSONObject("coord");
                Double resultLon = jsonObjectCoord.getDouble("lon");
                Double resultLat = jsonObjectCoord.getDouble("lat");

           /*  sys */

                JSONObject jsonObjectSys = jsonObject.getJSONObject("sys");
                String resultCountry = jsonObjectSys.getString("country");
                int resultSunrise = jsonObjectSys.getInt("sunrise");
                int resultSunset = jsonObjectSys.getInt("sunset");
                SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss a", Locale.ENGLISH);
                String sunrise = df.format(new Date(resultSunrise * 1000).getTime());
                String sunset = df.format(new Date(resultSunset * 1000).getTime());

            /*  weather  */

                String resultWeather;
                JSONArray jsonWeatherArray = jsonObject.getJSONArray("weather");
                if (jsonWeatherArray.length() > 0) {
                    JSONObject jsonObjectWeather = jsonWeatherArray.getJSONObject(0);
                    int resultId = jsonObjectWeather.getInt("id");
                    String resultMain = jsonObjectWeather.getString("main");
                    String resultDescription = jsonObjectWeather.getString("description");
                    String resultIcon = jsonObjectWeather.getString("icon");

                    resultWeather = "weather\tid: " + resultId + "\tmain: " + resultMain + "\tdescription: " + resultDescription + "\ticon: " + resultIcon;

                } else {
                    resultWeather = "weather empty!";
                }

            /*   base   */

                String resultBase = jsonObject.getString("base");

            /*    main  */

                JSONObject jsonObjectMain = jsonObject.getJSONObject("main");
                Double result_temp = jsonObjectMain.getDouble("temp");

                Double celsius = result_temp - 273.0;
                Double resultPressure = jsonObjectMain.getDouble("pressure");
                Double resultHumidity = jsonObjectMain.getDouble("humidity");
                Double resultTempMin = jsonObjectMain.getDouble("temp_min");
                Double resultTempMax = jsonObjectMain.getDouble("temp_max");

            /*   wind  */

                JSONObject jsonObjectWind = jsonObject.getJSONObject("wind");
                Double resultSpeed = jsonObjectWind.getDouble("speed");
                Double resultDeg = jsonObjectWind.getDouble("deg");
                String resultWind = "wind\tspeed: " + resultSpeed + "\tdeg: " + resultDeg;

            /*   clouds    */
                JSONObject jsonObjectClouds = jsonObject.getJSONObject("clouds");
                int resultAll = jsonObjectClouds.getInt("all");

            /*   dt  */
                int resultDt = jsonObject.getInt("dt");

            /*   id   */
                int resultId = jsonObject.getInt("id");

            /*  name  */
                String resultName = jsonObject.getString("name");

                weatherData.put("City Name : ", resultName);
                weatherData.put("Country : ", resultCountry);
                weatherData.put("Date : ", Integer.toString(resultDt));
                weatherData.put("Temp : ", Double.toString(result_temp) + " " + DEGREE + "C");
                weatherData.put("maxTemp : ", Double.toString(resultTempMin) + " " + DEGREE + "C");
                weatherData.put("minTemp : ", Double.toString(resultTempMin) + " " + DEGREE + "C");
                weatherData.put("pressure : ", Double.toString(resultPressure));
                weatherData.put("humidity : ", Double.toString(resultHumidity));
                weatherData.put("sunrise : ", sunrise);
                weatherData.put("sunset : ", sunset);

        }catch (JSONException e){
            LOG.error("Error In Parsing Jason String To Find Weather Data: "+e);
        }

        return weatherData;
    }

    public LinkedHashMap getWeatherData() {
        return weatherData;
    }

    public void setWeatherData(LinkedHashMap weatherData) {
        this.weatherData = weatherData;
    }


}
