package mecanismeJeu;


public class DeplacementInt extends Action{
    public DeplacementInt(Action suivant) {
        super(suivant);
    }



    public void fait(int ligne) throws Exception{
    }

    public boolean saitFaire(int ligne) {
        return false;
    }
}
