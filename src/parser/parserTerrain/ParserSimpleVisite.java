package parser.parserTerrain;


import application.ui.UIPlateau;
import parser.Parser;
import plateau.Plateau;
import terrain.TerrainNeutre;

public class ParserSimpleVisite extends Parser {


    public ParserSimpleVisite(Parser suivant) {
        super(suivant);
    }

    @Override
    public void parser(String[] values, UIPlateau plateau) throws Exception {
        TerrainNeutre T = new TerrainNeutre(Integer.parseInt(values[0]), values[1]);

        plateau.ajouterCasesP(T);
    }

    @Override
    public boolean saitParser(String[] values) {
        return values[1].matches("SIMPLE VISITE");
    }
}
