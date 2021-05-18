package joueur;

import terrain.TerrainAchetable;

import java.util.ArrayList;
import java.util.Objects;

public class Joueur {

    private String nomJoueur;
    private int capitalJoueur;
    private final ArrayList<TerrainAchetable> proprietesJoueur = new ArrayList<TerrainAchetable>();
    private int positionJoueur;
    private boolean estprisonnier;





    public Joueur(String nomJoueur, int capitalJoueur, int positionJoueur, boolean estprisonnier) {

        setNomJoueur(nomJoueur);
        setCapitalJoueur(capitalJoueur);
        setPositionJoueur(positionJoueur);
        setEstprisonnier(estprisonnier);
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
        if(nomJoueur == null || nomJoueur.isEmpty())
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

    /**
     * @param prop the proprietesAAjouter to set
     */

    public void ajouterPropriete(TerrainAchetable prop) {
        if(prop == null)
            throw new IllegalArgumentException("Erreur propriete");
        if(proprietesJoueur.contains(prop))
            throw new IllegalArgumentException("Proriete deja possede");

        proprietesJoueur.add(prop);
    }

    public int getNombreProprietes() {
        return proprietesJoueur.size();
    }

    public TerrainAchetable getPropriete(int i) {
        if(i<0 || i> getNombreProprietes())
            throw new IllegalArgumentException("Propriete inaccessible");
        return proprietesJoueur.get(i);
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
        if(positionJoueur<0 || positionJoueur>40)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Joueur joueur = (Joueur) o;
        return capitalJoueur == joueur.capitalJoueur && positionJoueur == joueur.positionJoueur && estprisonnier == joueur.estprisonnier && Objects.equals(nomJoueur, joueur.nomJoueur) && Objects.equals(proprietesJoueur, joueur.proprietesJoueur);
    }

    @Override
    public String toString() {
        return "Joueur{" +
                "nomJoueur='" + nomJoueur + '\'' +
                ", capitalJoueur=" + capitalJoueur +

                ", positionJoueur=" + positionJoueur +
                ", estprisonnier=" + estprisonnier +
                ", proprietesJoueur=\n\t" + proprietesJoueur +
                "}\n";
    }
}
