package org.mrudula;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by webonise on 16-03-2015.
 */
public class WeatherServiceApp extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(WeatherServicesAppConfig.class);
        WeatherController weatherController = context.getBean(WeatherController.class);
        weatherController.launch(stage);
    }
}