package terrain;


import joueur.Joueur;

public abstract class TerrainNonAchetable implements Terrain {
    private final int numeroTerrain;
    private final String nomTerrain;

    /**
     * Constructeur permettant de definir un terrain non achetable
     * @param numeroTerrain numero du terrain
     * @param nomTerrain nom du terrain
     */
    public TerrainNonAchetable(int numeroTerrain, String nomTerrain) {
        this.numeroTerrain = numeroTerrain;
        this.nomTerrain = nomTerrain;
    }

    /**
     * @return le nom du terrain
     */
    public String getNomTerrain() {
        return nomTerrain;
    }

    /**
     * @return faux car c'est un terrain non achetable
     */
    public boolean estAchetable() {
        return false;
    }

    public abstract void action(Joueur joueur);

    /**
     * @return Chaine concatenee definissant terrain non achetable
     */
    @Override
    public String toString() {
        return "TerrainNonAchetable{" +
                "numeroTerrain=" + numeroTerrain +
                ", nomTerrain='" + nomTerrain + '\'' +
                '}';
    }
}
