package terrain;

public class TerrainConstructible extends TerrainAchetable {


    private String couleur;
    private int nombreMaison;
    private int prixAchatMaison;
    private Loyer loyer;

    public TerrainConstructible(int numeroTerrain, String nomTerrain, int prixAchat, String couleur, int nombreMaison, int prixAchatMaison, Loyer loyer) {
        super(numeroTerrain, nomTerrain, prixAchat);
        setCouleur(couleur);
        setNombreMaison(nombreMaison);
        setPrixAchatMaison(prixAchatMaison);
        setLoyer(loyer);
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public int getNombreMaison() {
        return nombreMaison;
    }

    public void setNombreMaison(int nombreMaison) {
        this.nombreMaison = nombreMaison;
    }

    public int getPrixAchatMaison() {
        return prixAchatMaison;
    }

    public void setPrixAchatMaison(int prixAchatMaison) {
        this.prixAchatMaison = prixAchatMaison;
    }

    public Loyer getLoyer() {
        return loyer;
    }

    public void setLoyer(Loyer loyer) {
        this.loyer = loyer;
    }

    public boolean estConstructible() {
        return true;
    }

    @Override
    public String toString() {
        return "TerrainConstructible{" +
                "couleur='" + couleur + '\'' +
                ", nombreMaison=" + nombreMaison +
                ", prixAchatMaison=" + prixAchatMaison +
                ", loyer=" + loyer +
                "} " + super.toString();
    }
}
