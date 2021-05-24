package mecanismeJeu;

public class Hypothequer extends Action{
    public Hypothequer(Action suivant) {
        super(suivant);
    }



    public void fait(int ligne) throws Exception{
    }

    public boolean saitFaire(int ligne) {
        return false;
    }
}
