package org.mrudula.region;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.mrudula.utils.WeatherQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

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
    public VBox weatherDataInfo;
    @FXML
    public VBox weatherDataInfo1;
    @FXML
    private Button latLngSearch;

    WeatherQuery weatherQuery = new WeatherQuery();

    public List<HBox> listOfWeatherData = new ArrayList<>();
    public List<HBox> listOfWeatherData1 =  new ArrayList<>();

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
                clearWeatherData();
                weatherQuery.findWeatherByLocation(cityNameTextField.getText(),"c");
                showWeatherData();
             }
        };
        EventHandler<InputEvent> eventHandlerForLatLong = new EventHandler<InputEvent>() {
            @Override
            public void handle(InputEvent event) {
                clearWeatherData();
                weatherQuery.findWeatherByLatLng(lng.getText(),lat.getText(),"c");
                showWeatherData();
            }
        };

        citySearch.setOnMouseClicked(eventHandler);
        latLngSearch.setOnMouseClicked(eventHandlerForLatLong);
    }

    public void defaultWeatherDataInfo(){
        clearWeatherData();
        weatherQuery.findWeatherByLocation("London", "c");
        showWeatherData();
    }

    public void clearWeatherData(){
        listOfWeatherData.clear();
        listOfWeatherData1.clear();
        weatherDataInfo.getChildren().clear();
        weatherDataInfo1.getChildren().clear();
    }

    public String weatherDataFromMap(final String mapLng, final String mapLat) {
        latLngSearch.setOnAction(event -> {
            LOG.debug("EVENT OCCURS : " + lat.getText());
            clearWeatherData();
            weatherQuery.findWeatherByLatLng(mapLng,mapLat,"c");
            showWeatherData();
        });
        latLngSearch.fire();
        return (String) weatherQuery.getWeatherData().get("Temp : ");
    }

    public void showWeatherData(){
        Set set = weatherQuery.getWeatherData().entrySet();
        Iterator iterator = set.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            count++;
            HBox hBox = new HBox();
            Map.Entry info = (Map.Entry) iterator.next();
            Label labelKey = new Label((String) info.getKey());
            Label lableValue = new Label((String) info.getValue());
            hBox.getStylesheets().add("css/weather_demo.css");
            labelKey.getStyleClass().add("keyLable");
            lableValue.getStyleClass().add("valueLable");
            hBox.getChildren().addAll(labelKey, lableValue);
            if (count <= 6) {
                listOfWeatherData.add(hBox);
            } else {
                listOfWeatherData1.add(hBox);
            }
        }
        weatherDataInfo.getChildren().addAll(listOfWeatherData);
        weatherDataInfo1.getChildren().addAll(listOfWeatherData1);
    }

}