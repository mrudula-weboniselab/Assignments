package org.mrudula;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.mrudula.region.MapRegion;
import org.mrudula.region.UIRegion;
import org.mrudula.utils.WeatherQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by webonise on 20-03-2015.
 */
@Component
public class WeatherController extends BorderPane {
    private static final Logger LOG = LoggerFactory.getLogger(WeatherController.class);

    @Autowired
    private MapRegion mapRegion;

    @Autowired
    private UIRegion uiRegion;

    @Autowired
    private WeatherQuery weatherQuery;

   // WeatherQuery weatherQuery = new WeatherQuery();

    @PostConstruct
    public void init() {
        layoutRegions();
    }

    public void launch(Stage stage) {
        stage.setTitle("Weather Application");
        Scene scene = new Scene(this,1200,800);
        stage.setScene(scene);
        stage.show();
    }

    private void layoutRegions() {
        this.setTop(mapRegion);
        Label title = new Label("Weather Report");
        title.getStylesheets().add("css/weather_demo.css");
        title.getStyleClass().add("headerLable");
        this.setCenter(title);
        this.setBottom(uiRegion);
    }
}
