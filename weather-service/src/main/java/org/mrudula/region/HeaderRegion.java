package org.mrudula.region;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by webonise on 02-04-2015.
 */
@Component
public class HeaderRegion extends HBox {

    public HeaderRegion() {
        super();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/HeaderRegion.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException ex) {
            throw new RuntimeException("Error loading HeaderRegion: " + ex.getMessage(), ex);
        }
    }

}
