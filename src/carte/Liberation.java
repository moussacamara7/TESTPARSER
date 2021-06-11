package carte;


import joueur.Joueur;

public class Liberation implements Cartes {
    private String message;


    public Liberation(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void action(Joueur joueur) {
        joueur.setCarteSortirDePrison(joueur.getCarteSortirDePrison() + 1);
    }

    @Override
    public String toString() {
        return "Liberation{" +
                "message='" + message + '\'' +
                '}';
    }
}
