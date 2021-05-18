package parserCarte;

import carte.Anniversaire;
import plateau.Plateau;

public class ParserAnniversaire extends ParserC{

    public ParserAnniversaire(ParserC suivant) {
        super(suivant);
    }



    public void parser(String ligne) throws Exception{
        String[] values = ligne.split(";");
        Anniversaire c = new Anniversaire(values[1],Integer.parseInt(values[2]));
        Plateau.ajouterCommunaute(c);
    }

    public boolean saitParser(String ligne) {
        String[] values = ligne.split(";");
        if(values[0].matches("ANNIVERSAIRE")){
            return true;
        }
        else
            return false;
    }
}
