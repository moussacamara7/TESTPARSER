package parserCarte;

import carte.Chance;
import plateau.Plateau;

public class ParserChanceC extends ParserC {

    public ParserChanceC(ParserC suivant) {
        super(suivant);
    }


    public void parser(String ligne) throws Exception{
        String[] values = ligne.split(";");
        Chance c = new Chance(values[1],Integer.parseInt(values[2]));
        Plateau.ajouterCommunaute(c);
    }

    public boolean saitParser(String ligne) {
        String[] values = ligne.split(";");
        if(values[0].matches("CHANCE")){
            return true;
        }
        else
            return false;
    }
}
