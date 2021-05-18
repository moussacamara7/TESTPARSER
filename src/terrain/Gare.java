package terrain;


public class Gare extends TerrainAchetable{

    public Gare(int numeroTerrain, String nomTerrain, int prixAchat) {
        super(numeroTerrain, nomTerrain, prixAchat);
    }

    public boolean estConstructible(){
        return false;
    }

    @Override
    public String toString() {
        return "Gare{} " + super.toString();
    }
}
