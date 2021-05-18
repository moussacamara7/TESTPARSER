package parserTerrain;


import plateau.Plateau;
import terrain.TerrainNeutre;

public class ParserParkingGratuit extends ParserT {


    public ParserParkingGratuit(ParserT suivant) {
        super(suivant);
    }


    public void parser(String ligne) throws Exception{
        String[] values = ligne.split(";");

        TerrainNeutre T = new TerrainNeutre(Integer.parseInt(values[0]), values[1]);

        Plateau.ajouterCases(T);
    }

    public boolean saitParser(String ligne) {
        String[] values = ligne.split(";");
        if(values[1].matches("PARKING GRATUIT")){
            return true;
        }
        else
            return false;
    }
}
