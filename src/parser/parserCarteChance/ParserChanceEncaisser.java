package parser.parserCarteChance;

import carte.Encaisser;
import parser.Parser;
import plateau.Plateau;

public class ParserChanceEncaisser extends Parser {

    public ParserChanceEncaisser(Parser suivant) {
        super(suivant);
    }


    public void parser(String[] values, Plateau plateau) throws Exception {
        Encaisser c = new Encaisser(values[1], Integer.parseInt(values[2]));
        plateau.ajouterChance(c);
    }

    public boolean saitParser(String[] values) {
        return values[0].matches("ENCAISSER");
    }
}
