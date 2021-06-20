package terrain;

import joueur.Joueur;
import mecanismeJeu.Action;

public class TerrainAllerEnPrison extends TerrainNonAchetable{

    /**
     * Constructeur permettant de definir un terrain non achetable
     *
     * @param numeroTerrain numero du terrain
     * @param nomTerrain    nom du terrain
     */
    public TerrainAllerEnPrison(int numeroTerrain, String nomTerrain) {
        super(numeroTerrain, nomTerrain);
    }

    @Override
    public void action(Joueur joueur) {
        joueur.getUIPlateau().getMonopoly().getMessageFooter().setText("Vous allez en prison");
        Action.allerEnPrison(joueur);
    }
}
