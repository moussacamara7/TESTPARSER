package mecanismeJeu;

import joueur.Joueur;
import terrain.*;

import java.util.Scanner;

public class Gestion {
    public static final int TAXE_DE_LUXE = 100;
    public static final int TAXE_SUR_LE_REVENU = 200;


    public static void interactionCase(Joueur joueur) throws Exception {
        Terrain t = joueur.getPlateau().getCase(joueur.getPositionJoueur());
        Scanner sc = new Scanner(System.in);

        System.out.println("Vous arrivez sur la case " +joueur.getPositionJoueur());

        if(t.estAchetable()){
            TerrainAchetable ta = (TerrainAchetable) t;
            if(ta.aUnProprietaire() && ta.getProprietaire().equals(joueur)){
                if(ta.estConstructible()){
                    TerrainConstructible tc = (TerrainConstructible) ta;
                    if(Action.peutConstruire(joueur.getPositionJoueur(),joueur.getPlateau())){
                        //on propose de construire
                        System.out.println("Voulez vous construire ?\n");
                        System.out.println("Oui : 1 \nNon : 0");
                        int choix = sc.nextInt();
                        if(choix == 1){
                            Action.construire(joueur.getPositionJoueur(),joueur.getPlateau());
                        }
                    }
                }
            }else{
                if(ta.aUnProprietaire()){
                        if(ta instanceof Gare){
                            Gare g = (Gare) ta;
                            Action.payer(g.getLoyer(),joueur,g.getProprietaire());
                        }else if(ta instanceof Compagnie){
                                Compagnie c = (Compagnie) ta;
                                Action.payer(c.getLoyer(),joueur,c.getProprietaire());

                        }else{
                            //c'est constructible
                            TerrainConstructible tc = (TerrainConstructible) ta;
                            int prixLoyer = 0;
                            switch(tc.getNombreMaison()){
                                case 0 : prixLoyer = tc.getLoyer().getPrixAucuneMaison();
                                         break;
                                case 1 : prixLoyer = tc.getLoyer().getPrixUnemaison();
                                         break;
                                case 2 : prixLoyer = tc.getLoyer().getPrixDeuxMaison();
                                         break;
                                case 3 : prixLoyer = tc.getLoyer().getPrixTroisMaison();
                                         break;
                                case 4 : prixLoyer = tc.getLoyer().getPrixQuatreMaison();
                                         break;
                                case 5 : prixLoyer = tc.getLoyer().getPrixHotel();
                                         break;
                            }
                            Action.payer(prixLoyer, joueur, tc.getProprietaire());
                        }
                }else if(joueur.getCapitalJoueur() >= ta.getPrixAchat()){
                    //le joueur peut acheter le terrain
                    System.out.println("Voulez vous acheter " +ta.getNomTerrain() +"?");
                    System.out.println(ta.toString());
                    System.out.println("1 : oui \n0 : non");
                    int choix = sc.nextInt();
                    if(choix == 1){
                        Action.acheterPropriete(joueur, ta);
                    }

                }
            }

        }else{
            if(t instanceof TerrainAction){
                TerrainAction tac = (TerrainAction) t;
                switch (tac.getNomTerrain()) {
                    case "TAXE DE LUXE":
                        System.out.println("Vous payez la taxe de luxe");
                        Action.retirer(TAXE_DE_LUXE, joueur);
                        break;
                    case "IMPOT SUR LE REVENU":
                        System.out.println("Vous payez la taxe sur le revenue");
                        Action.retirer(TAXE_SUR_LE_REVENU, joueur);
                        break;
                    case "ALLEZ EN PRISON":
                        System.out.println("Vous allez en prison");
                        Action.allerEnPrison(joueur);
                        break;
                    case "CHANCE":
                        System.out.println("Vous piochez une carte chance");
                        Action.piocherChance(joueur);
                    case "CAISSE COMMUNAUTE":
                        System.out.println("Vous piochez une carte communauté");
                        Action.piocherCommunaute(joueur);
                }
            }

        }

    }

    public static void jouerTour(Joueur joueur) throws Exception {
        int deUn, deDeux;
        int nbDouble = 0;
        deUn = Action.lancerDe();
        deDeux = Action.lancerDe();

        do{
            if(deDeux == deUn) {
                nbDouble++;
                System.out.println("\nde 1: " + deUn + "\nde 2: " + deDeux + "\n");
                Action.avancerJoueur(joueur, deUn + deDeux);
                interactionCase(joueur);
                System.out.println("Vous avez fait un double, vous relancé.\nNombre de double ce tour : " + nbDouble);
                deUn = Action.lancerDe();
                deDeux = Action.lancerDe();
            }


        }while(deUn == deDeux && nbDouble < 3 );

        if(nbDouble == 3) {
            System.out.println("3 doubles d'affilé, vous allez en prison" +joueur.getNomJoueur());
            Action.allerEnPrison(joueur);
        }
        else {
            System.out.println("\nde 1: " + deUn + "\nde 2: " + deDeux + "\n");
            Action.avancerJoueur(joueur, deUn + deDeux);
            interactionCase(joueur);
        }
    }

    public static void jouerTour(Joueur joueur, int deUn, int deDeux) throws Exception {
        int nbDouble = 0;

        do{
            if(deDeux == deUn) {
                nbDouble++;
                System.out.println("\nde 1: " + deUn + "\nde 2: " + deDeux + "\n");
                Action.avancerJoueur(joueur, deUn + deDeux);
                interactionCase(joueur);
                System.out.println("Vous avez fait un double, vous relancé.\nNombre de double ce tour : " + nbDouble);
                deUn = Action.lancerDe();
                deDeux = Action.lancerDe();
            }


        }while(deUn == deDeux && nbDouble < 3 );

        if(nbDouble == 3) {
            System.out.println("3 doubles d'affilé, vous allez en prison" +joueur.getNomJoueur());
            Action.allerEnPrison(joueur);
        }
        else {
            System.out.println("\nde 1: " + deUn + "\nde 2: " + deDeux + "\n");
            Action.avancerJoueur(joueur, deUn + deDeux);
            interactionCase(joueur);
        }
    }
}