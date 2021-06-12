package application.event.eventChangerJoueur;

import application.FenetreChangerJoueur;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class EventValider implements EventHandler<ActionEvent> {
    private final FenetreChangerJoueur fenetreChangerJoueur;

    public EventValider(FenetreChangerJoueur fenetreChangerJoueur) {
        this.fenetreChangerJoueur = fenetreChangerJoueur;
    }

    @Override
    public void handle(ActionEvent event) {
        if (fenetreChangerJoueur.getTempNouveauxJoueurs().size() < 2)
            fenetreChangerJoueur.getMonopoly().DialogInfo("Il faut au moins deux joueurs !!");
        else {
            fenetreChangerJoueur.getMonopoly().getNouveauxJoueurs().clear();
            fenetreChangerJoueur.getMonopoly().getNouveauxJoueurs().putAll(fenetreChangerJoueur.getTempNouveauxJoueurs());
            fenetreChangerJoueur.getMonopoly().redemarrerPartie(fenetreChangerJoueur.getMonopoly().getPrimaryStage());
        }
    }
}
