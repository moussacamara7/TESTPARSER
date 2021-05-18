package mecanismeJeu;

public class Hypothequer extends Action{
    public Hypothequer(Action suivant) {
        super(suivant);
    }



    public void fait(String ligne) throws Exception{
    }

    public boolean saitFaire(String ligne) {
        return false;
    }
}
