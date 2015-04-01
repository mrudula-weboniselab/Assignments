package org.mrudula;

import javafx.application.Application;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by webonise on 31-03-2015.
 */
public class MainEventHandlerApp extends Application {
    private static final Logger LOG = LoggerFactory.getLogger(MainEventHandlerApp.class);

    //public static void main(String[] args) { launch(args); }

    @Override
    public void start(final Stage stage) throws Exception {
        LOG.info("Starting ....");
        ApplicationContext context = new AnnotationConfigApplicationContext(MainEventHandlerAppConfiguration.class);
        MainEventController mainEventController = context.getBean( MainEventController.class);
        mainEventController.launch(stage);
    }
}