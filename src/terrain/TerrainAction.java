package terrain;

import joueur.Joueur;
import mecanismeJeu.Action;

public class TerrainAction extends terrain.TerrainNonAchetable {

    public static final int TAXE_DE_LUXE = 100;
    public static final int TAXE_SUR_LE_REVENU = 200;

    /**
     * Constructeur permettant de definir un TerrainAction
     * @param numeroTerrain le numero du terrain
     * @param nomTerrain le nom du terrain
     */
    public TerrainAction(int numeroTerrain, String nomTerrain) {
        super(numeroTerrain, nomTerrain);
    }

    @Override
    public void action(Joueur joueur) {
        switch (this.getNomTerrain()) {
            case "TAXE DE LUXE":
                joueur.getUIPlateau().getMonopoly().getMessageFooter().setText("Vous payez la taxe de luxe");
                Action.retirer(TAXE_DE_LUXE, joueur);
                break;
            case "IMPOT SUR LE REVENU":
                joueur.getUIPlateau().getMonopoly().getMessageFooter().setText("Vous payez la taxe sur le revenue");
                Action.retirer(TAXE_SUR_LE_REVENU, joueur);
                break;
            case "ALLEZ EN PRISON":
                joueur.getUIPlateau().getMonopoly().getMessageFooter().setText("Vous allez en prison");
                Action.allerEnPrison(joueur);
                break;
        }
    }

    /**
     * @return chaine concatenee definissant un TerrainAction
     */
    @Override
    public String toString() {
        return "TerrainAction{} " + super.toString();
    }
}
