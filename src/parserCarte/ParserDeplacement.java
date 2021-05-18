package parserCarte;

import carte.Deplacement;
import plateau.Plateau;

public class ParserDeplacement extends ParserC {

    public ParserDeplacement(ParserC suivant) {
        super(suivant);
    }



    public void parser(String ligne) throws Exception{
        String[] values = ligne.split(";");
        Deplacement c = new Deplacement(values[1],values[2]);
        Plateau.ajouterCommunaute(c);
    }

    public boolean saitParser(String ligne) {
        String[] values = ligne.split(";");
        if(values[0].matches("DEPLACEMENT")){
            return true;
        }
        else
            return false;
    }
}
