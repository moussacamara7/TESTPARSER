package parser.parserCarteChance;

import application.ui.UIPlateau;
import carte.Liberation;
import parser.Parser;

public class ParserChanceLiberation extends Parser {

    public ParserChanceLiberation(Parser suivant) {
        super(suivant);
    }


    public void parser(String[] values, UIPlateau plateau) throws Exception {
        Liberation c = new Liberation(values[1]);
        plateau.ajouterChance(c);
    }

    public boolean saitParser(String[] values) {
        return values[0].matches("LIBERATION");
    }
}
