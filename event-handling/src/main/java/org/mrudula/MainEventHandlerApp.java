package org.mrudula;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.mrudula.events.mouse.ClickToZoom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Created by webonise on 31-03-2015.
 */
public class MainEventHandlerApp extends Application {
    private static final Logger LOG = LoggerFactory.getLogger(MainEventHandlerApp.class);
    public static void main(String[] args) { launch(args); }
    @Override
    public void start(final Stage stage) throws Exception {
        stage.setTitle("Left click to zoom in, right click to zoom out");
        ImageView imageView = new ImageView("/images/images.jpg");
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(150);
        imageView.setOnMouseClicked(new ClickToZoom());
        final StackPane layout = new StackPane();
        layout.getChildren().addAll(imageView);
        layout.setStyle("-fx-background-color: LightCyan;");
        stage.setScene(new Scene(layout, 400, 500));
        stage.show();
    }
}