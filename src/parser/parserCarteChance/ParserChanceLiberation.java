package parser.parserCarteChance;

import carte.Liberation;
import parser.Parser;
import plateau.Plateau;

public class ParserChanceLiberation extends Parser {

    public ParserChanceLiberation(Parser suivant) {
        super(suivant);
    }



    public void parser(String[] values, Plateau plateau) throws Exception{
        Liberation c = new Liberation(values[1], null);
        plateau.ajouterChance(c);
    }

    public boolean saitParser(String[] values) {
        return values[0].matches("LIBERATION");
    }
}
