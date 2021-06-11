package joueur;

import application.ui.UIPlateau;
import terrain.TerrainAchetable;

import java.util.ArrayList;
import java.util.Objects;

public class Joueur {

    private final ArrayList<TerrainAchetable> proprietesJoueur = new ArrayList<>();
    private String nomJoueur;
    private int capitalJoueur;
    private int positionJoueur;
    private boolean estprisonnier;
    private UIPlateau plateau;
    private int carteSortirDePrison;
    private int nombreDeTourEnPrison;


    public Joueur(String nomJoueur, int capitalJoueur, int positionJoueur, boolean estprisonnier, UIPlateau plateau) {

        setNomJoueur(nomJoueur);
        setCapitalJoueur(capitalJoueur);
        setPositionJoueur(positionJoueur);
        setEstprisonnier(estprisonnier);
        setNombreDeTourEnPrison(0);
        setCarteSortirDePrison(0);
        setPlateau(plateau);

    }

    public Joueur(String nomJoueur, UIPlateau plateau) {

        setNomJoueur(nomJoueur);
        setCapitalJoueur(1500);
        setPositionJoueur(0);
        setEstprisonnier(false);
        setNombreDeTourEnPrison(0);
        setCarteSortirDePrison(0);
        setPlateau(plateau);
    }


    /**
     * @return the nomJoueur
     */
    public String getNomJoueur() {
        return nomJoueur;
    }

    /**
     * @param nomJoueur the nomJoueur to set
     */
    public void setNomJoueur(String nomJoueur) {
        if (nomJoueur == null || nomJoueur.isEmpty())
            throw new IllegalArgumentException("Erreur nom Joueur");

        this.nomJoueur = nomJoueur;
    }

    /**
     * @return the capitalJoueur
     */
    public int getCapitalJoueur() {
        return capitalJoueur;
    }

    /**
     * @param capitalJoueur the capitalJoueur to set
     */
    public void setCapitalJoueur(int capitalJoueur) {
        this.capitalJoueur = capitalJoueur;
    }

    /**
     * @return the proprietesJoueur
     */
    public ArrayList<TerrainAchetable> getProprietesJoueur() {
        return proprietesJoueur;
    }

    public int getNombreDeTourEnPrison() {
        return nombreDeTourEnPrison;
    }

    public void setNombreDeTourEnPrison(int nombreDeTourEnPrison) {
        this.nombreDeTourEnPrison = nombreDeTourEnPrison;
    }

    /**
     * @param prop the proprietesAAjouter to set
     */

    public void ajouterPropriete(TerrainAchetable prop) {
        if (prop == null)
            throw new IllegalArgumentException("Erreur propriete");
        if (proprietesJoueur.contains(prop))
            throw new IllegalArgumentException("Proriete deja possede");

        proprietesJoueur.add(prop);
    }

    public int getCarteSortirDePrison() {
        return carteSortirDePrison;
    }

    public void setCarteSortirDePrison(int carteSortirDePrison) {
        this.carteSortirDePrison = carteSortirDePrison;
    }

    /**
     * @return the positionJoueur
     */
    public int getPositionJoueur() {
        return positionJoueur;
    }

    /**
     * @param positionJoueur the positionJoueur to set
     */
    public void setPositionJoueur(int positionJoueur) {
        if (positionJoueur < 0 || positionJoueur > 40)
            throw new IllegalArgumentException("Erreur position Joueur");

        this.positionJoueur = positionJoueur;
    }

    /**
     * @return the estprisonnier
     */
    public boolean isEstprisonnier() {
        return estprisonnier;
    }

    /**
     * @param estprisonnier the estprisonnier to set
     */
    public void setEstprisonnier(boolean estprisonnier) {
        this.estprisonnier = estprisonnier;
    }

    public UIPlateau getUIPlateau() {
        return plateau;
    }

    public void setPlateau(UIPlateau plateau) throws IllegalArgumentException {
        if (plateau == null)
            throw new IllegalArgumentException("Plateau null");
        this.plateau = plateau;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Joueur joueur = (Joueur) o;
        return capitalJoueur == joueur.capitalJoueur && positionJoueur == joueur.positionJoueur && estprisonnier == joueur.estprisonnier && Objects.equals(nomJoueur, joueur.nomJoueur) && Objects.equals(proprietesJoueur, joueur.proprietesJoueur) && Objects.equals(plateau, joueur.plateau);
    }

    @Override
    public String toString() {
        return "Joueur{" +
                "nomJoueur='" + nomJoueur + '\'' +
                ", capitalJoueur=" + capitalJoueur +
                ", proprietesJoueur=" + proprietesJoueur +
                ", positionJoueur=" + positionJoueur +
                ", estprisonnier=" + estprisonnier +
                ", plateau=" + plateau +
                '}';
    }
}
