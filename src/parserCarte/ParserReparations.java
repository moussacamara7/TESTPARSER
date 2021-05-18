package parserCarte;

import carte.Impot;
import carte.Reparation;
import plateau.Plateau;

public class ParserReparations extends ParserC {

    public ParserReparations(ParserC suivant) {
        super(suivant);
    }



    public void parser(String ligne) throws Exception{
        String[] values = ligne.split(";");
        Reparation c = new Reparation(values[1]);
        Plateau.ajouterChance(c);
    }

    public boolean saitParser(String ligne) {
        String[] values = ligne.split(";");
        if(values[0].matches("REPARATIONS")){
            return true;
        }
        else
            return false;
    }
}
