package parser.parserCarteChance;

import application.ui.UIPlateau;
import carte.Deplacement;
import parser.Parser;

public class ParserChanceDeplacement extends Parser {

    public ParserChanceDeplacement(Parser suivant) {
        super(suivant);
    }


    public void parser(String[] values, UIPlateau plateau) throws Exception {
        Deplacement c = new Deplacement(values[1], values[2]);
        plateau.ajouterChance(c);
    }

    public boolean saitParser(String[] values) {
        return values[0].matches("DEPLACEMENT");
    }
}
