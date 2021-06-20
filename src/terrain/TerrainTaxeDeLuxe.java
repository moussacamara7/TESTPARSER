package terrain;

import joueur.Joueur;
import mecanismeJeu.Action;

public class TerrainTaxeDeLuxe extends TerrainNonAchetable{
    public static final int TAXE_DE_LUXE = 100;


    /**
     * Constructeur permettant de definir un terrain non achetable
     *
     * @param numeroTerrain numero du terrain
     * @param nomTerrain    nom du terrain
     */
    public TerrainTaxeDeLuxe(int numeroTerrain, String nomTerrain) {
        super(numeroTerrain, nomTerrain);
    }

    @Override
    public void action(Joueur joueur) {
        joueur.getUIPlateau().getMonopoly().getMessageFooter().setText("Vous payez la taxe de luxe");
        Action.retirer(TAXE_DE_LUXE, joueur);
    }
}
