package parser.parserCarteCommunaute;

import application.ui.UIPlateau;
import carte.Liberation;
import parser.Parser;

public class ParserCommunauteLiberation extends Parser {

    public ParserCommunauteLiberation(Parser suivant) {
        super(suivant);
    }


    public void parser(String[] values, UIPlateau plateau) throws Exception {
        Liberation c = new Liberation(values[1]);
        plateau.ajouterCommunaute(c);
    }

    public boolean saitParser(String[] values) {
        return values[0].matches("LIBERATION");
    }
}
