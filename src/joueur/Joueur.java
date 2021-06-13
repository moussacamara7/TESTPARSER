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

    /**
     * Constructeur d'un joueur
     * ce constructeur permet de choisir le nom et le capital, la position et l'etat prisonnier initial du joueur
     * ce constructeur a ete implementer dans la phase de test
     * il permet d'initialiser un joueur selon d'autre regles
     * @param nomJoueur le nom du joueur
     * @param capitalJoueur le capital du joueur
     * @param positionJoueur la position du joueur
     * @param estprisonnier l'etat prisonnier
     * @param plateau le plateau du joueur
     */
    public Joueur(String nomJoueur, int capitalJoueur, int positionJoueur, boolean estprisonnier, UIPlateau plateau) {

        setNomJoueur(nomJoueur);
        setCapitalJoueur(capitalJoueur);
        setPositionJoueur(positionJoueur);
        setEstprisonnier(estprisonnier);
        setNombreDeTourEnPrison(0);
        setCarteSortirDePrison(0);
        setPlateau(plateau);

    }

    /**
     *  Constructeur d'un joueur
     *  Ce constructeur initialise selon les regles par défaut
     *  avec un capital de 1500, une position initial de 0 (case depart)
     * @param nomJoueur le nom du joueur
     * @param plateau le plateau du joueur
     */
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
     * @return le nom du Joueur
     */
    public String getNomJoueur() {
        return nomJoueur;
    }

    /**
     * Initialise le nom du joueur
     * @param nomJoueur le nom du joueur
     */
    public void setNomJoueur(String nomJoueur) {
        if (nomJoueur == null || nomJoueur.isEmpty())
            throw new IllegalArgumentException("Erreur nom Joueur");

        this.nomJoueur = nomJoueur;
    }

    /**
     * @return le capital du joueur
     */
    public int getCapitalJoueur() {
        return capitalJoueur;
    }

    /**
     * Initialise le capital du joueur
     * @param capitalJoueur le capital du joueur
     */
    public void setCapitalJoueur(int capitalJoueur) {
        this.capitalJoueur = capitalJoueur;
    }

    /**
     * @return la liste de propriete du joueur
     */
    public ArrayList<TerrainAchetable> getProprietesJoueur() {
        return proprietesJoueur;
    }

    /**
     * @return le nombre de tour que le joueur a passe en prison
     */
    public int getNombreDeTourEnPrison() {
        return nombreDeTourEnPrison;
    }

    /**
     * Initialise le nombre de tour que le joueur a passe en prisonn
     * @param nombreDeTourEnPrison le nombre de tour que le joueur a passe en prison
     */
    public void setNombreDeTourEnPrison(int nombreDeTourEnPrison) {
        this.nombreDeTourEnPrison = nombreDeTourEnPrison;
    }

    /**
     * ajoute un propriete a celles detenues par le joueur
     * @param prop la propriete a ajoute dans la liste de propriete du joueur
     */
    public void ajouterPropriete(TerrainAchetable prop) {
        if (prop == null)
            throw new IllegalArgumentException("Erreur propriete");
        if (proprietesJoueur.contains(prop))
            throw new IllegalArgumentException("Proriete deja possede");

        proprietesJoueur.add(prop);
    }

    /**
     * @return le nombre de carte liberation du joueur
     */
    public int getCarteSortirDePrison() {
        return carteSortirDePrison;
    }

    /**
     * Initialise le nombre de carte liberation du joueur
     * @param carteSortirDePrison le nombre de carte liberation du joueu
     */
    public void setCarteSortirDePrison(int carteSortirDePrison) {
        this.carteSortirDePrison = carteSortirDePrison;
    }

    /**
     * @return la position du joueur
     */
    public int getPositionJoueur() {
        return positionJoueur;
    }

    /**
     * Initialise la position du joueur
     * @param positionJoueur la nouvelle position du joueur
     */
    public void setPositionJoueur(int positionJoueur) {
        if (positionJoueur < 0 || positionJoueur > 40)
            throw new IllegalArgumentException("Erreur position Joueur");

        this.positionJoueur = positionJoueur;
    }

    /**
     * @return l'etat du joueur (prisonnier ou non)
     */
    public boolean isEstprisonnier() {
        return estprisonnier;
    }

    /**
     * Initalise l'etat prisonnier du joueur
     * @param estprisonnier l'etat prisonnier du joueur
     */
    public void setEstprisonnier(boolean estprisonnier) {
        this.estprisonnier = estprisonnier;
    }

    /**
     * @return le plateau du joueur
     */
    public UIPlateau getUIPlateau() {
        return plateau;
    }

    /**
     * Initialise le plateau du joueur
     * @param plateau le plateau du joueur
     */
    public void setPlateau(UIPlateau plateau) {
        if (plateau == null)
            throw new IllegalArgumentException("Plateau null");
        this.plateau = plateau;
    }

    /**
     * @param o objet a comparer au joueur
     * @return renvoie vrai si l'objet est egal au joueur
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Joueur joueur = (Joueur) o;
        return capitalJoueur == joueur.capitalJoueur && positionJoueur == joueur.positionJoueur && estprisonnier == joueur.estprisonnier && Objects.equals(nomJoueur, joueur.nomJoueur) && Objects.equals(proprietesJoueur, joueur.proprietesJoueur) && Objects.equals(plateau, joueur.plateau);
    }

    /**
     * @return la chaine concaténée des informations du joueur
     */
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
