package mecanismeJeu;

import joueur.Joueur;
import plateau.Plateau;
import terrain.Terrain;
import terrain.TerrainAchetable;


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


    public void payer(int somme, Joueur depart, Joueur destination) throws Exception {
        if (somme <= 0)
            throw new IllegalArgumentException("somme invalide");
        if (depart.getCapitalJoueur() < somme)
            throw new Exception("Capital insuffisant");

        //On retire l'argent au Joueur qui donne
        depart.setCapitalJoueur(depart.getCapitalJoueur() - somme);
        //On ajoute l'argent au joueur qui le recoit
        destination.setCapitalJoueur(destination.getCapitalJoueur() + somme);
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    //  Pour les opérations vers la banque on peut supposer que la banque a un budjet illimité          //
    //  Donc on a même pas besoin de définir une methode en destination de la banque                    //
    //  On ajoute ou on soustrait selon le cas ou il doit être crédité ou prelevé                       //
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    //pour payer depuis la banque


    public void payer(int somme, Joueur destination){
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
    public void retirer(int somme, Joueur destination){
        if (somme <= 0)
            throw new IllegalArgumentException("somme invalide");
        destination.setCapitalJoueur(destination.getCapitalJoueur() + somme);

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
    public void acheter(Joueur joueur, int numeroTerrain) throws Exception {

        Plateau plateau = joueur.getPlateau();

        if (!plateau.getCase(numeroTerrain).estAchetable())
            throw new IllegalArgumentException("terrain non achetable");

        //c'est bien un terrain achetable
        TerrainAchetable T = (TerrainAchetable) plateau.getCase(numeroTerrain);

        //on vérifie que le terrain n'ai pas de propriétaire
        if (T.aUnProprietaire())
            throw new Exception("Le terrain a deja un proprietaire");

        //on vérifie si le joueur peut l'acheter
        if (joueur.getCapitalJoueur() < T.getPrixAchat())
            throw new Exception("Capital insuffisant");

        //On retire l'argent au joueur et on lui ajoute la popriété
        joueur.setCapitalJoueur(joueur.getCapitalJoueur() - T.getPrixAchat());
        joueur.ajouterPropriete(T);
        ((TerrainAchetable) plateau.getCase(numeroTerrain)).setProprietaire(joueur);

    }

    public void acheterPropriété(Joueur joueur, Terrain T) throws Exception {

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

    public void allerEnPrison(Joueur joueur){
        if (joueur.isEstprisonnier())
            throw new IllegalArgumentException("joueur deja prisonnier");
        joueur.setEstprisonnier(true);
        joueur.setPositionJoueur(40);
    }

    public void sortirDePrison(Joueur joueur) {
        if (!joueur.isEstprisonnier())
            throw new IllegalArgumentException("joueur non prisonnier");
        joueur.setEstprisonnier(false);
        joueur.setPositionJoueur(10);   //On le place sur la case simple visite
    }

    public void deplacer(Joueur joueur, int position){
        if (position < joueur.getPositionJoueur())
            payer(200, joueur);
        joueur.setPositionJoueur(position);
    }

    public void construire(int terrain){

    }

    public void hypotequer(int terrain){

    }




}
