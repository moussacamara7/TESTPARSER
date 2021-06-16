package application.event;

import application.FenetreTerrain;
import application.Monopoly;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class EventGererMaison implements EventHandler<ActionEvent> {

    private final Monopoly monopoly;

    /**
     * Constructeur EventGererMaison
     * @param monopoly instance monopoly
     */
    public EventGererMaison(Monopoly monopoly) {
        this.monopoly = monopoly;
    }

    /**
     * Ouvre une fenetre de gestion des maisons si un terrain est selectionne dans la liste
     * @param e evenement bouton gestion de maisons et hotel
     */
    @Override
    public void handle(ActionEvent e) {
        if (monopoly.getTerrainSelectionne() == -1)
            monopoly.DialogAction("Tu n'as sélectionné aucun terrain !", true);
        else {
            new FenetreTerrain(monopoly);
        }
    }

}
