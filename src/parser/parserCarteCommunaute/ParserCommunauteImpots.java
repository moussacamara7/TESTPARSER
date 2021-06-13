package parser.parserCarteCommunaute;

import application.ui.UIPlateau;
import carte.Impot;
import exception.ParserManquantException;
import parser.Parser;

public class ParserCommunauteImpots extends Parser {

    /**
     * Constructeur permettant de parser suivant
     * @param suivant parser suivant
     */
    public ParserCommunauteImpots(Parser suivant) {
        super(suivant);
    }

    /**
     * Methode permettant de traiter la ligne
     * @param values la ligne a traiter
     * @param plateau le plateau
     * @throws ParserManquantException en cas de parser manquant
     */
    public void parser(String[] values, UIPlateau plateau) throws ParserManquantException {
        Impot c = new Impot(values[1]);
        plateau.ajouterCommunaute(c);
    }

    /**
     * @param values la ligne a segmenter
     * @return vrai si la ligne peut etre parser
     */
    public boolean saitParser(String[] values) {
        return values[0].matches("IMPOTS");
    }
}
