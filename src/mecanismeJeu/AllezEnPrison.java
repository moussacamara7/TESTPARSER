package mecanismeJeu;

public class AllezEnPrison extends Action{
    public AllezEnPrison(Action suivant) {
        super(suivant);
    }



    public void fait(int ligne) throws Exception{
    }

    public boolean saitFaire(int ligne) {
        return false;
    }
}
