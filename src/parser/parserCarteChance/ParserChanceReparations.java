package parser.parserCarteChance;

import carte.Reparation;
import parser.Parser;
import plateau.Plateau;

public class ParserChanceReparations extends Parser {

    public ParserChanceReparations(Parser suivant) {
        super(suivant);
    }


    public void parser(String[] values, Plateau plateau) throws Exception {
        Reparation c = new Reparation(values[1]);
        plateau.ajouterChance(c);
    }

    public boolean saitParser(String[] values) {
        return values[0].matches("REPARATIONS");
    }
}
