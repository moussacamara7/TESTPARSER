package terrain;

public class TerrainAction extends terrain.TerrainNonAchetable {
    /**
     * Constructeur permettant de definir un TerrainAction
     * @param numeroTerrain le numero du terrain
     * @param nomTerrain le nom du terrain
     */
    public TerrainAction(int numeroTerrain, String nomTerrain) {
        super(numeroTerrain, nomTerrain);
    }

    /**
     * @return chaine concatenee definissant un TerrainAction
     */
    @Override
    public String toString() {
        return "TerrainAction{} " + super.toString();
    }
}
