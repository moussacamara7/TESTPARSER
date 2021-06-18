package terrain;

import joueur.Joueur;

public interface Terrain {
    /**
     * @return vrai si le terrain est achetable
     */
    public abstract boolean estAchetable();

    public abstract void action(Joueur joueur);
}
