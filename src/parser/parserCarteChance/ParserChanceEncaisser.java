package parser.parserCarteChance;

import application.ui.UIPlateau;
import carte.Encaisser;
import exception.ParserManquantException;
import parser.Parser;

public class ParserChanceEncaisser extends Parser {

    /**
     * Constructeur permettant de parser suivant
     * @param suivant parser suivant
     */
    public ParserChanceEncaisser(Parser suivant) {
        super(suivant);
    }

    /**
     * Methode permettant de traiter la ligne
     * @param values la ligne a traiter
     * @param plateau le plateau
     * @throws ParserManquantException en cas de parser manquant
     */
    public void parser(String[] values, UIPlateau plateau) throws ParserManquantException {
        Encaisser c = new Encaisser(values[1], Integer.parseInt(values[2]));
        plateau.ajouterChance(c);
    }

    /**
     * @param values la ligne a segmenter
     * @return vrai si la ligne peut etre parser
     */
    public boolean saitParser(String[] values) {
        return values[0].matches("ENCAISSER");
    }
}
