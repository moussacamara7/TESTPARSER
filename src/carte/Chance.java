package carte;


import joueur.Joueur;
import mecanismeJeu.Action;

import java.util.Random;

public class Chance implements Cartes {
    private String message;
    private int somme;

    public Chance(String message, int somme) {
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


    public void action(Joueur joueur, boolean payer) {
        Random r = new Random();
        //payer une amande de 10 euros ou piocher une carte chance
        if (payer)
            Action.retirer(somme, joueur);
        else
            Action.piocherChance(joueur);


    }

    @Override
    public String toString() {
        return "Chance{" +
                "message='" + message + '\'' +
                ", somme=" + somme +
                '}';
    }
}
//
