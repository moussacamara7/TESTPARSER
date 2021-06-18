package terrain;

import exception.MonopolyException;
import joueur.Joueur;
import mecanismeJeu.Action;

public class Compagnie extends TerrainAchetable {

    private int loyer;

    /**
     * Constructeur permettant de definir une compagnie
     * @param numeroTerrain numero du terrain
     * @param nomTerrain nom du terrain
     * @param prixAchat prix d'achat terrain
     */
    public Compagnie(int numeroTerrain, String nomTerrain, int prixAchat) {
        super(numeroTerrain, nomTerrain, prixAchat);
        setLoyer(20);
    }

    /**
     * @return le loyer du terrain
     */
    public int getLoyer() {
        return loyer;
    }

    /**
     * Initialise le loyer
     * @param loyer loyer du terrain
     */
    public void setLoyer(int loyer) {
        this.loyer = loyer;
    }

    /**
     *
     * @return renvoie faux car c'est une compagnie
     */
    public boolean estConstructible() {
        return false;
    }

    @Override
    public void action(Joueur joueur) {
        if(this.aUnProprietaire() && ! this.getProprietaire().equals(joueur)){
            try {
                Action.payer(this.getLoyer(), joueur, this.getProprietaire());
            } catch (MonopolyException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @return chaine concaténée definissant la compagnie
     */
    @Override
    public String toString() {
        return "Compagnie{} " + super.toString();
    }
}
