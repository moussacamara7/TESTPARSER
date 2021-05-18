package terrain;

public class TerrainNeutre extends terrain.TerrainNonAchetable {

    public TerrainNeutre(int numeroTerrain, String nomTerrain) {
        super(numeroTerrain, nomTerrain);
    }

    @Override
    public String toString() {
        return "TerrainNeutre{} " + super.toString();
    }
}
