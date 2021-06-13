package carte;


import exception.MonopolyException;
import joueur.Joueur;
import mecanismeJeu.Action;

public class Anniversaire implements Cartes {
    private String message;
    private int somme;

    /**
     * Constructeur permettant de definir une carte Anniversaire
     * @param message le message figurant sur la carte
     * @param somme somme demandée aux joueurs
     */
    public Anniversaire(String message, int somme) {

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
     * initialise la somme demandée aux joueurs
     * @param somme la somme demandée aux joueurs
     */
    public void setSomme(int somme) {
        this.somme = somme;
    }

    /**
     * Parcours des joueurs du plateau, tous les joueurs payent celui qui a piocher la carte anniversaire
     * @param joueur joueur qui pioche la carte anniversaire et recoit l'argent
     * @throws MonopolyException renvoie un message d'erreur si les conditions ne sont pas respectées
     */
    @Override
    public void action(Joueur joueur) throws MonopolyException {
        for (Joueur j : joueur.getUIPlateau().getListeJoueurs()) {
            if (!j.equals(joueur))
                Action.payer(somme, j, joueur);
        }
    }

    /**
     * @return la chaine concaténée des informations de la carte Anniversaire
     */
    @Override
    public String toString() {
        return "Anniversaire{" +
                "message='" + message + '\'' +
                ", somme=" + somme +
                '}';
    }
}

