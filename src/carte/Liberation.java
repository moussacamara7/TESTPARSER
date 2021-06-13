package carte;


import joueur.Joueur;

public class Liberation implements Cartes {
    private String message;

    /**
     * Constructeur permettant de definir une carte Liberation
     * @param message le message figurant sur la carte
     */
    public Liberation(String message) {
        this.message = message;
    }

    /**
     * @return le message figurant sur la carte
     */
    public String getMessage() {
        return message;
    }

    /**
     * Intialise le message figurant sur la carte
     * @param message le message figurant sur la carte
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * le joueur qui a pioche la carte recoit une carte liberation
     * @param joueur joueur beneficiant de la carte liberation
     */
    @Override
    public void action(Joueur joueur) {
        joueur.setCarteSortirDePrison(joueur.getCarteSortirDePrison() + 1);
    }

    /**
     * @return la chaine concaténée des informations de la carte Liberation
     */
    @Override
    public String toString() {
        return "Liberation{" +
                "message='" + message + '\'' +
                '}';
    }
}
