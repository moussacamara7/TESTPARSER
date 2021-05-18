package carte;



public class Impot implements Cartes{
    private String message;

    public Impot(String message) {
        setMessage(message);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void action() {

    }

    @Override
    public String toString() {
        return "Impot{" +
                "message='" + message + '\'' +
                '}';
    }
}
