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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

/**
 * Created by webonise on 16-03-2015.
 */

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

    public MapRegion mapRegion=new MapRegion();

    public List<HBox> listOfWeatherData = new ArrayList<>();
    public List<HBox> listOfWeatherData1 = new ArrayList<>();

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

        defaultWeatherDataInfo();

        EventHandler<InputEvent> eventHandler = new EventHandler<InputEvent>() {
            @Override
            public void handle(InputEvent event) {
                LOG.debug("EVENT: "+weatherDataInfo.getChildren().size());
                clearWeatherData();
                mapRegion.findWeatherByLocation(cityNameTextField.getText(),"c");
                Set set = mapRegion.getWeatherData().entrySet();
                Iterator iterator = set.iterator();
                int count = 0;
                while (iterator.hasNext()){
                    count++;
                    HBox hBox = new HBox();
                    Map.Entry info = (Map.Entry)iterator.next();
                    Label labelKey = new Label((String)info.getKey());
                    Label lableValue = new Label((String) info.getValue());
                    hBox.getStylesheets().add("css/weather_demo.css");
                    labelKey.getStyleClass().add("keyLable");
                    lableValue.getStyleClass().add("valueLable");
                    hBox.getChildren().addAll(labelKey,lableValue);

                    if(count <= 5) {
                        listOfWeatherData.add(hBox);
                    }else{
                        listOfWeatherData1.add(hBox);
                    }
                }
                weatherDataInfo.getChildren().addAll(listOfWeatherData);
                weatherDataInfo1.getChildren().addAll(listOfWeatherData1);

            }
        };
        EventHandler<InputEvent> eventHandlerForLatLong = new EventHandler<InputEvent>() {
            @Override
            public void handle(InputEvent event) {
                clearWeatherData();
                mapRegion.findWeatherByLatLng(lng.getText(), lat.getText(), "c");
                Set set = mapRegion.getWeatherData().entrySet();
                Iterator iterator = set.iterator();
                int count = 0;
                while (iterator.hasNext()){
                    count++;
                    HBox hBox = new HBox();
                    Map.Entry info = (Map.Entry)iterator.next();
                    Label labelKey = new Label((String)info.getKey());
                    Label lableValue = new Label((String) info.getValue());
                    hBox.getStylesheets().add("css/weather_demo.css");
                    labelKey.getStyleClass().add("keyLable");
                    lableValue.getStyleClass().add("valueLable");
                    hBox.getChildren().addAll(labelKey,lableValue);
                    if(count <= 5) {
                        listOfWeatherData.add(hBox);
                    }else{
                        listOfWeatherData1.add(hBox);
                    }
                }
                weatherDataInfo.getChildren().addAll(listOfWeatherData);
                weatherDataInfo1.getChildren().addAll(listOfWeatherData1);

            }
        };
        citySearch.setOnMouseClicked(eventHandler);
        latLngSearch.setOnMouseClicked(eventHandlerForLatLong);
    }


    public void defaultWeatherDataInfo(){
        mapRegion.findWeatherByLocation("London", "c");
        weatherDataInfo.getChildren().removeAll();
        Set set = mapRegion.getWeatherData().entrySet();
        Iterator iterator = set.iterator();
        int count = 0;
        while (iterator.hasNext()){
            count++;
            HBox hBox = new HBox();
            Map.Entry info = (Map.Entry)iterator.next();
            Label labelKey = new Label((String)info.getKey());
            Label lableValue = new Label((String) info.getValue());
            hBox.getStylesheets().add("css/weather_demo.css");
            labelKey.getStyleClass().add("keyLable");
            lableValue.getStyleClass().add("valueLable");
            hBox.getChildren().addAll(labelKey,lableValue);
            if(count <= 5) {
                weatherDataInfo.getChildren().add(hBox);
            }else{
                weatherDataInfo1.getChildren().add(hBox);
            }
        }

    }

    public void clearWeatherData(){
        LOG.debug("in Clear data : Size: "+weatherDataInfo.getChildren().size()+" : "+weatherDataInfo1.getChildren().size());
        weatherDataInfo.getChildren().clear();
        weatherDataInfo1.getChildren().clear();
        LOG.debug("in Clear data : Size: After : "+weatherDataInfo.getChildren().size()+" : "+weatherDataInfo1.getChildren().size());

    }

    public void weatherDataFromMap(String lon,String lats){
        LOG.debug("FROM MAP: ");
        clearWeatherData();
        mapRegion.findWeatherByLatLng(lon, lats, "c");
        Set set = mapRegion.getWeatherData().entrySet();
        Iterator iterator = set.iterator();
        int count = 0;
        while (iterator.hasNext()){
            count++;
            HBox hBox = new HBox();
            Map.Entry info = (Map.Entry)iterator.next();
            Label labelKey = new Label((String)info.getKey());
            Label lableValue = new Label((String) info.getValue());
            hBox.getStylesheets().add("css/weather_demo.css");
            labelKey.getStyleClass().add("keyLable");
            lableValue.getStyleClass().add("valueLable");
            hBox.getChildren().addAll(labelKey,lableValue);
            if(count <= 5) {
                weatherDataInfo.getChildren().add(hBox);
            }else{
                weatherDataInfo1.getChildren().add(hBox);
            }
        }
        LOG.debug("End MAP: ");
    }
}
