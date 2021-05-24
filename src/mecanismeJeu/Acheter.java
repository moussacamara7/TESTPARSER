package mecanismeJeu;

import joueur.Joueur;
import plateau.Plateau;
import terrain.*;

public class Acheter extends Action{
    public Acheter(Action suivant) {
        super(suivant);
    }



    public void fait(int ligne) throws Exception{
        throw new Exception("Arguments insuffisants");
    }



    public void fait(Joueur joueur, int numeroTerrain) throws Exception{

        if(! Plateau.getCase(numeroTerrain).estAchetable())
            throw new IllegalArgumentException("terrain non achetable");

        //c'est bien un terrain achetable
        TerrainAchetable T = (TerrainAchetable) Plateau.getCase(numeroTerrain);

        //on vérifie que le terrain n'ai pas de propriétaire
        if(T.getProprietaire() != null)
            throw new Exception("Le terrain a deja un proprietaire");

        //on vérifie si le joueur peut l'acheter
        if(joueur.getCapitalJoueur() < T.getPrixAchat())
            throw new Exception("Capital insuffisant");

        //On retire l'argent au joueur et on lui ajoute la popriété
        joueur.setCapitalJoueur(joueur.getCapitalJoueur()-T.getPrixAchat());
        joueur.ajouterPropriete(T);
        ((TerrainAchetable) Plateau.getCase(numeroTerrain)).setProprietaire(joueur);

    }

    public void fait(Joueur joueur, Terrain T) throws Exception{

        if(! T.estAchetable())
            throw new IllegalArgumentException("terrain non achetable");

        //c'est bien un terrain achetable
        TerrainAchetable Ta = (TerrainAchetable) T;

        //on vérifie que le terrain n'ai pas de propriétaire
        if(Ta.getProprietaire() != null)
            throw new Exception("Le terrain a deja un proprietaire");

        //on vérifie si le joueur peut l'acheter
        if(joueur.getCapitalJoueur() < Ta.getPrixAchat())
            throw new Exception("Capital insuffisant");

        //On retire l'argent au joueur et on lui ajoute la popriété
        joueur.setCapitalJoueur(joueur.getCapitalJoueur()-Ta.getPrixAchat());
        joueur.ajouterPropriete(Ta);
        ((TerrainAchetable) T).setProprietaire(joueur);

    }

    public boolean saitFaire(int ligne) {
        if (ligne == 1)
            return true;
        return false;
    }
}
