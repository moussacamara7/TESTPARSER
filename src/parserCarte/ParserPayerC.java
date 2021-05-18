package parserCarte;

import carte.Payer;
import plateau.Plateau;

public class ParserPayerC extends ParserC {

    public ParserPayerC(ParserC suivant) {
        super(suivant);
    }



    public void parser(String ligne) throws Exception{
        String[] values = ligne.split(";");
        Payer c = new Payer(values[1],Integer.parseInt(values[2]));
        Plateau.ajouterChance(c);
    }

    public boolean saitParser(String ligne) {
        String[] values = ligne.split(";");
        if(values[0].matches("PAYER")){
            return true;
        }
        else
            return false;
    }
}
