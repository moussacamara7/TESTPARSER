package parser.parserTerrain;


import parser.Parser;
import plateau.Plateau;
import terrain.Gare;

public class ParserGare extends Parser {

    public ParserGare(Parser suivant) {
        super(suivant);
    }

    @Override
    public void parser(String[] values, Plateau plateau) throws Exception {
        Gare T = new Gare(Integer.parseInt(values[0]), values[2], 400);

        plateau.ajouterCases(T);
    }

    @Override
    public boolean saitParser(String[] values) {
        return values[1].matches("GARE");
    }
}
