package parser.parserCarteCommunaute;

import application.ui.UIPlateau;
import carte.Chance;
import parser.Parser;
import plateau.Plateau;

public class ParserCommunauteChance extends Parser {

    public ParserCommunauteChance(Parser suivant) {
        super(suivant);
    }


    public void parser(String[] values, UIPlateau plateau) throws Exception {
        Chance c = new Chance(values[1], Integer.parseInt(values[2]));
        plateau.ajouterCommunaute(c);
    }

    public boolean saitParser(String[] values) {
        return values[0].matches("CHANCE");
    }
}
