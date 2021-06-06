package parser.CoordoneesCases;

import parser.Parser;
import plateau.Plateau;
import terrain.Coordonees;

public class ParserCoordoneesCases extends ParserC {

    public ParserCoordoneesCases(Parser suivant) {
        super(suivant);
    }



    public void parser(String[] values, Plateau plateau) throws Exception{
        Coordonees c = new Coordonees(values[1],Integer.parseInt(values[2]));
        plateau.;
    }

    public boolean saitParser(String[] values) {
        return values[0].matches("ANNIVERSAIRE");
    }
}
