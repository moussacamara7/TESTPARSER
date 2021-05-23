package mecanismeJeu;

public class DeplacementString extends Action{
    public DeplacementString(Action suivant) {
        super(suivant);
    }



    public void fait(int ligne) throws Exception{
    }

    public boolean saitFaire(int ligne) {
        return false;
    }
}
