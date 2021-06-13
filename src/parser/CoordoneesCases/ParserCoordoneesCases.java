package parser.CoordoneesCases;

import application.ui.UICase;
import application.ui.UIPlateau;
import exception.ParserManquantException;
import parser.Parser;

public class ParserCoordoneesCases extends Parser {

    /**
     * Constructeur permettant de parser suivant
     * @param suivant parser suivant
     */
    public ParserCoordoneesCases(Parser suivant) {
        super(suivant);
    }

    /**
     * Methode permettant de segmenter la ligne pour definir les coordonees d'une case
     * @param values la ligne a segmenter
     * @param plateau le plateau
     * @throws ParserManquantException en cas de parser manquant
     */
    public void parser(String[] values, UIPlateau plateau) throws ParserManquantException {

        UICase c = plateau.getCase(Integer.parseInt(values[0]));
        c.setCoordonnees(Integer.parseInt(values[1]), Integer.parseInt(values[2]), Integer.parseInt(values[3]), Integer.parseInt(values[4]));

    }

    /**
     * @param values la ligne a segmenter
     * @return vrai si la ligne peut etre parser
     */
    public boolean saitParser(String[] values) {
        return true;
    }
}
