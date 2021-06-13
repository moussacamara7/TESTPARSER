package terrain;

import joueur.Joueur;

public abstract class TerrainAchetable implements Terrain {
    private int numeroTerrain;
    private String nomTerrain;
    private int prixAchat;
    private Joueur proprietaire;

    /**
     * Constructeur permettant de definir le terrain
     *
     * @param numeroTerrain numero du terrain
     * @param nomTerrain    nom du terrain
     * @param prixAchat     prix d'achat terrain
     */
    public TerrainAchetable(int numeroTerrain, String nomTerrain, int prixAchat) {
        this.numeroTerrain = numeroTerrain;
        this.nomTerrain = nomTerrain;
        this.prixAchat = prixAchat;
        setProprietaire(null);
    }

    /**
     * @return le numero du terrain
     */
    public int getNumeroTerrain() {
        return numeroTerrain;
    }

    /**
     * @return le nom du terrain
     */
    public String getNomTerrain() {
        return nomTerrain;
    }

    /**
     * @return le prix d'achat du terrain
     */
    public int getPrixAchat() {
        return prixAchat;
    }

    /**
     * @return le proprietaire du terrain
     */
    public Joueur getProprietaire() {
        return proprietaire;
    }

    /**
     * Initialise le proprietaire du terrain
     * @param proprietaire le proprietaire du terrain
     */
    public void setProprietaire(Joueur proprietaire) {
        this.proprietaire = proprietaire;
    }

    /**
     * @return vrai car c'est un terrain achetable
     */
    public boolean estAchetable() {
        return true;
    }

    /**
     * @return vrai si le proprietaire n'est pas null
     */
    public boolean aUnProprietaire() {
        return this.proprietaire != null;
    }

    /**
     * @return vrai si le terrain est constructible, sinon faux
     */
    public abstract boolean estConstructible();

    /**
     * @return chaine concatene definissant un terrainAchetable
     */
    @Override
    public String toString() {
        return "TerrainAchetable{" +
                "numeroTerrain=" + numeroTerrain +
                ", nomTerrain='" + nomTerrain + '\'' +
                ", prixAchat=" + prixAchat +
                '}';
    }
}