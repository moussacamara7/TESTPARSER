package application.event;

import application.Monopoly;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import mecanismeJeu.Action;


public class EventPayerPrison implements EventHandler<ActionEvent> {
    private static final int SOMME_SORTIR_DE_PRISON = 50;

    private final Monopoly monopoly;

    /**
     * Constructeur EventPayerPrison
     * @param monopoly instance monomoply
     */
    public EventPayerPrison(Monopoly monopoly) {
        this.monopoly = monopoly;
    }

    /**
     * Fonction permettant au joueur de payer pour sortir de prison
     * fait toutes les verifications necaissaires
     * @param e evenement bouton payer pour sortir de prison
     */
    @Override
    public void handle(ActionEvent e) {

        if (!monopoly.getJoueurCourant().isEstprisonnier()) {
            monopoly.DialogInfo("Vous n'êtes pas en prison !");
        } else {
            if (monopoly.isTourTermine()) {
                monopoly.DialogInfo("Ton tour est fini " + monopoly.getJoueurCourant().getNomJoueur() + ", gère tes terrains ou passe.");
            } else if (monopoly.getJoueurCourant().getCapitalJoueur() < SOMME_SORTIR_DE_PRISON) {
                monopoly.DialogInfo("Vous êtes fauché, dommage.");
            } else {
                Action.retirer(SOMME_SORTIR_DE_PRISON, monopoly.getJoueurCourant());
                Action.sortirDePrison(monopoly.getJoueurCourant());
                monopoly.setValueTfPorteMonnaie(monopoly.getJoueurCourant().getCapitalJoueur());
                monopoly.DialogInfo("Bon retour dans la plus grande prison !");
            }
        }

        //utiliser pour tester la faillite
        //Action.retirer(1600,monopoly.getJoueurCourant());
    }

}