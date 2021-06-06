package parser.CoordoneesCases.;

import application.ui.UIPlateau;
import plateau.Plateau;

public abstract class ParserC {
    private final ParserC suivant;

    public ParserC(ParserC suivant) {

        this.suivant = suivant;
    }


    public void traiter(String[] values, UIPlateau plateau) throws Exception {
        if (saitParser(values))
            parser(values, plateau);
        else if (aUnSuivant())
            getSuivant().traiter(values, plateau);
        else
            throw new IllegalArgumentException("Parser Manquant");//ParserManquantException();

    }

    private ParserC getSuivant() {
        return suivant;
    }

    private boolean aUnSuivant() {
        return suivant != null;
    }

    public abstract void parser(String[] values, Plateau plateau) throws Exception;

    public abstract boolean saitParser(String[] values);
}
