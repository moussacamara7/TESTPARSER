package parserTerrain;


import plateau.Plateau;
import terrain.TerrainAction;

public class ParserTaxeDeLuxe extends ParserT {


    public ParserTaxeDeLuxe(ParserT suivant) {
        super(suivant);
    }


    public void parser(String ligne) throws Exception{
        String[] values = ligne.split(";");

        TerrainAction T = new TerrainAction(Integer.parseInt(values[0]), values[1]);

        Plateau.ajouterCases(T);
    }

    public boolean saitParser(String ligne) {
        String[] values = ligne.split(";");
        if(values[1].matches("TAXE DE LUXE")){
            return true;
        }
        else
            return false;
    }
}
