package carte;


import joueur.Joueur;
import mecanismeJeu.Action;
import terrain.TerrainAchetable;
import terrain.TerrainConstructible;

public class Impot implements Cartes {
    private String message;

    /**
     * Constructeur permettant de definir une carte Impot
     * @param message le message figurant sur la carte
     */
    public Impot(String message) {
        setMessage(message);
    }

    /**
     * @return le message figurant sur la carte
     */
    public String getMessage() {
        return message;
    }

    /**
     * Initialise le message figurant sur la carte
     * @param message le message figurant sur la carte
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Parcours les proprietes du joueur et lui fait payer les impots selon le nombre de maison et hotel
     * @param joueur joueur qui paye les impots
     */
    @Override
    public void action(Joueur joueur) {
        int nbMaison = 0, nbHotel = 0;
        //Parcours des propriétés du joueur
        for (TerrainAchetable t : joueur.getProprietesJoueur()) {
            if (t instanceof TerrainConstructible) {
                TerrainConstructible tc = (TerrainConstructible) t;
                if (tc.getNombreMaison() == 5) {          //Cas d'un hotel
                    nbHotel++;
                } else {                                   //Cas des maisons
                    nbMaison += tc.getNombreMaison();
                }
            }
        }
        Action.retirer(40 * nbMaison, joueur);
        Action.retirer(115 * nbHotel, joueur);
    }

    /**
     * @return la chaine concaténée des informations de la carte Impot
     */
    @Override
    public String toString() {
        return "Impot{" +
                "message='" + message + '\'' +
                '}';
    }
}
