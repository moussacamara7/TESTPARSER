package mecanismeJeu;

public abstract class Action {
    private Action suivant = null;

    public Action(Action suivant) {

        this.suivant = suivant;
    }


    public void traiter(String action) throws Exception {
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

    public abstract void fait(String action) throws Exception;

    public abstract boolean saitFaire(String action);
}
