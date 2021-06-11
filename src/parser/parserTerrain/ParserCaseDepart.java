package parser.parserTerrain;


import application.ui.UIPlateau;
import parser.Parser;
import terrain.TerrainAction;

public class ParserCaseDepart extends Parser {

    public ParserCaseDepart(Parser suivant) {
        super(suivant);
    }

    @Override
    public void parser(String[] values, UIPlateau plateau) throws Exception {
        TerrainAction T = new TerrainAction(Integer.parseInt(values[0]), values[1]);

        plateau.ajouterCasesP(T);
    }

    @Override
    public boolean saitParser(String[] values) {
        return values[1].matches("CASE DEPART");
    }
}
