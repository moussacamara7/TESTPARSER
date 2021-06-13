package carte;

import joueur.Joueur;
import mecanismeJeu.Action;

public class Payer implements Cartes {
    private String message;
    private int somme;

    /**
     * Constructeur permettant de definir une carte Payer
     * @param message le message figurant sur la carte
     * @param somme somme retire au joueur
     */
    public Payer(String message, int somme) {
        setMessage(message);
        setSomme(somme);
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
     * @return la somme a retirer au joueur
     */
    public int getSomme() {
        return somme;
    }

    /**
     * Initialise la somme a retirer au joueur
     * @param somme la somme a retirer au joueur
     */
    public void setSomme(int somme) {
        this.somme = somme;
    }

    /**
     * retire la somme au joueur qui a pioche la carte Payer
     * @param joueur joueur qui  a pioche la carte Payer
     */
    @Override
    public void action(Joueur joueur) {

        Action.retirer(getSomme(), joueur);
    }

    /**
     * @return la chaine concaténée des informations de la carte Payer
     */
    @Override
    public String toString() {
        return "Payer{" +
                "message='" + message + '\'' +
                ", somme=" + somme +
                '}';
    }
}
