package terrain;


public abstract class TerrainNonAchetable implements Terrain{
    private int numeroTerrain;
    private String nomTerrain;

    public TerrainNonAchetable(int numeroTerrain, String nomTerrain) {
        this.numeroTerrain = numeroTerrain;
        this.nomTerrain = nomTerrain;
    }

    public int getNumeroTerrain() {
        return numeroTerrain;
    }

    public void setNumeroTerrain(int numeroTerrain) {
        this.numeroTerrain = numeroTerrain;
    }

    public String getNomTerrain() {
        return nomTerrain;
    }

    public void setNomTerrain(String nomTerrain) {
        this.nomTerrain = nomTerrain;
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
