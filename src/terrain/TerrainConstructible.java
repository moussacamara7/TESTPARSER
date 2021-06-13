package terrain;

public class TerrainConstructible extends TerrainAchetable {


    private String couleur;
    private int nombreMaison;
    private int prixAchatMaison;
    private Loyer loyer;


    /**
     * Constructeur permettant de definir un TerrainConstructible
     * @param numeroTerrain le numero du terrain
     * @param nomTerrain le nom du terrain
     * @param prixAchat le prix d'achat du terrain
     * @param couleur la couleur du terrain
     * @param nombreMaison le nombre de maison sur le terrain
     * @param prixAchatMaison le prix d'achat des maisons
     * @param loyer le loyer du terrain
     */
    public TerrainConstructible(int numeroTerrain, String nomTerrain, int prixAchat, String couleur, int nombreMaison, int prixAchatMaison, Loyer loyer) {
        super(numeroTerrain, nomTerrain, prixAchat);
        this.couleur = couleur;
        this.nombreMaison = nombreMaison;
        this.prixAchatMaison = prixAchatMaison;
        this.loyer = loyer;
    }

    /**
     * @return la couleur du terrain
     */
    public String getCouleur() {
        return couleur;
    }

    /**
     * @return le nombre de maisons sur le terrain
     */
    public int getNombreMaison() {
        return nombreMaison;
    }

    /**
     * Initialise le nombre de maison sur le terrain
     * @param nombreMaison le nombre de maison sur le terrain
     */
    public void setNombreMaison(int nombreMaison) {
        this.nombreMaison = nombreMaison;
    }

    /**
     * @return le prx d'achate d'une maison
     */
    public int getPrixAchatMaison() {
        return prixAchatMaison;
    }

    /**
     * @return le loyer du terrain
     */
    public Loyer getLoyer() {
        return loyer;
    }

    /**
     * @return vrai car c'est un terrainConstructible
     */
    public boolean estConstructible() {
        return true;
    }

    /**
     * @return Chaine concatenee definissant TerrainConsrtuctible
     */
    @Override
    public String toString() {
        return "TerrainConstructible{" +
                "couleur='" + couleur + '\'' +
                ", nombreMaison=" + nombreMaison +
                ", prixAchatMaison=" + prixAchatMaison +
                ", loyer=" + loyer +
                "} " + super.toString();
    }
}
