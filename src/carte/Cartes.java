package carte;


import joueur.Joueur;

/**
 * Interface régissant les cartes du jeu
 */
public interface Cartes {
    default void action(Joueur joueur) throws Exception {
    }
}
