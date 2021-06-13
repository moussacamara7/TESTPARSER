package parser;

import application.ui.UIPlateau;
import exception.ParserManquantException;

public abstract class Parser {
    private final Parser suivant;

    /**
     * Constructeur permettant de parser suivant
     * @param suivant parser suivant
     */
    public Parser(Parser suivant) {

        this.suivant = suivant;
    }

    /**
     * Methode permettant de rechercher le bon parser
     * @param values la ligne a segmenter
     * @param plateau le plateau
     * @throws ParserManquantException en cas de parser manquant
     */
    public void traiter(String[] values, UIPlateau plateau) throws ParserManquantException {
        if (saitParser(values))
            parser(values, plateau);
        else if (aUnSuivant())
            getSuivant().traiter(values, plateau);
        else
            throw new ParserManquantException("Parser Manquant");

    }

    /**
     * @return le suivant
     */
    private Parser getSuivant() {
        return suivant;
    }

    /**
     * @return vrai si il y a un suivant
     */
    private boolean aUnSuivant() {
        return suivant != null;
    }

    /**
     * Methode permettant de traiter la ligne
     * @param values la ligne a traiter
     * @param plateau le plateau
     * @throws ParserManquantException en cas de parser manquant
     */
    public abstract void parser(String[] values, UIPlateau plateau) throws ParserManquantException;

    /**
     * @param values la ligne a segmenter
     * @return vrai si la ligne peut etre parser
     */
    public abstract boolean saitParser(String[] values);
}
