package terrain;

import joueur.Joueur;
import mecanismeJeu.Action;

public class TerrainImpotSurLeRevenu extends TerrainNonAchetable{
    public static final int TAXE_SUR_LE_REVENU = 200;


    /**
     * Constructeur permettant de definir un terrain non achetable
     *
     * @param numeroTerrain numero du terrain
     * @param nomTerrain    nom du terrain
     */
    public TerrainImpotSurLeRevenu(int numeroTerrain, String nomTerrain) {
        super(numeroTerrain, nomTerrain);
    }

    @Override
    public void action(Joueur joueur) {
        joueur.getUIPlateau().getMonopoly().getMessageFooter().setText("Vous payez la taxe sur le revenue");
        Action.retirer(TAXE_SUR_LE_REVENU, joueur);

    }
}
