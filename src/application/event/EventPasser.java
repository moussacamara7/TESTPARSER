package application.event;

import application.Monopoly;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;
import joueur.Joueur;

import java.util.ArrayList;

public class EventPasser implements EventHandler<ActionEvent> {

    private final Monopoly monopoly;

    public EventPasser(Monopoly monopoly) {
        this.monopoly = monopoly;
    }

    @Override
    public void handle(ActionEvent e) {
        if (monopoly.isTourTermine()) {

            monopoly.setTourTermine(false);
            monopoly.setNbDoubles(0);
            ArrayList<Joueur> lesJoueurs = monopoly.getUiPlateau().getListeJoueurs();
            Joueur jc = monopoly.getJoueurCourant();
            int i = lesJoueurs.indexOf(jc);
            int suivant = (i + 1) % lesJoueurs.size();

            ToggleButton button = monopoly.getTabBoutonsJoueurs().get(suivant);
            button.fire();

            monopoly.setValueTfPorteMonnaie(monopoly.getJoueurCourant().getCapitalJoueur());
            monopoly.updateProprieteJoueurCourant();
            monopoly.setTerrainSelectionne(-1);
        } else {
            monopoly.DialogInfo("Ton tour n'est pas terminé " + monopoly.getJoueurCourant().getNomJoueur() + " ! Lance les dés.");
        }
    }

}
