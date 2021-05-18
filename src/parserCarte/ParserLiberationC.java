package parserCarte;

import carte.Liberation;
import plateau.Plateau;

public class ParserLiberationC extends ParserC {

    public ParserLiberationC(ParserC suivant) {
        super(suivant);
    }



    public void parser(String ligne) throws Exception{
        String[] values = ligne.split(";");
        Liberation c = new Liberation(values[1]);
        Plateau.ajouterChance(c);
    }

    public boolean saitParser(String ligne) {
        String[] values = ligne.split(";");
        if(values[0].matches("LIBERATION")){
            return true;
        }
        else
            return false;
    }
}
