package terrain;

public class Compagnie extends TerrainAchetable {

    private int loyer;

    public Compagnie(int numeroTerrain, String nomTerrain, int prixAchat) {
        super(numeroTerrain, nomTerrain, prixAchat);
        setLoyer(20);
    }

    public int getLoyer() {
        return loyer;
    }

    public void setLoyer(int loyer) {
        this.loyer = loyer;
    }

    public boolean estConstructible() {
        return false;
    }

    @Override
    public String toString() {
        return "Compagnie{} " + super.toString();
    }
}
