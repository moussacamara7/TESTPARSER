package parser.parserTerrain;


import application.ui.UIPlateau;
import exception.ParserManquantException;
import parser.Parser;
import terrain.TerrainTaxeDeLuxe;

public class ParserTaxeDeLuxe extends Parser {

    /**
     * Constructeur permettant de parser suivant
     * @param suivant parser suivant
     */
    public ParserTaxeDeLuxe(Parser suivant) {
        super(suivant);
    }

    /**
     * Methode permettant de traiter la ligne
     * @param values la ligne a traiter
     * @param plateau le plateau
     * @throws ParserManquantException en cas de parser manquant
     */
    @Override
    public void parser(String[] values, UIPlateau plateau) throws ParserManquantException {
        TerrainTaxeDeLuxe T = new TerrainTaxeDeLuxe(Integer.parseInt(values[0]), values[1]);

        plateau.ajouterCasesP(T);
    }

    /**
     * @param values la ligne a segmenter
     * @return vrai si la ligne peut etre parser
     */
    @Override
    public boolean saitParser(String[] values) {
        return values[1].matches("TAXE DE LUXE");
    }
}
