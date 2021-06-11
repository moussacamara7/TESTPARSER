package parser.parserCarteChance;

import application.ui.UIPlateau;
import carte.Impot;
import parser.Parser;

public class ParserChanceImpots extends Parser {

    public ParserChanceImpots(Parser suivant) {
        super(suivant);
    }


    public void parser(String[] values, UIPlateau plateau) throws Exception {
        Impot c = new Impot(values[1]);
        plateau.ajouterChance(c);
    }

    public boolean saitParser(String[] values) {
        return values[0].matches("IMPOTS");
    }
}
