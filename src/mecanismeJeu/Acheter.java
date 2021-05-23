package mecanismeJeu;

public class Acheter extends Action{
    public Acheter(Action suivant) {
        super(suivant);
    }



    public void fait(int ligne) throws Exception{
    }

    public boolean saitFaire(int ligne) {
        if (ligne == 1)
            return true;
        return false;
    }
}
