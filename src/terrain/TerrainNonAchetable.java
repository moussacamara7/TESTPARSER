package terrain;


public abstract class TerrainNonAchetable implements Terrain {
    private final int numeroTerrain;
    private final String nomTerrain;

    public TerrainNonAchetable(int numeroTerrain, String nomTerrain) {
        this.numeroTerrain = numeroTerrain;
        this.nomTerrain = nomTerrain;
    }

    public String getNomTerrain() {
        return nomTerrain;
    }

    public boolean estAchetable() {
        return false;
    }

    @Override
    public String toString() {
        return "TerrainNonAchetable{" +
                "numeroTerrain=" + numeroTerrain +
                ", nomTerrain='" + nomTerrain + '\'' +
                '}';
    }
}
