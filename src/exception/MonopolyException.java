package exception;

public class MonopolyException extends Exception {

    /**
     * Constructeur de MonopolyExceptions
     * @param message le message a afficher
     */
    public MonopolyException(String message) {
        super(message);
    }

}
