package parserCarte;

import carte.Impot;
import plateau.Plateau;

public class ParserImpots extends ParserC {

    public ParserImpots(ParserC suivant) {
        super(suivant);
    }



    public void parser(String ligne) throws Exception{
        String[] values = ligne.split(";");
        Impot c = new Impot(values[1]);
        Plateau.ajouterChance(c);
    }

    public boolean saitParser(String ligne) {
        String[] values = ligne.split(";");
        if(values[0].matches("IMPOTS")){
            return true;
        }
        else
            return false;
    }
}
