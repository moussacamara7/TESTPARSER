package main;

import plateau.Plateau;

import java.io.IOException;

public class Main {

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

    }
}
