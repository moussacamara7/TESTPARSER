package mecanismeJeu;
import joueur.Joueur;

/****************************************
 * Actions :
 * 1 - Acheter
 * 2 - Construire
 * 3 - Payer
****************************************/

public abstract class Action {
    private Action suivant = null;

    public Action(Action suivant) {

        this.suivant = suivant;
    }


    public void traiter(int action) throws Exception {
        if (saitFaire(action))
            fait(action);
        else if (aUnSuivant())
            getSuivant().traiter(action);
        else
            throw new IllegalArgumentException("Action Manquante");

    }

    private Action getSuivant() {
        return suivant;
    }

    private boolean aUnSuivant() {
        return suivant != null;
    }

    public abstract void fait(int action) throws Exception;

    public abstract boolean saitFaire(int action);
}
