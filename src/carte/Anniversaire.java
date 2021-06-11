package carte;


import joueur.Joueur;
import mecanismeJeu.Action;

public class Anniversaire implements Cartes {
    private String message;
    private int somme;

    public Anniversaire(String message, int somme) {

        setMessage(message);
        setSomme(somme);
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    public void setSomme(int somme) {
        this.somme = somme;
    }

    @Override
    public void action(Joueur joueur) throws Exception {
        for (Joueur j : joueur.getUIPlateau().getListeJoueurs()) {
            if (!j.equals(joueur))
                Action.payer(somme, j, joueur);
        }
        //Parcours des joueurs via l'ArrayList de plateau en diminuant leur capital de 10euros sauf le joueur
    }

    @Override
    public String toString() {
        return "Anniversaire{" +
                "message='" + message + '\'' +
                ", somme=" + somme +
                '}';
    }
}

