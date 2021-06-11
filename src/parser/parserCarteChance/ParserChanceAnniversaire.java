package parser.parserCarteChance;

import application.ui.UIPlateau;
import carte.Anniversaire;
import parser.Parser;

public class ParserChanceAnniversaire extends Parser {

    public ParserChanceAnniversaire(Parser suivant) {
        super(suivant);
    }


    public void parser(String[] values, UIPlateau plateau) throws Exception {
        Anniversaire c = new Anniversaire(values[1], Integer.parseInt(values[2]));
        plateau.ajouterChance(c);
    }

    public boolean saitParser(String[] values) {
        return values[0].matches("ANNIVERSAIRE");
    }
}
