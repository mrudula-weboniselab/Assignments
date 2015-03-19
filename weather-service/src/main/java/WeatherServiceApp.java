import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import region.MapRegion;
import region.UIRegion;

/**
 * Created by webonise on 16-03-2015.
 */
public class WeatherServiceApp extends Application{

     public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Weather Application");
        BorderPane borderPane = new BorderPane();
        MapRegion mapRegion = new MapRegion();
        borderPane.setTop(mapRegion);
        Label title = new Label("Weather Report");
        title.getStylesheets().add("css/weather_demo.css");
        title.getStyleClass().add("headerLable");
        borderPane.setCenter(title);
        UIRegion uiRegion = new UIRegion();
        borderPane.setBottom(uiRegion);
        Scene scene = new Scene(borderPane,1200,800);
        stage.setScene(scene);
        stage.show();

    }
}