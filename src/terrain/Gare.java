package terrain;


public class Gare extends TerrainAchetable{

    private int loyer;

    public int getLoyer() {
        return loyer;
    }

    public void setLoyer(int loyer) {
        this.loyer = loyer;
    }

    public Gare(int numeroTerrain, String nomTerrain, int prixAchat) {
        super(numeroTerrain, nomTerrain, prixAchat);
        setLoyer(20);
    }

    public boolean estConstructible(){
        return false;
    }

    @Override
    public String toString() {
        return "Gare{} " + super.toString();
    }
}
