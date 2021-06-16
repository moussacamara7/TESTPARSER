package application.event.eventChangerJoueur;

import application.FenetreChangerJoueur;
import application.Monopoly;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class EventChangerJoueur implements EventHandler<ActionEvent> {
    private final Monopoly monopoly;

    /**
     * Constructeur de EventChangerJoueur
     * @param monopoly l'instance monoply
     */
    public EventChangerJoueur(Monopoly monopoly) {
        this.monopoly = monopoly;
    }

    /**
     * creer une FenetreChangerJoueur
     * @param event evenement bouton nouveaux joueurs
     */
    @Override
    public void handle(ActionEvent event) {
        new FenetreChangerJoueur(monopoly);
    }
}
