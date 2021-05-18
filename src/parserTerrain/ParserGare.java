package parserTerrain;


import plateau.Plateau;
import terrain.Gare;

public class ParserGare extends ParserT {

    public ParserGare(ParserT suivant) {
        super(suivant);
    }


    public void parser(String ligne) throws Exception{
        String[] values = ligne.split(";");

        Gare T = new Gare(Integer.parseInt(values[0]),values[2], 400);

        Plateau.ajouterCases(T);
    }

    public boolean saitParser(String ligne) {
        String[] values = ligne.split(";");
        if(values[1].matches("GARE")){
            return true;
        }
        else
            return false;
    }
}
