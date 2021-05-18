package parserCarte;

public abstract class ParserC {
    private ParserC suivant = null;

    public ParserC(ParserC suivant) {

        this.suivant = suivant;
    }


    public void traiter(String ligne) throws Exception {
        if (saitParser(ligne))
            parser(ligne);
        else if (aUnSuivant())
            getSuivant().traiter(ligne);
        else
            throw new IllegalArgumentException("Parser Manquant");//ParserManquantException();

    }

    private ParserC getSuivant() {
        return suivant;
    }

    private boolean aUnSuivant() {
        return suivant != null;
    }

    public abstract void parser(String ligne) throws Exception;

    public abstract boolean saitParser(String ligne);
}
