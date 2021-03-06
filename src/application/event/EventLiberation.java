package application.event;

import application.Monopoly;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import mecanismeJeu.Action;


public class EventLiberation implements EventHandler<ActionEvent> {

    private final Monopoly monopoly;

    /**
     * Constructeur EventLiberation
     * @param monopoly instance monomoply
     */
    public EventLiberation(Monopoly monopoly) {
        this.monopoly = monopoly;
    }

    /**
     * gere l'utilisation d'une carte liberation
     * fait toutes les verifications necaissaires
     * @param e evenement bouton utiliser une carte liberation
     */
    @Override
    public void handle(ActionEvent e) {
        if (!monopoly.getJoueurCourant().isEstprisonnier()) {
            monopoly.DialogInfo("Vous n'êtes pas en prison !");
        } else {
            if(monopoly.isTourTermine()){
                monopoly.DialogInfo("Ton tour est fini " + monopoly.getJoueurCourant().getNomJoueur() + ", gère tes terrains ou passe.");
            }else if (monopoly.getJoueurCourant().getCarteSortirDePrison() == 0) {
                monopoly.DialogInfo("Vous n'avez pas de carte libération !");
            } else {
                monopoly.getJoueurCourant().setCarteSortirDePrison(monopoly.getJoueurCourant().getCarteSortirDePrison() - 1);
                Action.sortirDePrison(monopoly.getJoueurCourant());
                monopoly.DialogInfo("Bon retour dans la plus grande prison !");
            }
        }
    }

}