package application.ui;

public enum nomPion {
    Chien("Chien"), Bateau("Bateau"), Brouette("Brouette"), Chapeau("Chapeau"), Chat("Chat"), Chaussure("Chaussure"), DeACoudre("DeACoudre"), Voiture("Voiture"),
    ;

    private final String nom;

    /**
     * Initalise le nom du pion
     * @param nom le nom du pion
     */
    nomPion(String nom) {
        this.nom = nom;
    }

    /**
     * @return le nom du pion
     */
    public String getNom() {
        return nom;
    }

}
