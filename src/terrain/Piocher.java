package terrain;

public class Piocher extends TerrainNonAchetable {

    /**
     * constructeur permettant de definir un terrain demandant au joueur de piocher
     * @param numeroTerrain le numero du terrain
     * @param nomTerrain le nome du terrain
     */
    public Piocher(int numeroTerrain, String nomTerrain) {
        super(numeroTerrain, nomTerrain);
    }

    /**
     * @return chaine concatenee definissant piocher
     */
    @Override
    public String toString() {
        return "Piocher{} " + super.toString();
    }
}
