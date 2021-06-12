package application.event;

import application.Monopoly;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class EventRedemarrer implements EventHandler<ActionEvent> {
    private Monopoly monopoly;

    public EventRedemarrer(Monopoly monopoly){
        this.monopoly = monopoly;
    }
    @Override
    public void handle(ActionEvent event) {
        if(DialogRedemarrer())
            monopoly.redemarrerPartie(monopoly.getPrimaryStage());

    }

    private boolean DialogRedemarrer() {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Redemarrer la partie");
        alert.setContentText("Êtes vous sûr ?");
        ButtonType oui = new ButtonType("Oui");
        ButtonType non = new ButtonType("Non");
        alert.getButtonTypes().setAll(oui, non);
        Optional<ButtonType> result = alert.showAndWait();

        return result.get().equals(oui);

    }
}
