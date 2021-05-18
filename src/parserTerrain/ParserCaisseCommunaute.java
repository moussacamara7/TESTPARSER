package parserTerrain;


import plateau.Plateau;
import terrain.Piocher;

public class ParserCaisseCommunaute extends ParserT {


    public ParserCaisseCommunaute(ParserT suivant) {
        super(suivant);
    }


    public void parser(String ligne) throws Exception{
        String[] values = ligne.split(";");

        Piocher T = new Piocher(Integer.parseInt(values[0]), values[1]);

        Plateau.ajouterCases(T);
    }

    public boolean saitParser(String ligne) {
        String[] values = ligne.split(";");
        if(values[1].matches("CAISSE COMMUNAUTE")){
            return true;
        }
        else
            return false;
    }
}
