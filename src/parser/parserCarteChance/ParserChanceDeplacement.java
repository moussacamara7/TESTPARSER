package parser.parserCarteChance;

import carte.Deplacement;
import parser.Parser;
import plateau.Plateau;

public class ParserChanceDeplacement extends Parser {

    public ParserChanceDeplacement(Parser suivant) {
        super(suivant);
    }


    public void parser(String[] values, Plateau plateau) throws Exception {
        Deplacement c = new Deplacement(values[1], values[2]);
        plateau.ajouterChance(c);
    }

    public boolean saitParser(String[] values) {
        return values[0].matches("DEPLACEMENT");
    }
}
