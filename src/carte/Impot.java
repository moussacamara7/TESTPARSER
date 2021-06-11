package carte;


import joueur.Joueur;
import mecanismeJeu.Action;
import terrain.TerrainAchetable;
import terrain.TerrainConstructible;

public class Impot implements Cartes {
    private String message;

    public Impot(String message) {
        setMessage(message);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void action(Joueur joueur) {
        int nbMaison = 0, nbHotel = 0;
        //Parcours des propriétés du joueur
        for (TerrainAchetable t : joueur.getProprietesJoueur()) {
            if(t instanceof TerrainConstructible) {
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

    @Override
    public String toString() {
        return "Impot{" +
                "message='" + message + '\'' +
                '}';
    }
}
