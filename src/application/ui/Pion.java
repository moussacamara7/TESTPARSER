package application.ui;


import java.util.Objects;

/**
 * Cette classe existe peut-être dans votre projet. Si c'est le cas, n'utiliser pas la classe ci-dessous mais la vôtre
 *
 * @author YL
 */
public class Pion {
    private String nom = "rien";
    private int position = 0;


    public Pion(String nom) {
        setNom(nom);
    }

    public String getNom() {
        return nom;
    }

    private void setNom(String nom) {
        if (nom == null || nom.trim().equals(""))
            throw new IllegalArgumentException("Le nom du pion ne peut pas être vide ou null");
        this.nom = nom;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        if (position < 0 || position > 40)
            throw new IllegalArgumentException("Le numéro de la case est faux");

        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pion pion = (Pion) o;
        return Objects.equals(nom, pion.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }
}
