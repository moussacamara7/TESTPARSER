package application.ui;

public enum nomPion {
    Chien("Chien"), Bateau("Bateau"), Brouette("Brouette"),Chapeau("Chapeau"),Chat("Chat"), Chaussure("Chaussure"),DeACoudre("DeACoudre"), Voiture("Voiture"), ;

    private final String nom;

    nomPion(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

}
