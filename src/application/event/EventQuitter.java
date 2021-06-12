package application.event;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class EventQuitter implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent e) {
        if(DialogQuitter()) {
            Platform.exit();
            System.exit(0);
        }
    }

    private boolean DialogQuitter() {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Quitter la partie");
        alert.setContentText("Êtes vous sûr ?");
        ButtonType oui = new ButtonType("Oui");
        ButtonType non = new ButtonType("Non");
        alert.getButtonTypes().setAll(oui, non);
        Optional<ButtonType> result = alert.showAndWait();

        return result.get().equals(oui);

    }

}
