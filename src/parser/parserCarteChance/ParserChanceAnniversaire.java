package parser.parserCarteChance;

import application.ui.UIPlateau;
import carte.Anniversaire;
import exception.MonopolyException;
import exception.ParserManquantException;
import parser.Parser;

public class ParserChanceAnniversaire extends Parser {

    /**
     * Constructeur permettant de parser suivant
     * @param suivant parser suivant
     */
    public ParserChanceAnniversaire(Parser suivant) {
        super(suivant);
    }

    /**
     * Methode permettant de traiter la ligne
     * @param values la ligne a traiter
     * @param plateau le plateau
     * @throws ParserManquantException en cas de parser manquant
     */
    public void parser(String[] values, UIPlateau plateau) throws ParserManquantException {
        Anniversaire c = new Anniversaire(values[1], Integer.parseInt(values[2]));
        plateau.ajouterChance(c);
    }

    /**
     * @param values la ligne a segmenter
     * @return vrai si la ligne peut etre parser
     */
    public boolean saitParser(String[] values) {
        return values[0].matches("ANNIVERSAIRE");
    }
}
