package application.event;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class EventQuitter implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent e) {
        Platform.exit();
        System.exit(0);
    }

}
