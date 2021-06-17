package application.ui;

import exception.MonopolyException;

import java.util.ArrayList;

public class UICase {

    private final ArrayList<Pion> listePions = new ArrayList<>();
    public int x1, y1, x2, y2;

    /**
     * Constructeur d'une UICase
     */
    public UICase() {
    }

    /**
     * Initialise les coordonnees de la case
     * @param x1 coordonnees x1
     * @param y1 coordonnees y1
     * @param x2 coordonnees x2
     * @param y2 coordonnees y2
     */
    public void setCoordonnees(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    /**
     * Ajoute un pion dans la liste de pions de la case
     * @param pion le pion a poser
     */
    public void poser(Pion pion) {
        if (pion == null)
            throw new IllegalArgumentException("Le pion ne peu pas être null");

        listePions.add(pion);
    }

    /**
     * Retire un pion de la liste de pions de la case
     * @param pion le pion a retirer
     */
    public void enlever(Pion pion) {
        if (pion == null)
            throw new IllegalArgumentException("Le pion ne peu pas être null");

        if (!listePions.contains(pion))
            try {
                throw new MonopolyException("Le pion n'est pas sur cette case");
            } catch (MonopolyException e) {
                e.printStackTrace();
            }
        listePions.remove(pion);
    }

    /**
     * @return la liste de pion sur la case
     */
    public ArrayList<Pion> getListePions() {
        return listePions;
    }

    /**
     * @return le nombre de pion sur la case
     */
    public int getNombrePion() {
        return listePions.size();
    }

    /**
     * vide la liste de pion de la case
     */
    public void vider() {
        listePions.clear();
    }
}
