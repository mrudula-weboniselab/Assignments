package region;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by webonise on 16-03-2015.
 */

public class UIRegion extends VBox {

    @FXML
    private TextField cityName;

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




    }

    public void urlEncoder(){

        String location = cityName.getText();
        try {
            String encodedUrl = URLEncoder.encode(location, "UTF-8");

        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        }
    }
}
