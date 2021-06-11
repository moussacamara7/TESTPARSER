package application.event;

import application.Monopoly;
import application.ui.Pion;
import carte.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import joueur.Joueur;
import mecanismeJeu.Action;
import terrain.*;

import java.util.concurrent.TimeUnit;


public class EventJouer implements EventHandler<ActionEvent> {

    private final Monopoly monopoly;
    public static final int TAXE_DE_LUXE = 100;
    public static final int TAXE_SUR_LE_REVENU = 200;


    public EventJouer(Monopoly monopoly) {
        this.monopoly = monopoly;
    }

    @Override
    public void handle(ActionEvent event) {



        String tfDe1 = monopoly.getTfValeurDe1().getText();
        String tfDe2 = monopoly.getTfValeurDe2().getText();

        int de1, de2;

        if(! tfDe1.trim().isEmpty() && ! tfDe2.trim().isEmpty()) {

            de1 = Integer.parseInt(tfDe1);
            de2 = Integer.parseInt(tfDe2);

        }else{
            monopoly.getMessageFooter().setText("");
            de1 = Action.lancerDe();
            de2 = Action.lancerDe();
            monopoly.setTfDe1(String.valueOf(de1));
            monopoly.setTfDe2(String.valueOf(de2));
        }

        int nbCases = de1 + de2;

        Joueur joueur = monopoly.getJoueurCourant();

        System.out.println("d1=" + de1 + "  d2=" + de2 + "  nb cases=" + nbCases);

        if(! joueur.isEstprisonnier()){

            if (de1 == de2) {
                int nbDbl = monopoly.getNbDoubles();

                nbDbl++;
                monopoly.setNbDoubles(nbDbl);
                if (nbDbl == 1){
                    monopoly.getMessageFooter().setText("C'est ton premier double !");
                    Action.avancerJoueur(joueur, nbCases);
                }else if (nbDbl == 2) {
                    monopoly.getMessageFooter().setText("C'est ton deuxième double !! Encore un et c'est la taule...");
                    Action.avancerJoueur(joueur, nbCases);

                }else {
                    monopoly.getMessageFooter().setText("Police, menottes, prison...");
                    Action.allerEnPrison(joueur);
                    try {
                        monopoly.setNbDoubles(0);
                    } catch (Exception e) {
                        monopoly.DialogAction(e.getMessage(), true);
                    }
                }
            } else {
                monopoly.setNbDoubles(0);
                Action.avancerJoueur(joueur, nbCases);
            }

            Terrain t = joueur.getUIPlateau().getCaseP(joueur.getPositionJoueur());
            try {
                interactionCase(t);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            //prisonnier
            if(joueur.getNombreDeTourEnPrison() == 3) {
                monopoly.getMessageFooter().setText("C'est ton troisième tour en prison, tu sors et tu payes 50.");
                Action.retirer(50, joueur);
                Action.sortirDePrison(joueur);
                joueur.setNombreDeTourEnPrison(0);
                handle(event);
            }else{
                if(de1 == de2){
                    monopoly.getMessageFooter().setText("Un double ! Vous sortez de prison !");
                    Action.sortirDePrison(joueur);
                    joueur.setNombreDeTourEnPrison(0);
                    handle(event);
                }else{
                    monopoly.getMessageFooter().setText("Pas de double... un tour de plus en prison.");
                    joueur.setNombreDeTourEnPrison(joueur.getNombreDeTourEnPrison()+1);
                }

            }
        }
    }

    public void interactionCase(Terrain t) throws Exception {
        Joueur joueur = monopoly.getJoueurCourant();
        payerLoyer();
        if (t instanceof TerrainAction) {
            TerrainAction tac = (TerrainAction) t;
            switch (tac.getNomTerrain()) {
                case "TAXE DE LUXE":
                    monopoly.getMessageFooter().setText("Vous payez la taxe de luxe");
                    Action.retirer(TAXE_DE_LUXE, joueur);
                    break;
                case "IMPOT SUR LE REVENU":
                    monopoly.getMessageFooter().setText("Vous payez la taxe sur le revenue");
                    Action.retirer(TAXE_SUR_LE_REVENU, joueur);
                    break;
                case "ALLEZ EN PRISON":
                    monopoly.getMessageFooter().setText("Vous allez en prison");
                    Action.allerEnPrison(joueur);
                    break;
            }
        } else if (t instanceof Piocher) {
            Piocher tpiocher = (Piocher) t;

            switch (tpiocher.getNomTerrain()) {
                case "CHANCE":
                    monopoly.getMessageFooter().setText("Vous piochez une carte chance");

                    Cartes chance = Action.piocherChance(joueur);
                    utiliserCarteChance(chance);

                    break;

                case "CAISSE COMMUNAUTE":
                    monopoly.getMessageFooter().setText("Vous piochez une carte communauté");
                    Cartes communautee = Action.piocherCommunaute(joueur);
                    if(communautee instanceof Chance) {
                        monopoly.getMessageFooter().setText(((Chance) communautee).getMessage());
                        Boolean payer = monopoly.DialogActionCarteChance();
                        //on doit le gerer comme ça pour que tout s'affiche correctement
                        if(payer)
                            Action.retirer(10,joueur);
                        else {
                            Cartes chances = Action.piocherChance(joueur);
                            utiliserCarteChance(chances);
                        }
                    } else if (communautee instanceof Anniversaire) {
                        Anniversaire c = (Anniversaire) communautee;
                        monopoly.getMessageFooter().setText(c.getMessage());
                    } else if (communautee instanceof Deplacement) {
                        Deplacement c = (Deplacement) communautee;
                        monopoly.getMessageFooter().setText(c.getMessage());
                    } else if (communautee instanceof Encaisser) {
                        Encaisser c = (Encaisser) communautee;
                        monopoly.getMessageFooter().setText(c.getMessage());
                    } else if (communautee instanceof Liberation) {
                        Liberation c = (Liberation) communautee;
                        monopoly.getMessageFooter().setText(c.getMessage());
                    } else if (communautee instanceof Payer) {
                        Payer c = (Payer) communautee;
                        monopoly.getMessageFooter().setText(c.getMessage());
                    }
                    try {
                        communautee.action(joueur);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
        //on reposisionne son pion
        int numJoueurCourant = monopoly.getUiPlateau().getListeJoueurs().indexOf(joueur);
        Pion pionJoueurCourant = monopoly.getListePions().get(numJoueurCourant);
        monopoly.getUiPlateau().getCase(pionJoueurCourant.getPosition()).enlever(pionJoueurCourant);
        pionJoueurCourant.setPosition(joueur.getPositionJoueur());
        monopoly.getUiPlateau().getCase(pionJoueurCourant.getPosition()).poser(pionJoueurCourant);
        monopoly.getUiPlateau().dessiner(monopoly.getGrillePane());

        //on mets a jour son porte monnaie
        monopoly.setValueTfPorteMonnaie(joueur.getCapitalJoueur());
    }



    public void utiliserCarteChance(Cartes chance){
        if (chance instanceof Deplacement) {
            Deplacement c = (Deplacement) chance;
            monopoly.getMessageFooter().setText(c.getMessage());
        } else if (chance instanceof Encaisser) {
            Encaisser c = (Encaisser) chance;
            monopoly.getMessageFooter().setText(c.getMessage());
        } else if (chance instanceof Liberation) {
            Liberation c = (Liberation) chance;
            monopoly.getMessageFooter().setText(c.getMessage());
        } else if (chance instanceof Payer) {
            Payer c = (Payer) chance;
            monopoly.getMessageFooter().setText(c.getMessage());
        } else if (chance instanceof Impot) {
            Impot c = (Impot) chance;
            monopoly.getMessageFooter().setText(c.getMessage());
        } else if (chance instanceof Reparation) {
            Reparation c = (Reparation) chance;
            monopoly.getMessageFooter().setText(c.getMessage());
        }
        try {
            chance.action(monopoly.getJoueurCourant());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void payerLoyer() throws Exception {

        Joueur joueur = monopoly.getJoueurCourant();
        Terrain t = joueur.getUIPlateau().getCaseP(joueur.getPositionJoueur());
        if(t.estAchetable()){
            TerrainAchetable ta = (TerrainAchetable) t;

            if (ta.aUnProprietaire()) {
                if (ta instanceof Gare) {
                    Gare g = (Gare) ta;
                    Action.payer(g.getLoyer(), joueur, g.getProprietaire());
                } else if (ta instanceof Compagnie) {
                    Compagnie c = (Compagnie) ta;
                    Action.payer(c.getLoyer(), joueur, c.getProprietaire());

                } else {
                    //c'est constructible
                    TerrainConstructible tc = (TerrainConstructible) ta;
                    int prixLoyer = 0;
                    switch (tc.getNombreMaison()) {
                        case 0:
                            prixLoyer = tc.getLoyer().getPrixAucuneMaison();
                            break;
                        case 1:
                            prixLoyer = tc.getLoyer().getPrixUnemaison();
                            break;
                        case 2:
                            prixLoyer = tc.getLoyer().getPrixDeuxMaison();
                            break;
                        case 3:
                            prixLoyer = tc.getLoyer().getPrixTroisMaison();
                            break;
                        case 4:
                            prixLoyer = tc.getLoyer().getPrixQuatreMaison();
                            break;
                        case 5:
                            prixLoyer = tc.getLoyer().getPrixHotel();
                            break;
                    }
                    Action.payer(prixLoyer, joueur, tc.getProprietaire());
                }
            }
        }


    }
}
