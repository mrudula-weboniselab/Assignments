import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import region.UIRegion;

/**
 * Created by webonise on 16-03-2015.
 */
public class WeatherServiceApp extends Application{
    private WebView webView;

    UIRegion uiRegion;
    public static final String WEATHER_URL ="http://api.openweathermap.org/data/2.5/weather";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Weather Application");
        GridPane gridPane = new GridPane();

        MapRegion mapBox = new MapRegion();
        gridPane.add(mapBox,0,0,1,2);
        uiRegion = new UIRegion();
        gridPane.add(uiRegion,0,2);
        Scene scene = new Scene(gridPane);

        stage.setScene(scene);
        stage.show();

    }
}
