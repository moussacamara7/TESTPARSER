package parser.CoordoneesCases;

import application.ui.UICase;
import application.ui.UIPlateau;
import carte.Anniversaire;
import parser.Parser;
import plateau.Plateau;
import terrain.Coordonees;

public class ParserCoordoneesCases extends Parser {

    public ParserCoordoneesCases(Parser suivant) {
        super(suivant);
    }



    public void parser(String[] values, UIPlateau plateau) throws Exception{

        UICase c = plateau.getCase(Integer.parseInt(values[0]));
        c.setCoordonnees(Integer.parseInt(values[1]), Integer.parseInt(values[2]), Integer.parseInt(values[3]), Integer.parseInt(values[4]));

    }

    public boolean saitParser(String[] values) {
            return true;//values[0].matches("ANNIVERSAIRE");
    }
}
