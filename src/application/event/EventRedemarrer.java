package application.event;

import application.Monopoly;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class EventRedemarrer implements EventHandler<ActionEvent> {
    private final Monopoly monopoly;

    /**
     * Constructeur EventRedemarrer
     *
     * @param monopoly instance monopoly
     */
    public EventRedemarrer(Monopoly monopoly) {
        this.monopoly = monopoly;
    }

    /**
     * Ouvre une boite de dialogue demandant si le joueur veut vraiment redemarrer
     * Redemarre une partie si DialogRedemarrer est vrai
     *
     * @param event evenement bouton redemarrer
     */
    @Override
    public void handle(ActionEvent event) {
        if (DialogRedemarrer())
            monopoly.redemarrerPartie();

    }

    /**
     * @return vrai si le joueur appuie sur oui
     */
    private boolean DialogRedemarrer() {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Redémarrer la partie");
        alert.setContentText("Êtes vous sûr ?");
        ButtonType oui = new ButtonType("Oui");
        ButtonType non = new ButtonType("Non");
        alert.getButtonTypes().setAll(oui, non);
        Optional<ButtonType> result = alert.showAndWait();

        return result.get().equals(oui);

    }
}
