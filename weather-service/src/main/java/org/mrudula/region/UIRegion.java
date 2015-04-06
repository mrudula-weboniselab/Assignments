package org.mrudula.region;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.VBox;
import org.mrudula.utils.WeatherQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by webonise on 16-03-2015.
 */
@Component
public class UIRegion extends VBox {
    private static final Logger LOG = LoggerFactory.getLogger(UIRegion.class);

    @FXML
    private TextField cityNameTextField;
    @FXML
    private TextField lat;
    @FXML
    private TextField lng;
    @FXML
    private Button citySearch;
    @FXML
    private Label pressure;
    @FXML
    private Label humidity;
    @FXML
    private Label sunrise;
    @FXML
    private Label sunset;
    @FXML
    private Label geoCoords;
    @FXML
    private Button latLngSearch;
    @FXML
    private Label cityName;
    @FXML
    private Label cityTemp;
    @FXML
    private Label cityMinMax;

    WeatherQuery weatherQuery = new WeatherQuery();

    public UIRegion() {
        super();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/UIRegion.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException ex) {
            throw new RuntimeException("Error loading UIRegion: " + ex.getMessage(), ex);
        }

        LOG.debug("UI : "+weatherQuery.getWeatherDataMap().size());

        defaultWeatherDataInfo();

        EventHandler<InputEvent> eventHandler = new EventHandler<InputEvent>() {
            @Override
            public void handle(InputEvent event) {
                weatherQuery.findWeatherByLocation(cityNameTextField.getText(),"c");
                showWeatherData();
             }
        };
        EventHandler<InputEvent> eventHandlerForLatLong = new EventHandler<InputEvent>() {
            @Override
            public void handle(InputEvent event) {
                weatherQuery.findWeatherByLatLng(lng.getText(),lat.getText(),"c");
                showWeatherData();
            }
        };

        citySearch.setOnMouseClicked(eventHandler);
        latLngSearch.setOnMouseClicked(eventHandlerForLatLong);
    }

    public void defaultWeatherDataInfo(){

        weatherQuery.findWeatherByLocation("London", "c");
        showWeatherData();
    }

    public String weatherDataFromMap(final String mapLng, final String mapLat) {
        latLngSearch.setOnAction(event -> {
            clearData();
            weatherQuery.findWeatherByLatLng(mapLng,mapLat,"c");
            showWeatherData();
        });
        latLngSearch.fire();
        return (String) weatherQuery.getWeatherData().get("Temp");
    }

    public void clearData(){
        lat.setText("");
        lng.setText("");
        cityNameTextField.setText("");
    }

    public void showWeatherData(){
        HashMap weatherData = weatherQuery.getWeatherData();
        cityName.setText((String)weatherData.get("City")+","+(String)weatherData.get("Country"));
        cityTemp.setText((String)weatherData.get("Temp"));
        cityMinMax.setText("[Min Temp : "+(String)weatherData.get("maxTemp")+",\n Max Temp: "+(String)weatherData.get("minTemp")+" ]");
        pressure.setText((String)weatherData.get("pressure"));
        humidity.setText((String)weatherData.get("humidity"));
        sunrise.setText((String)weatherData.get("sunrise"));
        sunset.setText((String)weatherData.get("sunset"));
        geoCoords.setText("[ "+(String)weatherData.get("Latitude")+", "+(String)weatherData.get("Longitude")+" ]");
    }
}