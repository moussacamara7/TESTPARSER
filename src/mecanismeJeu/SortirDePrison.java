package mecanismeJeu;

public class SortirDePrison extends Action{
    public SortirDePrison(Action suivant) {
        super(suivant);
    }



    public void fait(String ligne) throws Exception{
    }

    public boolean saitFaire(String ligne) {
        return false;
    }
}
