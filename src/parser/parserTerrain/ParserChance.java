package parser.parserTerrain;


import parser.Parser;
import plateau.Plateau;
import terrain.Piocher;

public class ParserChance extends Parser {

    public ParserChance(Parser suivant) {
        super(suivant);
    }

    @Override
    public void parser(String[] values, Plateau plateau) throws Exception {
        Piocher T = new Piocher(Integer.parseInt(values[0]), values[1]);

        plateau.ajouterCases(T);
    }

    @Override
    public boolean saitParser(String[] values) {
        return values[1].matches("CHANCE");
    }
}
