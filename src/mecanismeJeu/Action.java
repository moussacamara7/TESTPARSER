package mecanismeJeu;

import application.ui.UIPlateau;
import carte.Cartes;
import exception.JoueurFailliteException;
import joueur.Joueur;
import plateau.Plateau;
import terrain.Terrain;
import terrain.TerrainAchetable;
import terrain.TerrainConstructible;

import java.util.Random;


public class Action {

    /****************************
     *
     * actions :
     *
     *      payer un joueur
     *      acheter une propriété
     *      construire
     *      aller en prison
     *      sortir de prison
     *      se deplacer
     *      hypotequer
     *
     ****************************/


    //////////////////////////////////////////////////////////////////////////////////////////////////////
    //  Pour les opérations vers la banque on peut supposer que la banque a un budjet illimité          //
    //  Donc on a même pas besoin de définir une methode en destination de la banque                    //
    //  On ajoute ou on soustrait selon le cas ou il doit être crédité ou prelevé                       //
    //////////////////////////////////////////////////////////////////////////////////////////////////////

    //pour payer depuis la banque
    public static void payer(int somme, Joueur destination) {
        if (somme <= 0)
            throw new IllegalArgumentException("somme invalide");
        destination.setCapitalJoueur(destination.getCapitalJoueur() + somme);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    //  les cas ou le joueur perd de l'argent c'est quand quand il doit payer une amende, payer un autre    //
    //  Joueur (Loyer, aniverssaire)                                                                        //
    //  Pour le loyer on a déjà payer(...)                                                                  //
    //  Pour anniversaire on peut parcourir la liste des joueurs du plateau puis les soustraire a chacun    //
    //  la somme puis pour le joueur concerné il recoit: recu = somme * (Plateau.getNombreJoueurs() - 1)    //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////

    //retirer la somme à un joueur
    public static void retirer(int somme, Joueur destination) {
        if (somme <= 0)
            throw new IllegalArgumentException("somme invalide");
        destination.setCapitalJoueur(destination.getCapitalJoueur() - somme);

        //on peut lancer l'exception de faillite ici si le joueur est en negatif
        //ce qui l'obligera a hypotequer ou perdre la partie


    }

    public static void payer(int somme, Joueur depart, Joueur destination) throws Exception {
        if (somme <= 0)
            throw new IllegalArgumentException("somme invalide");
        if (depart.getCapitalJoueur() < somme)
            throw new Exception("Capital insuffisant");
        //on ne considere pas l'exception ou depart = destination

        //On retire l'argent au Joueur qui donne
        Action.retirer(somme, depart);
        //On ajoute l'argent au joueur qui le recoit
        Action.payer(somme, destination);
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
//  Dans notre cas je présuppose qu'il ne peut avoir qu'un seul plateau de monopoly                         //
//  Plateau p1 = new Plateau();                                                                             //
//      est une ruse pour juste charger les terrains et cartes                                              //
//      Si on a besoin d'acceder aux cases, joueurs ou cartes on fait par ex: PLateau.getNombreJoueurs()    //
//      C'est pour cela j'ai privilégié les fonctions de plateau en "static"                                //
//////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * acheter un terrain
     * pas de verification de la position du joueur
     */
    public static void acheterPropriete(Joueur joueur, int numeroTerrain) throws Exception {

        UIPlateau plateau = joueur.getPlateau();


        if (!plateau.getCaseP(numeroTerrain).estAchetable())
            throw new IllegalArgumentException("terrain non achetable");

        //c'est bien un terrain achetable
        TerrainAchetable T = (TerrainAchetable) plateau.getCaseP(numeroTerrain);

        //on vérifie que le terrain n'ai pas de propriétaire
        if (T.aUnProprietaire())
            throw new Exception("Le terrain a deja un proprietaire");

        //on vérifie si le joueur peut l'acheter
        if (joueur.getCapitalJoueur() < T.getPrixAchat())
            throw new Exception("Capital insuffisant");

        //On retire l'argent au joueur et on lui ajoute la popriété
        joueur.setCapitalJoueur(joueur.getCapitalJoueur() - T.getPrixAchat());
        joueur.ajouterPropriete(T);
        ((TerrainAchetable) plateau.getCaseP(numeroTerrain)).setProprietaire(joueur);

    }

    public static void acheterPropriete(Joueur joueur, Terrain T) throws Exception {

        if (!T.estAchetable())
            throw new IllegalArgumentException("terrain non achetable");

        //c'est bien un terrain achetable
        TerrainAchetable Ta = (TerrainAchetable) T;

        //on vérifie que le terrain n'ai pas de propriétaire
        if (Ta.aUnProprietaire())
            throw new Exception("Le terrain a deja un proprietaire");

        //on vérifie si le joueur peut l'acheter
        if (joueur.getCapitalJoueur() < Ta.getPrixAchat())
            throw new Exception("Capital insuffisant");

        //On retire l'argent au joueur et on lui ajoute la popriété
        joueur.setCapitalJoueur(joueur.getCapitalJoueur() - Ta.getPrixAchat());
        joueur.ajouterPropriete(Ta);
        ((TerrainAchetable) T).setProprietaire(joueur);

    }

    public static void allerEnPrison(Joueur joueur) {
        if (joueur.isEstprisonnier())
            throw new IllegalArgumentException("joueur deja prisonnier");
        joueur.setEstprisonnier(true);
        joueur.setPositionJoueur(40);
    }

    public static void sortirDePrison(Joueur joueur) {
        if (!joueur.isEstprisonnier())
            throw new IllegalArgumentException("joueur non prisonnier");
        joueur.setEstprisonnier(false);
        joueur.setPositionJoueur(10);   //On le place sur la case simple visite
    }

    public static void deplacer(Joueur joueur, int position) {
        if (position < joueur.getPositionJoueur())
            payer(200, joueur);
        joueur.setPositionJoueur(position);
    }

    public static void avancerJoueur(Joueur joueur, int nbDeplacement) {
        if (nbDeplacement <= 0)
            throw new IllegalArgumentException("deplacement illegal");

        int nouvellePos;
        if (joueur.getPositionJoueur() + nbDeplacement > 39) {
            nouvellePos = joueur.getPositionJoueur() + nbDeplacement - 40;
            payer(200, joueur);
            //case depart
        } else {
            nouvellePos = joueur.getPositionJoueur() + nbDeplacement;
        }

        joueur.setPositionJoueur(nouvellePos);
    }


    //pour verifier si un joueur peut construire sur un terrain
    //cette methode peut etre utilisée dans la gestion du jeu
    public static boolean peutConstruire(int terrain, UIPlateau plateau) {

        if (!plateau.getCaseP(terrain).estAchetable())
            return false;
        //terrain non achetable


        TerrainAchetable ta = (TerrainAchetable) plateau.getCaseP(terrain);

        if (!ta.estConstructible())
            return false;
        //terrain non constructible

        if (!ta.aUnProprietaire())
            return false;
        //le terrain n'a pas de propriétaire

        TerrainConstructible tc = (TerrainConstructible) ta;
        String couleur = tc.getCouleur();
        Joueur propietaire = tc.getProprietaire();
        int nbMaison = tc.getNombreMaison();

        for (Terrain n : plateau.getListeCasesP()) {
            if (n.estAchetable()) {
                TerrainAchetable na = (TerrainAchetable) n;
                if (na.estConstructible()) {
                    TerrainConstructible nc = (TerrainConstructible) na;
                    if (nc.getCouleur().equals(couleur)) {

                        if (!propietaire.equals(nc.getProprietaire()))
                            return false;
                        //le joueur ne possède pas tous les terrains de cette couleur
                        if (nc.getNombreMaison() < nbMaison)
                            return false;
                        //terrain non eligible a la construction
                        //le joueur n'a pas assez de construction sur les autres terrains de meme couleur
                    }
                }
            }
        }
        return true;
    }

    public static void construire(int terrain, UIPlateau plateau) {

        if (!Action.peutConstruire(terrain, plateau))
            throw new IllegalArgumentException(("terrain non eligible a la construction"));
        TerrainConstructible tc = (TerrainConstructible) plateau.getCaseP(terrain);
        Action.retirer(tc.getPrixAchatMaison(), tc.getProprietaire());
        tc.setNombreMaison(tc.getNombreMaison() + 1);
    }

    public static int lancerDe() {
        return (int) Math.floor(Math.random() * (6) + 1);
    }

    public static Cartes piocherChance(Joueur joueur) {
        Random r = new Random();
        return joueur.getPlateau().getChance(r.nextInt(joueur.getPlateau().getNombreCarteChance() - 1));
    }

    public static Cartes piocherCommunaute(Joueur joueur) {
        Random r = new Random();
        return joueur.getPlateau().getCommunaute(r.nextInt(joueur.getPlateau().getNombreCarteCommunaute() - 1));
    }

    public void hypotequer(int terrain) {

    }


}
