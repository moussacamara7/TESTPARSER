package terrain;

public class TerrainNeutre extends terrain.TerrainNonAchetable {

    /**
     * Constructeur permettant de definir un terrain neutre
     * @param numeroTerrain numero du terrain
     * @param nomTerrain nom du terrain
     */
    public TerrainNeutre(int numeroTerrain, String nomTerrain) {
        super(numeroTerrain, nomTerrain);
    }

    /**
     * @return Chaine concatenee definissant terrain neutre
     */
    @Override
    public String toString() {
        return "TerrainNeutre{} " + super.toString();
    }
}
