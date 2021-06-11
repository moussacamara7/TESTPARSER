package parser.parserCarteChance;

import application.ui.UIPlateau;
import carte.Encaisser;
import parser.Parser;

public class ParserChanceEncaisser extends Parser {

    public ParserChanceEncaisser(Parser suivant) {
        super(suivant);
    }


    public void parser(String[] values, UIPlateau plateau) throws Exception {
        Encaisser c = new Encaisser(values[1], Integer.parseInt(values[2]));
        plateau.ajouterChance(c);
    }

    public boolean saitParser(String[] values) {
        return values[0].matches("ENCAISSER");
    }
}
