package terrain;

public class Compagnie extends TerrainAchetable {

    private int loyer;

    /**
     * Constructeur permettant de definir une compagnie
     * @param numeroTerrain numero du terrain
     * @param nomTerrain nom du terrain
     * @param prixAchat prix d'achat terrain
     */
    public Compagnie(int numeroTerrain, String nomTerrain, int prixAchat) {
        super(numeroTerrain, nomTerrain, prixAchat);
        setLoyer(20);
    }

    /**
     * @return le loyer du terrain
     */
    public int getLoyer() {
        return loyer;
    }

    /**
     * Initialise le loyer
     * @param loyer loyer du terrain
     */
    public void setLoyer(int loyer) {
        this.loyer = loyer;
    }

    /**
     *
     * @return renvoie faux car c'est une compagnie
     */
    public boolean estConstructible() {
        return false;
    }

    /**
     * @return chaine concaténée definissant la compagnie
     */
    @Override
    public String toString() {
        return "Compagnie{} " + super.toString();
    }
}
