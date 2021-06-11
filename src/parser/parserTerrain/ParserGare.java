package parser.parserTerrain;


import application.ui.UIPlateau;
import parser.Parser;
import terrain.Gare;

public class ParserGare extends Parser {

    public ParserGare(Parser suivant) {
        super(suivant);
    }

    @Override
    public void parser(String[] values, UIPlateau plateau) throws Exception {
        Gare T = new Gare(Integer.parseInt(values[0]), values[2], 400);

        plateau.ajouterCasesP(T);
    }

    @Override
    public boolean saitParser(String[] values) {
        return values[1].matches("GARE");
    }
}
