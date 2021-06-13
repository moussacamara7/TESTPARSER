package application.event.eventChangerJoueur;

import application.FenetreChangerJoueur;
import application.Monopoly;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class EventChangerJoueur implements EventHandler<ActionEvent> {
    private final Monopoly monopoly;

    public EventChangerJoueur(Monopoly monopoly) {
        this.monopoly = monopoly;
    }

    @Override
    public void handle(ActionEvent event) {
        new FenetreChangerJoueur(monopoly);
    }
}
