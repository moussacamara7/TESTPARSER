package terrain;

public class Compagnie extends TerrainAchetable{


    public Compagnie(int numeroTerrain, String nomTerrain, int prixAchat) {
        super(numeroTerrain, nomTerrain, prixAchat);
    }

    public boolean estConstructible(){
        return false;
    }

    @Override
    public String toString() {
        return "Compagnie{} " + super.toString();
    }
}
