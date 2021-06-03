package carte;

import joueur.Joueur;
import mecanismeJeu.Action;

import joueur.Joueur;

public class Payer implements Cartes{
    private String message;
    private int somme;

    public Payer(String message, int somme) {
        setMessage(message);
        setSomme(somme);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getSomme() {
        return somme;
    }

    public void setSomme(int somme) {
        this.somme = somme;
    }

    @Override
    public void action(Joueur joueur) {

        Action.retirer(getSomme(),joueur);
    }

    @Override
    public String toString() {
        return "Payer{" +
                "message='" + message + '\'' +
                ", somme=" + somme +
                '}';
    }
}
