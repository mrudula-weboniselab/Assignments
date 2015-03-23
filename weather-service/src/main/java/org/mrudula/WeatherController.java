package org.mrudula;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.mrudula.region.MapRegion;
import org.mrudula.region.UIRegion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by webonise on 20-03-2015.
 */
@Component
public class WeatherController extends BorderPane {

    @Autowired
    private MapRegion mapRegion;

    @Autowired
    private UIRegion uiRegion;

    public void launch(Stage stage) {
        stage.setTitle("Weather Application");
        this.setTop(mapRegion);
        Label title = new Label("Weather Report");
        title.getStylesheets().add("css/weather_demo.css");
        title.getStyleClass().add("headerLable");
        this.setCenter(title);
        this.setBottom(uiRegion);
        Scene scene = new Scene(this,1200,800);
        stage.setScene(scene);
        stage.show();
    }


}
