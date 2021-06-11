package application.event;

import application.Monopoly;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import mecanismeJeu.Action;


public class EventLiberation implements EventHandler<ActionEvent> {

    private final Monopoly monopoly;

    public EventLiberation(Monopoly monopoly) {
        this.monopoly = monopoly;
    }

    @Override
    public void handle(ActionEvent e) {
        if (!monopoly.getJoueurCourant().isEstprisonnier()) {
            monopoly.DialogInfo("Vous n'êtes pas en prison !");
        } else {
            if (monopoly.getJoueurCourant().getCarteSortirDePrison() == 0) {
                monopoly.DialogInfo("Vous n'avez pas de carte libération !");
            } else {
                monopoly.getJoueurCourant().setCarteSortirDePrison(monopoly.getJoueurCourant().getCarteSortirDePrison() - 1);
                Action.sortirDePrison(monopoly.getJoueurCourant());
                monopoly.DialogInfo("Bon retour dans la plus grande prison !");
            }
        }
    }

}