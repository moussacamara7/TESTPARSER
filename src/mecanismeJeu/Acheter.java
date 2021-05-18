package mecanismeJeu;

public class Acheter extends Action{
    public Acheter(Action suivant) {
        super(suivant);
    }



    public void fait(String ligne) throws Exception{
    }

    public boolean saitFaire(String ligne) {
        return false;
    }
}
