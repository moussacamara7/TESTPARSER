package parser.parserCarteChance;

import carte.Impot;
import parser.Parser;
import plateau.Plateau;

public class ParserChanceImpots extends Parser {

    public ParserChanceImpots(Parser suivant) {
        super(suivant);
    }



    public void parser(String[] values, Plateau plateau) throws Exception{
        Impot c = new Impot(values[1]);
        plateau.ajouterChance(c);
    }

    public boolean saitParser(String[] values) {
        return values[0].matches("IMPOTS");
    }
}
