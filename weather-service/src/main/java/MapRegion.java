import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.net.URL;

/**
 * Created by webonise on 16-03-2015.
 */
public class MapRegion extends VBox {

    @FXML
    private VBox mapRegion;

    @FXML
    private WebView webView;

    @FXML
    private VBox map;

    public MapRegion(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/MapRegion.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException ex) {
            throw new RuntimeException("Error loading MapsRegion: " + ex.getMessage(), ex);
        }

        final URL urlMap = getClass().getClassLoader().getResource("html/mapregion.html");
        WebEngine webEngine=webView.getEngine();
        webEngine.setJavaScriptEnabled(true);
        webEngine.load(urlMap.toExternalForm());
        map.setVisible(true);
    }



}
