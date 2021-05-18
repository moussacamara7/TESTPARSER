package carte;



public class Liberation implements Cartes {
    private String message;

    public Liberation(String message) {
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
        return "Liberation{" +
                "message='" + message + '\'' +
                '}';
    }
}
