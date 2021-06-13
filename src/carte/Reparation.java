package carte;


import joueur.Joueur;
import mecanismeJeu.Action;
import terrain.TerrainAchetable;
import terrain.TerrainConstructible;

public class Reparation implements Cartes {
    private String message;

    /**
     * Constructeur permettant de definir une carte Reparation
     * @param message le message figurant sur la carte
     */
    public Reparation(String message) {
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
     * Parcours les proprietes du joueur et lui fait payer les reparations selon le nombre de maison et hotel
     * @param joueur joueur qui paye les reparations
     */
    @Override
    public void action(Joueur joueur) {
        int nbMaison = 0, nbHotel = 0;
        //Parcours des propriétés du joueur
        for (TerrainAchetable t : joueur.getProprietesJoueur()) {
            TerrainConstructible tc = (TerrainConstructible) t;
            if (tc.getNombreMaison() == 5) {          //Cas d'un hotel
                nbHotel++;
            } else {                                   //Cas des maisons
                nbMaison += tc.getNombreMaison();
            }
        }
        Action.retirer(25 * nbMaison, joueur);
        Action.retirer(100 * nbHotel, joueur);
    }

    /**
     * @return la chaine concaténée des informations de la carte Reparation
     */
    @Override
    public String toString() {
        return "Reparation{" +
                "message='" + message + '\'' +
                '}';
    }
}
