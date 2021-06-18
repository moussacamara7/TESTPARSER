package application.event;

import application.Monopoly;
import application.ui.Pion;
import carte.*;
import exception.MonopolyException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import joueur.Joueur;
import mecanismeJeu.Action;
import terrain.*;


public class EventJouer implements EventHandler<ActionEvent> {

    public static final int TAXE_DE_LUXE = 100;
    public static final int TAXE_SUR_LE_REVENU = 200;
    private final Monopoly monopoly;


    /**
     * Constructeur EventJouer
     * @param monopoly instance monomoply
     */
    public EventJouer(Monopoly monopoly) {
        this.monopoly = monopoly;
    }

    /**
     * gere le tour d'un joueur
     * @param event evenement bouton lancer les des
     */
    @Override
    public void handle(ActionEvent event) {


        if (monopoly.isTourTermine()) {
            monopoly.DialogInfo("Ton tour est fini " + monopoly.getJoueurCourant().getNomJoueur() + ", gère tes terrains ou passe.");
            return;
        }

        String tfDe1 = monopoly.getTfValeurDe1().getText();
        String tfDe2 = monopoly.getTfValeurDe2().getText();

        int de1, de2;

        if (!tfDe1.trim().isEmpty() && !tfDe2.trim().isEmpty()) {

            de1 = Integer.parseInt(tfDe1);
            de2 = Integer.parseInt(tfDe2);
            monopoly.getMessageFooter().setText("");
        } else {
            monopoly.getMessageFooter().setText("");
            de1 = Action.lancerDe();
            de2 = Action.lancerDe();
            monopoly.setTfDe1(String.valueOf(de1));
            monopoly.setTfDe2(String.valueOf(de2));
        }
        int nbCases = de1 + de2;

        Joueur joueur = monopoly.getJoueurCourant();

        //System.out.println("d1=" + de1 + "  d2=" + de2 + "  nb cases=" + nbCases);

        if (!joueur.isEstprisonnier()) {

            if (de1 == de2) {
                int nbDbl = monopoly.getNbDoubles();

                nbDbl++;
                monopoly.setNbDoubles(nbDbl);
                if (nbDbl == 1) {
                    monopoly.getMessageFooter().setText("C'est ton premier double !");
                    Action.avancerJoueur(joueur, nbCases);
                } else if (nbDbl == 2) {
                    monopoly.getMessageFooter().setText("C'est ton deuxième double !! Encore un et c'est la taule...");
                    Action.avancerJoueur(joueur, nbCases);

                } else {
                    monopoly.getMessageFooter().setText("Police, menottes, prison...");
                    Action.allerEnPrison(joueur);
                    monopoly.setTourTermine(true);
                    try {
                        monopoly.setNbDoubles(0);
                    } catch (Exception e) {
                        monopoly.DialogAction(e.getMessage(), true);
                    }
                }
            } else {
                monopoly.setNbDoubles(0);
                Action.avancerJoueur(joueur, nbCases);
                monopoly.setTourTermine(true);
            }

        } else {
            //prisonnier
            if (joueur.getNombreDeTourEnPrison() == 3 && (de1 != de2)) {
                monopoly.getMessageFooter().setText("C'est ton troisième tour en prison, tu sors et tu payes 50.");
                Action.retirer(50, joueur);
                Action.sortirDePrison(joueur);
                Action.avancerJoueur(joueur, nbCases);
                monopoly.setTourTermine(true);
            } else {
                if (de1 == de2) {
                    monopoly.getMessageFooter().setText("Un double ! Vous sortez de prison !");
                    Action.sortirDePrison(joueur);
                    joueur.setNombreDeTourEnPrison(0);
                    Action.avancerJoueur(joueur, nbCases);
                    monopoly.setNbDoubles(monopoly.getNbDoubles() + 1);
                } else {
                    monopoly.getMessageFooter().setText("Pas de double... un tour de plus en prison.");
                    joueur.setNombreDeTourEnPrison(joueur.getNombreDeTourEnPrison() + 1);
                    monopoly.setTourTermine(true);
                }

            }
        }

        repositionnerPion();

        Terrain t = joueur.getUIPlateau().getCaseP(joueur.getPositionJoueur());
        t.action(joueur);

        repositionnerPion();
        //on mets a jour son porte monnaie
        monopoly.setValueTfPorteMonnaie(joueur.getCapitalJoueur());



    }

    /**
     * Fonction permettant de repositionner le pion des joueurs sur l'interface
     */
    private void repositionnerPion(){
        Joueur joueur = monopoly.getJoueurCourant();
        int numJoueurCourant = monopoly.getUiPlateau().getListeJoueurs().indexOf(joueur);
        Pion pionJoueurCourant = monopoly.getListePions().get(numJoueurCourant);
        try {
            monopoly.getUiPlateau().getCase(pionJoueurCourant.getPosition()).enlever(pionJoueurCourant);
        } catch (Exception e) {
            e.printStackTrace();
        }
        pionJoueurCourant.setPosition(joueur.getPositionJoueur());
        monopoly.getUiPlateau().getCase(pionJoueurCourant.getPosition()).poser(pionJoueurCourant);
        monopoly.getUiPlateau().dessiner(monopoly.getGrillePane());
    }
}
