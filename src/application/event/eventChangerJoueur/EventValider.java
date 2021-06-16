package application.event.eventChangerJoueur;

import application.FenetreChangerJoueur;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class EventValider implements EventHandler<ActionEvent> {
    private final FenetreChangerJoueur fenetreChangerJoueur;

    /**
     * Constructeur de EventValider
     * @param fenetreChangerJoueur la fenetre pour changer les joueurs
     */
    public EventValider(FenetreChangerJoueur fenetreChangerJoueur) {
        this.fenetreChangerJoueur = fenetreChangerJoueur;
    }

    /**
     * Remdarre une partie avec les joueurs creer dans la fenetre de creation de joueurs
     * verifie qu'il y ait au moins deux joueurs
     * @param event evenement bouton valider
     */
    @Override
    public void handle(ActionEvent event) {
        if (fenetreChangerJoueur.getTempNouveauxJoueurs().size() < 2)
            fenetreChangerJoueur.getMonopoly().DialogInfo("Il faut au moins deux joueurs !!");
        else {
            fenetreChangerJoueur.getMonopoly().getNouveauxJoueurs().clear();
            fenetreChangerJoueur.getMonopoly().getNouveauxJoueurs().putAll(fenetreChangerJoueur.getTempNouveauxJoueurs());
            fenetreChangerJoueur.getMonopoly().redemarrerPartie();
        }
    }
}
