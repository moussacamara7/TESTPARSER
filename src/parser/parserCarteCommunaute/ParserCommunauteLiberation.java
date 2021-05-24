package parser.parserCarteCommunaute;

import carte.Liberation;
import parser.Parser;
import plateau.Plateau;

public class ParserCommunauteLiberation extends Parser {

    public ParserCommunauteLiberation(Parser suivant) {
        super(suivant);
    }



    public void parser(String[] values, Plateau plateau) throws Exception{
        Liberation c = new Liberation(values[1]);
        plateau.ajouterCommunaute(c);
    }

    public boolean saitParser(String[] values) {
        return values[0].matches("LIBERATION");
    }
}
