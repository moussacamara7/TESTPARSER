package carte;


import joueur.Joueur;

public class Liberation implements Cartes {
    private String message;
    private Joueur proprietaire;



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Joueur getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(Joueur proptietaire) {
        this.proprietaire = proptietaire;
    }

    public Liberation(String message, Joueur proprietaire) {
        this.message = message;
        setProprietaire(proprietaire);
    }

    @Override
    public void action(Joueur joueur) {

    }

    @Override
    public String toString() {
        return "Liberation{" +
                "message='" + message + '\'' +
                '}';
    }
}
