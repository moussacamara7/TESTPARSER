package main;

import joueur.Joueur;
import plateau.Plateau;
import mecanismeJeu.*;

import java.io.IOException;

public class Main {

    //numero du joueur dont c'est le tour dans listJoueur
    private static int numeroJoueurActuel;

    public static int getNumeroJoueurActuel(){
        return numeroJoueurActuel;
    }

    public static void setNumeroJoueurActuel(int numeroJoueurActuel) {
        Main.numeroJoueurActuel = numeroJoueurActuel;
    }

    public static void main(String[] args) throws IOException {


        Plateau plt = new Plateau();

        System.out.println("Les terrains Du jeu MONOPOLY:");
        System.out.println("Le nombre de Terrain : " + plt.getNombreCase());

        for(int i=0; i<plt.getNombreCase(); i++)
            System.out.println(plt.getCase(i));

        System.out.println("Les cartes Chance:");
        System.out.println("Le nombre de cartes : " + plt.getNombreCarteChance());

        for(int i=0; i<plt.getNombreCarteChance(); i++)
            System.out.println(plt.getChance(i));

        System.out.println("Les cartes Caisse de communaute:");
        System.out.println("Le nombre de Cartes : " + plt.getNombreCarteCommunaute());

        for(int i=0; i<plt.getNombreCarteCommunaute(); i++)
            System.out.println(plt.getCommunaute(i));

        joueur.Joueur A = new Joueur("A",200,0,false);
        joueur.Joueur B = new Joueur("B",200,0,false);




    }
}
