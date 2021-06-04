package parser.parserCarteCommunaute;

import carte.Deplacement;
import parser.Parser;
import plateau.Plateau;

public class ParserCommunauteDeplacement extends Parser {

    public ParserCommunauteDeplacement(Parser suivant) {
        super(suivant);
    }


    public void parser(String[] values, Plateau plateau) throws Exception {
        Deplacement c = new Deplacement(values[1], values[2]);
        plateau.ajouterCommunaute(c);
    }

    public boolean saitParser(String[] values) {
        return values[0].matches("DEPLACEMENT");
    }
}
