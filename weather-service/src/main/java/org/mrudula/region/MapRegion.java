package org.mrudula.region;

import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;
import org.mrudula.utils.WeatherQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

/**
 * Created by webonise on 16-03-2015.
 */
@Component
public class MapRegion extends VBox {
    private static final Logger LOG = LoggerFactory.getLogger(MapRegion.class);

    @Autowired
    private WeatherQuery weatherQuery;

    @FXML
    private VBox mapRegion;
    @FXML
    private WebView webView;
    @FXML
    private VBox map;

    JSObject jsObject;

    public MapRegion(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/MapRegion.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
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

    public void findWeatherFromMap(String lon,String lat, String unitType) {
            weatherQuery.findWeatherFromMap(lon,lat,unitType,jsObject);
    }

}
