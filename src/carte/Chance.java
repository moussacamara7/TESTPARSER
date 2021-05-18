package carte;


public class Chance implements Cartes{
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

    @Override
    public void action() {

    }

    @Override
    public String toString() {
        return "Chance{" +
                "message='" + message + '\'' +
                ", somme=" + somme +
                '}';
    }
}
