package mecanismeJeu;

public class AllezEnPrison extends Action{
    public AllezEnPrison(Action suivant) {
        super(suivant);
    }



    public void fait(String ligne) throws Exception{
    }

    public boolean saitFaire(String ligne) {
        return false;
    }
}
