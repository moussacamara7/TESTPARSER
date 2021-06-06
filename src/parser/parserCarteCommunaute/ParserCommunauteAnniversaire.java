package parser.parserCarteCommunaute;

import application.ui.UIPlateau;
import carte.Anniversaire;
import parser.Parser;
import plateau.Plateau;

public class ParserCommunauteAnniversaire extends Parser {

    public ParserCommunauteAnniversaire(Parser suivant) {
        super(suivant);
    }


    public void parser(String[] values, UIPlateau plateau) throws Exception {
        Anniversaire c = new Anniversaire(values[1], Integer.parseInt(values[2]));
        plateau.ajouterCommunaute(c);
    }

    public boolean saitParser(String[] values) {
        return values[0].matches("ANNIVERSAIRE");
    }
}
