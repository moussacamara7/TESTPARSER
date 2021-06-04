package parser.parserCarteCommunaute;

import carte.Impot;
import parser.Parser;
import plateau.Plateau;

public class ParserCommunauteImpots extends Parser {

    public ParserCommunauteImpots(Parser suivant) {
        super(suivant);
    }


    public void parser(String[] values, Plateau plateau) throws Exception {
        Impot c = new Impot(values[1]);
        plateau.ajouterCommunaute(c);
    }

    public boolean saitParser(String[] values) {
        return values[0].matches("IMPOTS");
    }
}
