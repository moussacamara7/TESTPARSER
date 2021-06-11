package parser.parserTerrain;


import application.ui.UIPlateau;
import parser.Parser;
import terrain.Compagnie;

public class ParserCompagnie extends Parser {

    public ParserCompagnie(Parser suivant) {
        super(suivant);
    }

    @Override
    public void parser(String[] values, UIPlateau plateau) throws Exception {
        Compagnie T = new Compagnie(Integer.parseInt(values[0]), values[2], Integer.parseInt(values[3]));

        plateau.ajouterCasesP(T);
    }

    @Override
    public boolean saitParser(String[] values) {
        return values[1].matches("COMPAGNIE");
    }
}
