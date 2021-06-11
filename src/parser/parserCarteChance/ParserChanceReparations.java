package parser.parserCarteChance;

import application.ui.UIPlateau;
import carte.Reparation;
import parser.Parser;

public class ParserChanceReparations extends Parser {

    public ParserChanceReparations(Parser suivant) {
        super(suivant);
    }


    public void parser(String[] values, UIPlateau plateau) throws Exception {
        Reparation c = new Reparation(values[1]);
        plateau.ajouterChance(c);
    }

    public boolean saitParser(String[] values) {
        return values[0].matches("REPARATIONS");
    }
}
