package application.event.eventChangerJoueur;

import application.FenetreChangerJoueur;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class EventAnnuler implements EventHandler<ActionEvent> {
    FenetreChangerJoueur fenetreChangerJoueur;

    EventAnnuler(FenetreChangerJoueur fenetreChangerJoueur){
        this.fenetreChangerJoueur = fenetreChangerJoueur;
    }

    @Override
    public void handle(ActionEvent event) {
        fenetreChangerJoueur.getMonopoly().getNouveauxJoueurs().clear();
        fenetreChangerJoueur.close();
    }
}
