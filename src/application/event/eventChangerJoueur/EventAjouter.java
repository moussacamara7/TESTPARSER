package application.event.eventChangerJoueur;

import application.FenetreChangerJoueur;
import application.ui.nomPion;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;
import java.util.Arrays;


public class EventAjouter implements EventHandler<ActionEvent> {
    FenetreChangerJoueur fenetreChangerJoueur;

    /**
     * Constructeur de EventAjouter
     * @param fenetreChangerJoueur la fenetreChangerJoueur
     */
    public EventAjouter(FenetreChangerJoueur fenetreChangerJoueur) {
        this.fenetreChangerJoueur = fenetreChangerJoueur;
    }


    /**
     * La fonction qui permet d'ajouter un joueur depuis la fenetre d'ajouts
     * @param event evenement bouton ajouter
     */
    @Override
    public void handle(ActionEvent event) {
        if (fenetreChangerJoueur.getNomJoueur().getText().trim().isEmpty())
            fenetreChangerJoueur.getMonopoly().DialogInfo("Vous n'avez pas saisi de nom !");
        else if (fenetreChangerJoueur.getPionSelectionne() == -1)
            fenetreChangerJoueur.getMonopoly().DialogInfo("Vous n'avez pas choisi de pion !");
        else {
            String nomJoueur = fenetreChangerJoueur.getNomJoueur().getText();
            ArrayList<nomPion> listPion = new ArrayList<>(Arrays.asList(nomPion.values()));
            nomPion p = listPion.get(fenetreChangerJoueur.getPionSelectionne());
            fenetreChangerJoueur.getTempNouveauxJoueurs().put(nomJoueur, p);
            fenetreChangerJoueur.getNomJoueur().setText("");
            fenetreChangerJoueur.updateLabelnbNouveauxJoueurs();
        }
    }
}
