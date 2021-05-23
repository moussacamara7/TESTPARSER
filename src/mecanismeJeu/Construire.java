package mecanismeJeu;

public class Construire extends Action{
    public Construire(Action suivant) {
        super(suivant);
    }



    public void fait(int ligne) throws Exception{

    }

    public boolean saitFaire(int ligne) {
        if(ligne == 2)
            return true;

        return false;
    }
}
