package parserCarte;


import carte.Deplacement;
import plateau.Plateau;

public class ParserDeplacementC extends ParserC {

    public ParserDeplacementC(ParserC suivant) {
        super(suivant);
    }



    public void parser(String ligne) throws Exception{
        String[] values = ligne.split(";");
        Deplacement c = new Deplacement(values[1],values[2]);
        Plateau.ajouterChance(c);
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