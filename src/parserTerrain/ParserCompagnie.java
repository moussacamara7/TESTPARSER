package parserTerrain;


import plateau.Plateau;
import terrain.Compagnie;

public class ParserCompagnie extends ParserT {

    public ParserCompagnie(ParserT suivant) {
        super(suivant);
    }


    public void parser(String ligne) throws Exception{
        String[] values = ligne.split(";");

        Compagnie T = new Compagnie(Integer.parseInt(values[0]),values[2], Integer.parseInt(values[3]));

        Plateau.ajouterCases(T);
    }

    public boolean saitParser(String ligne) {
        String[] values = ligne.split(";");
        if(values[1].matches("COMPAGNIE")){
            return true;
        }
        else
            return false;
    }
}
