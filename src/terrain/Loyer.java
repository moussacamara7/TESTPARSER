package terrain;

public class Loyer {
    private final int prixUnemaison;
    private final int prixDeuxMaison;
    private final int prixTroisMaison;
    private final int prixQuatreMaison;
    private final int prixHotel;
    private final int prixAucuneMaison;

    /**
     * Constructeur permettant de definir la fiche tarifaire du loyer
     * @param prixUnemaison prix si possede une maison
     * @param prixDeuxMaison prix si possede deux maisons
     * @param prixTroisMaison prix si possede trois maisons
     * @param prixQuatreMaison prix si possede quatre maisons
     * @param prixHotel prix si possede un hotel
     * @param prixAucuneMaison prix si possede aucune maison
     */
    public Loyer(int prixUnemaison, int prixDeuxMaison, int prixTroisMaison, int prixQuatreMaison, int prixHotel, int prixAucuneMaison) {
        this.prixUnemaison = prixUnemaison;
        this.prixDeuxMaison = prixDeuxMaison;
        this.prixTroisMaison = prixTroisMaison;
        this.prixQuatreMaison = prixQuatreMaison;
        this.prixHotel = prixHotel;
        this.prixAucuneMaison = prixAucuneMaison;
    }

    /**
     * @return prix d'une maison
     */
    public int getPrixUnemaison() {
        return prixUnemaison;
    }

    /**
     * @return prix de deux maisons
     */
    public int getPrixDeuxMaison() {
        return prixDeuxMaison;
    }

    /**
     * @return prix de trois maisons
     */
    public int getPrixTroisMaison() {
        return prixTroisMaison;
    }

    /**
     * @return prix de quatre maisons
     */
    public int getPrixQuatreMaison() {
        return prixQuatreMaison;
    }

    /**
     * @return prix d'un hotel
     */
    public int getPrixHotel() {
        return prixHotel;
    }

    /**
     * @return prix terrain vide
     */
    public int getPrixAucuneMaison() {
        return prixAucuneMaison;
    }

    /**
     * @return chaine concaténée definissant le loyer
     */
    @Override
    public String toString() {
        return "Loyer{" +
                "prixUnemaison=" + prixUnemaison +
                ", prixDeuxMaison=" + prixDeuxMaison +
                ", prixTroisMaison=" + prixTroisMaison +
                ", prixQuatreMaison=" + prixQuatreMaison +
                ", prixHotel=" + prixHotel +
                ", prixAucuneMaison=" + prixAucuneMaison +
                '}';
    }
}
