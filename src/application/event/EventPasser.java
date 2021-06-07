package application.event;

import application.Monopoly;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;
import joueur.Joueur;

import java.util.ArrayList;

public class EventPasser implements EventHandler<ActionEvent> {

    private Monopoly monopoly;

    public EventPasser(Monopoly monopoly) {
        this.monopoly = monopoly;
    }

    @Override
    public void handle(ActionEvent e) {
        // TODO Verifier lorsqu'il y a eu double, ou prison
        ArrayList<Joueur> lesJoueurs = monopoly.getListeJoueurs();
        Joueur jc = monopoly.getJoueurCourant();
        int i = lesJoueurs.indexOf(jc);
        int suivant = (i + 1) % lesJoueurs.size();

        ToggleButton button = monopoly.getTabBoutonsJoueurs().get(suivant);
        button.fire();
        monopoly.setValueTfPorteMonnaie(monopoly.getJoueurCourant().getCapitalJoueur());
    }

}
