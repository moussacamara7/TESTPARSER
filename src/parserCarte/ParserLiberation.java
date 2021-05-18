package parserCarte;

import carte.Liberation;
import plateau.Plateau;

public class ParserLiberation extends ParserC {

    public ParserLiberation(ParserC suivant) {
        super(suivant);
    }



    public void parser(String ligne) throws Exception{
        String[] values = ligne.split(";");
        Liberation c = new Liberation(values[1]);
        Plateau.ajouterCommunaute(c);
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
