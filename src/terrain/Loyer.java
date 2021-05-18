package terrain;

public class Loyer {
    private int prixUnemaison;
    private int prixDeuxMaison;
    private int prixTroisMaison;
    private int prixQuatreMaison;
    private int prixHotel;
    private int prixAucuneMaison;

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

    public void setPrixUnemaison(int prixUnemaison) {
        this.prixUnemaison = prixUnemaison;
    }

    public int getPrixDeuxMaison() {
        return prixDeuxMaison;
    }

    public void setPrixDeuxMaison(int prixDeuxMaison) {
        this.prixDeuxMaison = prixDeuxMaison;
    }

    public int getPrixTroisMaison() {
        return prixTroisMaison;
    }

    public void setPrixTroisMaison(int prixTroisMaison) {
        this.prixTroisMaison = prixTroisMaison;
    }

    public int getPrixQuatreMaison() {
        return prixQuatreMaison;
    }

    public void setPrixQuatreMaison(int prixQuatreMaison) {
        this.prixQuatreMaison = prixQuatreMaison;
    }

    public int getPrixHotel() {
        return prixHotel;
    }

    public void setPrixHotel(int prixHotel) {
        this.prixHotel = prixHotel;
    }

    public int getPrixAucuneMaison() {
        return prixAucuneMaison;
    }

    public void setPrixAucuneMaison(int prixAucuneMaison) {
        this.prixAucuneMaison = prixAucuneMaison;
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
