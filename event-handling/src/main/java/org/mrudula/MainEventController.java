package org.mrudula;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by webonise on 31-03-2015.
 */
@Component
public class MainEventController extends GridPane {
    private static final Logger LOG = LoggerFactory.getLogger(MainEventController.class);

    Stage stage;
    Scene scene = new Scene(this,450,200);
    Button keyEventButton = new Button("Key Event Handling");
    Button mouseEventButton = new Button("Mouse Event Handling");
    Button actionEventButton = new Button("Action Event Handling");
    TextArea outputTextArea = new TextArea();

    public void launch(Stage primaryStage) {
        stage = primaryStage;
        this.setHgap(10);
        this.setVgap(10);
        add(keyEventButton,1,0);
        add(mouseEventButton,4,0);
        add(actionEventButton,2,2);
        add(outputTextArea,1,3,4,3);
        this.setPadding(new Insets(30,0,10,0));
        primaryStage.setTitle("EVENT HANDLING EXAMPLES");
        //Scene scene = new Scene(this,450,200);

        primaryStage.setScene(scene);
        primaryStage.show();

        init();
    }

    public void init(){

        EventHandler<InputEvent> keyEventHandler = new EventHandler<InputEvent>() {
            @Override
            public void handle(InputEvent event) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Press Any Keys From Keyboard !");
                alert.showAndWait();
                scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        outputTextArea.appendText("key pressed : "+event.getCode().getName()+"\n");
                    }
                });

            }
        };

        keyEventButton.setOnMouseClicked(keyEventHandler);

    }


}
