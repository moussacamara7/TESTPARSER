package parser;

import plateau.Plateau;

public abstract class Parser {
    private final Parser suivant;

    public Parser(Parser suivant) {

        this.suivant = suivant;
    }


    public void traiter(String[] values, Plateau plateau) throws Exception {
        if (saitParser(values))
            parser(values, plateau);
        else if (aUnSuivant())
            getSuivant().traiter(values, plateau);
        else
            throw new IllegalArgumentException("Parser Manquant");//ParserManquantException();

    }

    private Parser getSuivant() {
        return suivant;
    }

    private boolean aUnSuivant() {
        return suivant != null;
    }

    public abstract void parser(String[] values, Plateau plateau) throws Exception;

    public abstract boolean saitParser(String[] values);
}
