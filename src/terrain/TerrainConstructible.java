package terrain;

import application.ui.UIPlateau;
import exception.MonopolyException;
import joueur.Joueur;
import mecanismeJeu.Action;

public class TerrainConstructible extends TerrainAchetable {


    private String couleur;
    private int nombreMaison;
    private int prixAchatMaison;
    private Loyer loyer;


    /**
     * Constructeur permettant de definir un TerrainConstructible
     * @param numeroTerrain le numero du terrain
     * @param nomTerrain le nom du terrain
     * @param prixAchat le prix d'achat du terrain
     * @param couleur la couleur du terrain
     * @param nombreMaison le nombre de maison sur le terrain
     * @param prixAchatMaison le prix d'achat des maisons
     * @param loyer le loyer du terrain
     */
    public TerrainConstructible(int numeroTerrain, String nomTerrain, int prixAchat, String couleur, int nombreMaison, int prixAchatMaison, Loyer loyer) {
        super(numeroTerrain, nomTerrain, prixAchat);
        this.couleur = couleur;
        this.nombreMaison = nombreMaison;
        this.prixAchatMaison = prixAchatMaison;
        this.loyer = loyer;
    }

    /**
     * @return la couleur du terrain
     */
    public String getCouleur() {
        return couleur;
    }

    /**
     * @return le nombre de maisons sur le terrain
     */
    public int getNombreMaison() {
        return nombreMaison;
    }

    /**
     * Initialise le nombre de maison sur le terrain
     * @param nombreMaison le nombre de maison sur le terrain
     */
    public void setNombreMaison(int nombreMaison) {
        this.nombreMaison = nombreMaison;
    }

    /**
     * @return le prx d'achate d'une maison
     */
    public int getPrixAchatMaison() {
        return prixAchatMaison;
    }

    /**
     * @return le loyer du terrain
     */
    public Loyer getLoyer() {
        return loyer;
    }

    /**
     * @return vrai car c'est un terrainConstructible
     */
    public boolean estConstructible() {
        return true;
    }

    public void construire(Joueur joueur){
        UIPlateau plateau = joueur.getUIPlateau();
        if (!Action.peutConstruire(getNumeroTerrain(), plateau))
            throw new IllegalArgumentException(("terrain non eligible a la construction"));
        Action.retirer(getPrixAchatMaison(), getProprietaire());
        setNombreMaison(getNombreMaison() + 1);

    }

    @Override
    public void action(Joueur joueur) {
        if(this.aUnProprietaire() && ! this.getProprietaire().equals(joueur)) {
            int prixLoyer = 0;
            switch (this.getNombreMaison()) {
                case 0:
                    prixLoyer = this.getLoyer().getPrixAucuneMaison();
                    break;
                case 1:
                    prixLoyer = this.getLoyer().getPrixUnemaison();
                    break;
                case 2:
                    prixLoyer = this.getLoyer().getPrixDeuxMaison();
                    break;
                case 3:
                    prixLoyer = this.getLoyer().getPrixTroisMaison();
                    break;
                case 4:
                    prixLoyer = this.getLoyer().getPrixQuatreMaison();
                    break;
                case 5:
                    prixLoyer = this.getLoyer().getPrixHotel();
                    break;
            }
            try {
                Action.payer(prixLoyer, joueur, this.getProprietaire());
            } catch (MonopolyException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @return Chaine concatenee definissant TerrainConsrtuctible
     */
    @Override
    public String toString() {
        return "TerrainConstructible{" +
                "couleur='" + couleur + '\'' +
                ", nombreMaison=" + nombreMaison +
                ", prixAchatMaison=" + prixAchatMaison +
                ", loyer=" + loyer +
                "} " + super.toString();
    }
}
