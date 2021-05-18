package mecanismeJeu;

public class DeplacementString extends Action{
    public DeplacementString(Action suivant) {
        super(suivant);
    }



    public void fait(String ligne) throws Exception{
    }

    public boolean saitFaire(String ligne) {
        return false;
    }
}
