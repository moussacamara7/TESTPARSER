package parser.parserTerrain;


import parser.Parser;
import plateau.Plateau;
import terrain.TerrainAction;

public class ParserPrison extends Parser {


    public ParserPrison(Parser suivant) {
        super(suivant);
    }

    @Override
    public void parser(String[] values, Plateau plateau) throws Exception {
        TerrainAction T = new TerrainAction(Integer.parseInt(values[0]), values[1]);

        plateau.ajouterCases(T);
    }

    @Override
    public boolean saitParser(String[] values) {
        return values[1].matches("PRISON");
    }
}
