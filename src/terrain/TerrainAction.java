package terrain;

public class TerrainAction extends terrain.TerrainNonAchetable {
    public TerrainAction(int numeroTerrain, String nomTerrain) {
        super(numeroTerrain, nomTerrain);
    }

    @Override
    public String toString() {
        return "TerrainAction{} " + super.toString();
    }
}
