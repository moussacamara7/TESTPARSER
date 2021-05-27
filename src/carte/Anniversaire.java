package carte;


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


    public int getSomme() {
        return somme;
    }


    public void setSomme(int somme) {
        this.somme = somme;
    }

    @Override
    public void action() {
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
