package parser.parserTerrain;


import application.ui.UIPlateau;
import parser.Parser;
import plateau.Plateau;
import terrain.Piocher;

public class ParserCaisseCommunaute extends Parser {


    public ParserCaisseCommunaute(Parser suivant) {
        super(suivant);
    }

    @Override
    public void parser(String[] values, UIPlateau plateau) throws Exception {
        Piocher T = new Piocher(Integer.parseInt(values[0]), values[1]);

        plateau.ajouterCasesP(T);
    }

    @Override
    public boolean saitParser(String[] values) {
        return values[1].matches("CAISSE COMMUNAUTE");
    }
}
