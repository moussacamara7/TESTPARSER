package parserTerrain;



public abstract class ParserT {

    private ParserT suivant = null;

    public ParserT(ParserT suivant) {
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

    private ParserT getSuivant() {
        return suivant;
    }

    private boolean aUnSuivant() {
        return suivant != null;
    }

    public abstract void parser(String ligne) throws Exception;

    public abstract boolean saitParser(String ligne);
}
