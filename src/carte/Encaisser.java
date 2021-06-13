package carte;


import joueur.Joueur;
import mecanismeJeu.Action;

public class Encaisser implements Cartes {
    private String message;
    private int somme;

    /**
     * Constructeur permettant de definir une carte Encaisser
     * @param message le message figurant sur la carte
     * @param somme la somme que le joueur recoit
     */
    public Encaisser(String message, int somme) {
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
     * Initialise la somme que le joueur recoir
     * @param somme que le joueur recoit
     */
    public void setSomme(int somme) {
        this.somme = somme;
    }

    /**
     * le joueur qui pioche la carte recoit la somme
     * @param joueur joueur qui a piocher la carte et recoit l'argent
     */
    @Override
    public void action(Joueur joueur) {
        Action.payer(somme, joueur);

    }

    /**
     * @return la chaine concaténée des informations de la carte Deplacement
     */
    @Override
    public String toString() {
        return "Encaisser{" +
                "message='" + message + '\'' +
                ", somme=" + somme +
                '}';
    }
}

