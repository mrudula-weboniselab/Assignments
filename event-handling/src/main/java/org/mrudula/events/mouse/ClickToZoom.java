package org.mrudula.events.mouse;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by webonise on 31-03-2015.
 */
public class ClickToZoom implements EventHandler<MouseEvent> {
    private static final Logger LOG = LoggerFactory.getLogger(ClickToZoom.class);

    @Override public void handle(final MouseEvent event) {
        if (event.getSource() instanceof Node) {
            final Node n = (Node) event.getSource();
            switch (event.getButton()) {
                case PRIMARY:
                    n.setScaleX(n.getScaleX()*1.1);
                    n.setScaleY(n.getScaleY()*1.1);
                    break;
                case SECONDARY:
                    n.setScaleX(n.getScaleX()/1.1);
                    n.setScaleY(n.getScaleY()/1.1);
                    break;
            }
        }
    }
}
