package application.event;

import application.Monopoly;
import application.FenetreChangerJoueur;
import application.ui.nomPion;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.HashMap;

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
