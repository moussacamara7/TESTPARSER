package parserTerrain;


import plateau.Plateau;
import terrain.Loyer;
import terrain.TerrainConstructible;

public class ParserTerrainConstructible extends ParserT {


    public ParserTerrainConstructible(ParserT suivant) {
        super(suivant);
    }



    public void parser(String ligne) throws Exception{
        String[] values = ligne.split(";");

        Loyer l = new Loyer(Integer.parseInt(values[7]),Integer.parseInt(values[8]),Integer.parseInt(values[9]),Integer.parseInt(values[10]),Integer.parseInt(values[11]),Integer.parseInt(values[6]));

        TerrainConstructible T = new TerrainConstructible(Integer.parseInt(values[0]),values[3],Integer.parseInt(values[4]),values[2],0,Integer.parseInt(values[5]),l);

        Plateau.ajouterCases(T);
    }

    public boolean saitParser(String ligne) {
        String[] values = ligne.split(";");
        if(values[1].matches("TERRAIN CONSTRUCTIBLE")){
            return true;
        }
        else
            return false;
    }
}
