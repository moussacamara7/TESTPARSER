package parserCarte;

import carte.Encaisser;
import plateau.Plateau;

public class ParserEncaisser extends ParserC {

    public ParserEncaisser(ParserC suivant) {
        super(suivant);
    }



    public void parser(String ligne) throws Exception{
        String[] values = ligne.split(";");
        Encaisser c = new Encaisser(values[1],Integer.parseInt(values[2]));
        Plateau.ajouterCommunaute(c);
    }

    public boolean saitParser(String ligne) {
        String[] values = ligne.split(";");
        if(values[0].matches("ENCAISSER")){
            return true;
        }
        else
            return false;
    }
}
