package parser.parserCarteCommunaute;

import carte.Payer;
import parser.Parser;
import plateau.Plateau;

public class ParserCommunautePayer extends Parser {

    public ParserCommunautePayer(Parser suivant) {
        super(suivant);
    }


    public void parser(String[] values, Plateau plateau) throws Exception {
        Payer c = new Payer(values[1], Integer.parseInt(values[2]));
        plateau.ajouterCommunaute(c);
    }

    public boolean saitParser(String[] values) {
        return values[0].matches("PAYER");
    }
}
