package parser.parserTerrain;


import application.ui.UIPlateau;
import parser.Parser;
import terrain.Loyer;
import terrain.TerrainConstructible;

public class ParserTerrainConstructible extends Parser {


    public ParserTerrainConstructible(Parser suivant) {
        super(suivant);
    }

    @Override
    public void parser(String[] values, UIPlateau plateau) throws Exception {
        Loyer loyer = new Loyer(
                Integer.parseInt(values[7]),
                Integer.parseInt(values[8]),
                Integer.parseInt(values[9]),
                Integer.parseInt(values[10]),
                Integer.parseInt(values[11]),
                Integer.parseInt(values[6])
        );

        TerrainConstructible T = new TerrainConstructible(
                Integer.parseInt(values[0]),
                values[3], Integer.parseInt(values[4]),
                values[2], 0,
                Integer.parseInt(values[5]),
                loyer
        );

        plateau.ajouterCasesP(T);
    }

    @Override
    public boolean saitParser(String[] values) {
        return values[1].matches("TERRAIN CONSTRUCTIBLE");
    }
}