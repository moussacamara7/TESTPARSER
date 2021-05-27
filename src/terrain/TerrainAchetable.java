package terrain;

import joueur.Joueur;

public abstract class TerrainAchetable implements Terrain{
    private int numeroTerrain;
    private String nomTerrain;
    private int prixAchat;
    private Joueur proprietaire;

    public TerrainAchetable(int numeroTerrain, String nomTerrain, int prixAchat) {
        setNumeroTerrain(numeroTerrain);
        setNomTerrain(nomTerrain);
        setPrixAchat(prixAchat);
        setProprietaire(null);
    }

    public void setNumeroTerrain(int numeroTerrain) {
        this.numeroTerrain = numeroTerrain;
    }

    public void setNomTerrain(String nomTerrain) {
        this.nomTerrain = nomTerrain;
    }

    public void setPrixAchat(int prixAchat) {
        this.prixAchat = prixAchat;
    }


    public int getNumeroTerrain() {
        return numeroTerrain;
    }

    public String getNomTerrain() {
        return nomTerrain;
    }

    public int getPrixAchat() {
        return prixAchat;
    }

    public void setProprietaire(Joueur proprietaire) {
        this.proprietaire = proprietaire;
    }

    public Joueur getProprietaire() {
        return proprietaire;
    }

    public boolean estAchetable() {
        return true;
    }

    public boolean aUnProprietaire(){
        return this.proprietaire != null;
    }

    public abstract boolean estConstructible();

    @Override
    public String toString() {
        return "TerrainAchetable{" +
                "numeroTerrain=" + numeroTerrain +
                ", nomTerrain='" + nomTerrain + '\'' +
                ", prixAchat=" + prixAchat +
                '}';
    }
}