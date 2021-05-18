package carte;



public class Reparation implements Cartes{
    private String message;

    public Reparation(String message) {
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
        return "Reparation{" +
                "message='" + message + '\'' +
                '}';
    }
}
