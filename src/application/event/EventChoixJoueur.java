package application.event;

import application.Monopoly;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;
import joueur.Joueur;

public class EventChoixJoueur implements EventHandler<ActionEvent> {

    private final Monopoly monopoly;


    /**
     * Constructeur de EventChoixJoueur
     * @param monopoly instance monopoly
     */
    public EventChoixJoueur(Monopoly monopoly) {
        this.monopoly = monopoly;
    }

    /**
     * Gere la selection des joueurs
     * @param e evenement changement de joueur
     */
    @Override
    public void handle(ActionEvent e) {
        if (e.getSource() instanceof ToggleButton) {
            ToggleButton b = (ToggleButton) e.getSource();
            Joueur j = (Joueur) b.getUserData();

            monopoly.setJoueurCourant(j);
            System.out.println(monopoly.getJoueurCourant() + " doit jouer");

            monopoly.getZoneProprietes().getItems().clear();
        }
    }

}
