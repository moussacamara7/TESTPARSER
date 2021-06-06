package application.event;

import application.Monopoly;
import carte.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import joueur.Joueur;
import mecanismeJeu.Action;
import terrain.Piocher;
import terrain.Terrain;
import terrain.TerrainAction;


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
            de1 = Action.lancerDe();
            de2 = Action.lancerDe();
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
            interactionCase(t);
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

    public void interactionCase(Terrain t){
        Joueur joueur = monopoly.getJoueurCourant();
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
                        chance.action(joueur);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case "CAISSE COMMUNAUTE":
                    monopoly.getMessageFooter().setText("Vous piochez une carte communauté");
                    Cartes communautee = Action.piocherCommunaute(joueur);
                    if (communautee instanceof Chance) {
                        System.out.println(((Chance) communautee).getMessage());
                        Boolean choix = monopoly.DialogActionCarteChance();
                        ((Chance) communautee).action(joueur, choix);
                    } else if (communautee instanceof Anniversaire) {
                        Anniversaire c = (Anniversaire) communautee;
                        System.out.println(c.getMessage());
                    } else if (communautee instanceof Deplacement) {
                        Deplacement c = (Deplacement) communautee;
                        System.out.println(c.getMessage());
                    } else if (communautee instanceof Encaisser) {
                        Encaisser c = (Encaisser) communautee;
                        System.out.println(c.getMessage());
                    } else if (communautee instanceof Liberation) {
                        Liberation c = (Liberation) communautee;
                        System.out.println(c.getMessage());
                    } else if (communautee instanceof Payer) {
                        Payer c = (Payer) communautee;
                        System.out.println(c.getMessage());
                    }
                    try {
                        communautee.action(joueur);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }
}
