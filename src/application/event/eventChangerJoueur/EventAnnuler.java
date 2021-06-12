package application.event.eventChangerJoueur;

import application.FenetreChangerJoueur;
import application.ui.nomPion;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.HashMap;

public class EventAnnuler implements EventHandler<ActionEvent> {
    FenetreChangerJoueur fenetreChangerJoueur;

    public EventAnnuler(FenetreChangerJoueur fenetreChangerJoueur){
        this.fenetreChangerJoueur = fenetreChangerJoueur;
    }

    @Override
    public void handle(ActionEvent event) {
        fenetreChangerJoueur.close();
    }
}
