package terrain;


import exception.MonopolyException;
import joueur.Joueur;
import mecanismeJeu.Action;

public class Gare extends TerrainAchetable {

    private int loyer;

    /**
     * Constructeur permettant de definir une gare
     * @param numeroTerrain numero du terrain
     * @param nomTerrain nom du terrain
     * @param prixAchat prix d'achat terrain
     */
    public Gare(int numeroTerrain, String nomTerrain, int prixAchat) {
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
     * @return renvoie faux car c'est une gare
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
     * @return chaine concaténée definissant la gare
     */
    @Override
    public String toString() {
        return "Gare{} " + super.toString();
    }
}
