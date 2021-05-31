package carte;


import joueur.Joueur;

public interface Cartes {
    default void action(Joueur joueur) throws Exception {

    }
    //public abstract void action(Joueur joueur);
}
