package application.event;

import application.Monopoly;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import mecanismeJeu.Action;


public class EventPayerPrison implements EventHandler<ActionEvent> {
    private static final int SOMME_SORTIR_DE_PRISON = 50;

    private final Monopoly monopoly;

    public EventPayerPrison(Monopoly monopoly) {
        this.monopoly = monopoly;
    }

    @Override
    public void handle(ActionEvent e) {
//        if (!monopoly.getJoueurCourant().isEstprisonnier()) {
//            monopoly.DialogInfo("Vous n'êtes pas en prison !");
//        } else {
//            if (monopoly.getJoueurCourant().getCapitalJoueur() < SOMME_SORTIR_DE_PRISON) {
//                monopoly.DialogInfo("Vous êtes fauché, dommage.");
//            } else {
//                Action.retirer(SOMME_SORTIR_DE_PRISON, monopoly.getJoueurCourant());
//                Action.sortirDePrison(monopoly.getJoueurCourant());
//                monopoly.setValueTfPorteMonnaie(monopoly.getJoueurCourant().getCapitalJoueur());
//                monopoly.DialogInfo("Bon retour dans la plus grande prison !");
//            }
//        }
//
        Action.retirer(1600,monopoly.getJoueurCourant());
    }

}