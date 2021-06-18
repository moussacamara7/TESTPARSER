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

//        String tfDe1 = monopoly.getTfValeurDe1().getText();
//        String tfDe2 = monopoly.getTfValeurDe2().getText();

        int de1, de2;

        /*if (!tfDe1.trim().isEmpty() && !tfDe2.trim().isEmpty()) {

            de1 = Integer.parseInt(tfDe1);
            de2 = Integer.parseInt(tfDe2);
            monopoly.getMessageFooter().setText("");
        } else {*/
            monopoly.getMessageFooter().setText("");
            de1 = Action.lancerDe();
            de2 = Action.lancerDe();
            monopoly.setTfDe1(String.valueOf(de1));
            monopoly.setTfDe2(String.valueOf(de2));
        //}
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
        try {
            interactionCase(t);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * Gere l'interaction entre la case et le joueur
     * @param t le terrain ou se trouve le joueur
     */
    private void interactionCase(Terrain t) {
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
                    utiliserCarteChance(chance);

                    break;

                case "CAISSE COMMUNAUTE":
                    monopoly.getMessageFooter().setText("Vous piochez une carte communauté");
                    Cartes communautee = Action.piocherCommunaute(joueur);
                    if (communautee instanceof Chance) {
                        monopoly.getMessageFooter().setText(((Chance) communautee).getMessage());
                        Boolean payer = monopoly.DialogActionCarteChance();
                        //on doit le gerer comme ça pour que tout s'affiche correctement
                        if (payer)
                            Action.retirer(10, joueur);
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

        repositionnerPion();
        //on mets a jour son porte monnaie
        monopoly.setValueTfPorteMonnaie(joueur.getCapitalJoueur());
    }

    /**
     * Fonction qui permet d'utiliser une carte chance piocher
     * @param chance la carte pioche par le joueur
     */
    private void utiliserCarteChance(Cartes chance) {
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

    /**
     * Fonction qui verifie si le joueur doit payer un loyer sur le terrain ou il se trouve
     * si se trouve sur la propriete d'un autre joueur, il lui paye le loyer
     */
    private void payerLoyer(){

        Joueur joueur = monopoly.getJoueurCourant();
        Terrain t = joueur.getUIPlateau().getCaseP(joueur.getPositionJoueur());
        if (t.estAchetable()) {
            TerrainAchetable ta = (TerrainAchetable) t;

            if (ta.aUnProprietaire()) {
                if (ta instanceof Gare) {
                    Gare g = (Gare) ta;
                    try {
                        Action.payer(g.getLoyer(), joueur, g.getProprietaire());
                    } catch (MonopolyException e) {
                        e.printStackTrace();
                    }
                } else if (ta instanceof Compagnie) {
                    Compagnie c = (Compagnie) ta;
                    try {
                        Action.payer(c.getLoyer(), joueur, c.getProprietaire());
                    } catch (MonopolyException e) {
                        e.printStackTrace();
                    }

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
                    try {
                        Action.payer(prixLoyer, joueur, tc.getProprietaire());
                    } catch (MonopolyException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
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
        try {
            payerLoyer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
