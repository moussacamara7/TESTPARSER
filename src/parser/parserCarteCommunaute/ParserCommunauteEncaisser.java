package parser.parserCarteCommunaute;

import carte.Encaisser;
import parser.Parser;
import plateau.Plateau;

public class ParserCommunauteEncaisser extends Parser {

    public ParserCommunauteEncaisser(Parser suivant) {
        super(suivant);
    }



    public void parser(String[] values, Plateau plateau) throws Exception{
        Encaisser c = new Encaisser(values[1],Integer.parseInt(values[2]));
        plateau.ajouterCommunaute(c);
    }

    public boolean saitParser(String[] values) {
        return values[0].matches("ENCAISSER");
    }
}
