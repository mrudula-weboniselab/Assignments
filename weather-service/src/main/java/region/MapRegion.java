package region;

import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by webonise on 16-03-2015.
 */
public class MapRegion extends VBox {
    private static final Logger LOG = LoggerFactory.getLogger(MapRegion.class);

    @FXML
    private VBox mapRegion;
    @FXML
    private WebView webView;
    @FXML
    private VBox map;


    public static final String WEATHER_URL ="http://api.openweathermap.org/data/2.5/weather";
    final String DEGREE  = "\u00b0";
    public  LinkedHashMap weatherData = new LinkedHashMap();
    JSObject jsObject;

    public MapRegion(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/MapRegion.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException ex) {
            throw new RuntimeException("Error loading MapsRegion: " + ex.getMessage(), ex);
        }

        final URL urlMap = getClass().getClassLoader().getResource("html/mapregion.html");
        WebEngine webEngine=webView.getEngine();
        webEngine.setJavaScriptEnabled(true);
        webEngine.load(urlMap.toExternalForm());
        webView.getEngine().getLoadWorker().stateProperty().addListener( (observableValue, oldValue, newValue) -> {
            if (newValue == Worker.State.SUCCEEDED) {
                 jsObject = (JSObject)
                webView.getEngine().executeScript("window");
                jsObject.setMember("WeatherView", this);
            }
        });
        map.setVisible(true);
    }


    public void findWeatherByLocation(String cityRegion ,String unitType) {
        String queryString = null;
        queryString = cityRegion;
        String units = "f".equalsIgnoreCase(unitType) ? "imperial": "metric";
        String weatherRequest = WEATHER_URL +"?q=" + queryString +"&" + "units=" + units +"&" + "mode=json";
        getWeatherDataFromResponse(weatherRequest, unitType);
    }

    public void findWeatherByLatLng(String lon,String lats, String unitType) {
        String queryString = null;
        String queryString1 = null;
        queryString = lon;
        queryString1 = lats;
        String units = "f".equalsIgnoreCase(unitType) ? "imperial": "metric";
        String weatherRequest = WEATHER_URL +"?lat=" + queryString1 +"&lon=" + queryString +"&" + "units=" + units +"&" + "mode=json";
        getWeatherDataFromResponse(weatherRequest, unitType);

    }


    public void findWeatherFromMap(String lon,String lats, String unitType) {
        String queryString = lon;
        String queryString1 = lats;
        String units = "f".equalsIgnoreCase(unitType) ? "imperial": "metric";
        String weatherRequest = WEATHER_URL +"?lat=" + queryString1 +"&lon=" + queryString + "&" +"units=" +units +"&" + "mode=json";
        getWeatherDataFromResponseFromMap(weatherRequest, unitType);
        UIRegion uiRegion = new UIRegion();
        uiRegion.weatherDataFromMap(queryString,queryString1);
    }

    public void getWeatherDataFromResponseFromMap(String weatherRequest, String unitType) {
        String json = jsonGetResponse(weatherRequest);
        jsObject.setMember("temp",json);
        weatherData = ParseResult(json);
    }

    public void getWeatherDataFromResponse(String weatherRequest, String unitType) {
        String json = jsonGetResponse(weatherRequest);
        weatherData = ParseResult(json);
    }

    public String jsonGetResponse(String urlQueryString) {
        String json = null;
        try {
            LOG.info("Request: "+ urlQueryString);

            URL url = new URL(urlQueryString);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("charset", "UTF-8");
            connection.connect();
            InputStream inStream = connection.getInputStream();
            json = inputStreamToString(inStream);
            LOG.info("Response: "+json);

        } catch (IOException  ex ) {
          LOG.error("Error in json Response : "+ex);

        }
        return json;
    }

    public LinkedHashMap ParseResult(String json) {
        LinkedHashMap weatherData = new LinkedHashMap<>();

        try {
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


    public String inputStreamToString(InputStream inputStream) {
        String text = new Scanner(inputStream, "UTF-8").useDelimiter("\\Z").next();
        return text;
    }

    public HashMap getWeatherData() {
        return weatherData;
    }


}
