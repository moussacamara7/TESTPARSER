package mecanismeJeu;


public class DeplacementInt extends Action{
    public DeplacementInt(Action suivant) {
        super(suivant);
    }



    public void fait(String ligne) throws Exception{
    }

    public boolean saitFaire(String ligne) {
        return false;
    }
}
