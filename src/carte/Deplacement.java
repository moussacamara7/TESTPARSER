package carte;



public class Deplacement implements Cartes{
    private String message;
    private String destination;

    public Deplacement(String message, String destination) {
        setMessage(message);
        setDestination(destination);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        if(message==null || message.trim().isEmpty())
            throw new IllegalArgumentException("Message errone");
        this.message = message;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        if(destination == null || destination.trim().isEmpty())
            throw new IllegalArgumentException("Destination errone");
        this.destination = destination;
    }

    public void action(){
        //joueur.deplacer(destination);
    }

    @Override
    public String toString() {
        return "Deplacement{" +
                "message='" + message + '\'' +
                ", destination='" + destination + '\'' +
                '}';
    }
}
