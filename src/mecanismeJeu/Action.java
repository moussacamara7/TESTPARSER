package mecanismeJeu;
import joueur.Joueur;
import plateau.Plateau;
import terrain.Terrain;
import terrain.TerrainAchetable;


public class Action {

    /***********************
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
     ***********************/



    public void payer(int somme, Joueur depart, Joueur destination) throws Exception{
        if(depart.getCapitalJoueur() < somme)
            throw new Exception("Capital insuffisant");

        //On retire l'argent au Joueur qui donne
        depart.setCapitalJoueur(depart.getCapitalJoueur() - somme);
        //On ajoute l'argent au joueur qui le recoit
        destination.setCapitalJoueur(destination.getCapitalJoueur() + somme);
    }


    public void acheter(Joueur joueur, int numeroTerrain) throws Exception{

        Plateau plateau = joueur.getPlateau();

        if(! plateau.getCase(numeroTerrain).estAchetable())
            throw new IllegalArgumentException("terrain non achetable");

        //c'est bien un terrain achetable
        TerrainAchetable T = (TerrainAchetable) plateau.getCase(numeroTerrain);

        //on vérifie que le terrain n'ai pas de propriétaire
        if(T.aUnProprietaire())
            throw new Exception("Le terrain a deja un proprietaire");

        //on vérifie si le joueur peut l'acheter
        if(joueur.getCapitalJoueur() < T.getPrixAchat())
            throw new Exception("Capital insuffisant");

        //On retire l'argent au joueur et on lui ajoute la popriété
        joueur.setCapitalJoueur(joueur.getCapitalJoueur()-T.getPrixAchat());
        joueur.ajouterPropriete(T);
        ((TerrainAchetable) plateau.getCase(numeroTerrain)).setProprietaire(joueur);

    }

    public void acheter(Joueur joueur, Terrain T) throws Exception{

        if(! T.estAchetable())
            throw new IllegalArgumentException("terrain non achetable");

        //c'est bien un terrain achetable
        TerrainAchetable Ta = (TerrainAchetable) T;

        //on vérifie que le terrain n'ai pas de propriétaire
        if(Ta.aUnProprietaire())
            throw new Exception("Le terrain a deja un proprietaire");

        //on vérifie si le joueur peut l'acheter
        if(joueur.getCapitalJoueur() < Ta.getPrixAchat())
            throw new Exception("Capital insuffisant");

        //On retire l'argent au joueur et on lui ajoute la popriété
        joueur.setCapitalJoueur(joueur.getCapitalJoueur()-Ta.getPrixAchat());
        joueur.ajouterPropriete(Ta);
        ((TerrainAchetable) T).setProprietaire(joueur);

    }



}
