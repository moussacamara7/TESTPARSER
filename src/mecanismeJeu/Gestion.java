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

        if(t.estAchetable()){
            TerrainAchetable ta = (TerrainAchetable) t;
            if(ta.aUnProprietaire() && ta.getProprietaire().equals(joueur)){
                if(ta.estConstructible()){
                    TerrainConstructible tc = (TerrainConstructible) ta;
                    if(Action.peutConstruire(joueur.getPositionJoueur(),joueur.getPlateau())){
                        //on propose de construire
                        System.out.println("voulez vous construire ?\n");
                        System.out.println("Oui : 1 \n Non : 0");
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
                    System.out.println("1 : oui \n 0 : non");
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
                        Action.retirer(TAXE_DE_LUXE, joueur);
                        break;
                    case "IMPOT SUR LE REVENU":
                        Action.retirer(TAXE_SUR_LE_REVENU, joueur);
                        break;
                    case "ALLEZ EN PRISON":
                        Action.allerEnPrison(joueur);
                        break;
                    case "CHANCE":
                        Action.piocherChance(joueur);
                    case "CAISSE COMMUNAUTE":
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
            if(deDeux == deUn)
                nbDouble++;
            Action.avancerJoueur(joueur, deUn + deDeux);
            interactionCase(joueur);
            deUn = Action.lancerDe();
            deDeux = Action.lancerDe();
            System.out.println("\nde 1:" +deUn +"\nde 2:" +deDeux +"\n");


        }while(deUn == deDeux && nbDouble < 3 );

        if(nbDouble == 3)
            Action.allerEnPrison(joueur);
        else
            Action.avancerJoueur(joueur, deUn + deDeux);
            interactionCase(joueur);

    }

    public static void jouerTour(Joueur joueur, int deUn, int deDeux) throws Exception {
        int nbDouble = 0;

        do{
            if(deDeux == deUn)
                nbDouble++;
            Action.avancerJoueur(joueur, deUn + deDeux);
            interactionCase(joueur);

            deUn = Action.lancerDe();
            deDeux = Action.lancerDe();
            System.out.println("\nde 1:" +deUn +"\nde 2:" +deDeux +"\n");


        }while(deUn == deDeux && nbDouble < 3 );

        if(nbDouble == 3)
            Action.allerEnPrison(joueur);
        else
            Action.avancerJoueur(joueur, deUn + deDeux);
            interactionCase(joueur);

    }
}
