package carte;


import joueur.Joueur;
import mecanismeJeu.Action;

public class Chance implements Cartes {
    private String message;
    private int somme;

    /**
     * Constructeur permettant de definir une carte Chance
     * @param message le message figurant sur la carte
     * @param somme somme à payer si le joueur decide de payer au lieu de piocher
     */
    public Chance(String message, int somme) {
        setMessage(message);
        setSomme(somme);
    }

    /**
     *
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
     * initialise la somme a payer
     * @param somme la somme a payer
     */
    public void setSomme(int somme) {
        this.somme = somme;
    }

    /**
     * le joueur doit payer une amande de 10 euros ou piocher une carte chance
     * @param joueur joueur qui pioche la carte Caisse de Communaute et choisit de payer 10 ou piocher une carte Chance
     * @param payer vrai si le joueur decide de payer 10, sinon il pioche une carte Chance
     */
    public void action(Joueur joueur, boolean payer) {
        if (payer)
            Action.retirer(somme, joueur);
        else {
            Action.piocherChance(joueur);
        }
    }

    /**
     * @return la chaine concaténée des informations de la carte Chance
     */
    @Override
    public String toString() {
        return "Chance{" +
                "message='" + message + '\'' +
                ", somme=" + somme +
                '}';
    }
}

