package terrain;

public class Loyer {
    private final int prixUnemaison;
    private final int prixDeuxMaison;
    private final int prixTroisMaison;
    private final int prixQuatreMaison;
    private final int prixHotel;
    private final int prixAucuneMaison;

    public Loyer(int prixUnemaison, int prixDeuxMaison, int prixTroisMaison, int prixQuatreMaison, int prixHotel, int prixAucuneMaison) {
        this.prixUnemaison = prixUnemaison;
        this.prixDeuxMaison = prixDeuxMaison;
        this.prixTroisMaison = prixTroisMaison;
        this.prixQuatreMaison = prixQuatreMaison;
        this.prixHotel = prixHotel;
        this.prixAucuneMaison = prixAucuneMaison;
    }

    public int getPrixUnemaison() {
        return prixUnemaison;
    }

    public int getPrixDeuxMaison() {
        return prixDeuxMaison;
    }

    public int getPrixTroisMaison() {
        return prixTroisMaison;
    }

    public int getPrixQuatreMaison() {
        return prixQuatreMaison;
    }

    public int getPrixHotel() {
        return prixHotel;
    }

    public int getPrixAucuneMaison() {
        return prixAucuneMaison;
    }

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
