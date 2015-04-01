package org.mrudula;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.mrudula.events.KeyEventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by webonise on 31-03-2015.
 */
@Component
public class MainEventController extends GridPane {
    private static final Logger LOG = LoggerFactory.getLogger(MainEventController.class);

    Button keyEventButton = new Button("Key Event Handling");
    Button mouseEventButton = new Button("Mouse Event Handling");
    Button actionEventButton = new Button("Action Event Handling");

    public void launch(Stage primaryStage) {

        this.setHgap(10);
        this.setVgap(10);
        add(keyEventButton,1,0);
        add(mouseEventButton,4,0);
        add(actionEventButton,2,2);
        this.setPadding(new Insets(30,0,10,0));

        primaryStage.setTitle("EVENT HANDLING EXAMPLES");

        Scene scene = new Scene(this,450,200);
        primaryStage.setScene(scene);
        primaryStage.show();

        init();
    }

    public void init(){

        EventHandler<InputEvent> keyEventHandler = new EventHandler<InputEvent>() {
            @Override
            public void handle(InputEvent event) {

                Application.launch(KeyEventHandler.class);
            }
       };

        keyEventButton.setOnMouseClicked(keyEventHandler);
    }


}
