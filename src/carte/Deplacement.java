package carte;


import exception.MonopolyException;
import joueur.Joueur;
import mecanismeJeu.Action;


public class Deplacement implements Cartes {
    private String message;
    private String destination;

    /**
     * Constructeur permettant de definir une carte Deplacement
     * @param message le message figurant sur la carte
     * @param destination le nom de la case ou le joueur est deplacer
     */
    public Deplacement(String message, String destination) {
        setMessage(message);
        setDestination(destination);
    }

    /**
     * @return le message figurant sur la carte
     */
    public String getMessage() {
        return message;
    }

    /**
     Initialise le message figurant sur la carte
     * @param message le message figurant sur la carte
     */
    public void setMessage(String message) {
        if (message == null || message.trim().isEmpty())
            throw new IllegalArgumentException("Message errone");
        this.message = message;
    }

    /**
     * Initialise le nom de la case ou le joueur est deplacer
     * @param destination le nom de la case ou le joueur est deplacer
     */
    public void setDestination(String destination) {
        if (destination == null || destination.trim().isEmpty())
            throw new IllegalArgumentException("Destination errone");
        this.destination = destination;
    }

    /**
     * Le joueur est deplacer sur la case destination selon les conditions de la carte
     * @param joueur joueur qui se fait deplacer
     * @throws MonopolyException renvoie un message d'erreur si les conditions ne sont pas respectées
     */
    public void action(Joueur joueur) throws MonopolyException {
        //joueur.deplacer(destination);
        switch (destination) {
            case "PRISON":
                Action.allerEnPrison(joueur);
                break;
            case "CASE DEPART":
                Action.deplacer(joueur, 0);
                break;
            case "RUE DE LA PAIX":
                Action.deplacer(joueur, 39);
                break;
            case "3 CASES":
                joueur.setPositionJoueur(joueur.getPositionJoueur() - 3);
                //sinon il gagne 200 car sa nvpos < pos
                break;
            case "GARE DE LYON":
                Action.deplacer(joueur, 15);
                break;
            case "AVENUE HENRI-MARTIN":
                Action.deplacer(joueur, 24);
                break;
            case "BOULEVARD DE LA VILLETTE":
                Action.deplacer(joueur, 11);
                break;
            case "BOULEVARD DE BELLEVILLE":
                Action.deplacer(joueur, 1);
                break;
        }

    }

    /**
     * @return la chaine concaténée des informations de la carte Deplacement
     */
    @Override
    public String toString() {
        return "Deplacement{" +
                "message='" + message + '\'' +
                ", destination='" + destination + '\'' +
                '}';
    }
}

