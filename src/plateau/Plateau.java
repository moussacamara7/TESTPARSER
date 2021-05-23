package plateau;

import carte.Cartes;
import fichier.*;
import joueur.Joueur;
import parserCarte.*;
import parserTerrain.*;
import terrain.Terrain;

import java.io.IOException;
import java.util.ArrayList;

public class Plateau {
    private static final int NB_JOUEUR_MAX = 10;
    private static ArrayList<Joueur> listeJoueurs = new ArrayList<Joueur>();
    private static ArrayList<Terrain> cases = new ArrayList<Terrain>();
    private static ArrayList<Cartes> chance = new ArrayList<Cartes>();
    private static ArrayList<Cartes> caisseCommunaute = new ArrayList<Cartes>();


    public Plateau() throws IOException {
        initialisationTerrain();
        initialisationCarteCommunaute();
        initialisationCarteChance();
    }

    public void initialisationTerrain(){

        try {
            String fichierTerrain = "data/Terrains.csv";

            ParserT premierParser = null;
            premierParser = new ParserAllezEnPrison(premierParser);
            premierParser = new ParserCaisseCommunaute(premierParser);
            premierParser = new ParserCaseDepart(premierParser);
            premierParser = new ParserChance(premierParser);
            premierParser = new ParserCompagnie(premierParser);
            premierParser = new ParserGare(premierParser);
            premierParser = new ParserImpotSurLeRevenu(premierParser);
            premierParser = new ParserParkingGratuit(premierParser);
            premierParser = new ParserPrison(premierParser);
            premierParser = new ParserSimpleVisite(premierParser);
            premierParser = new ParserTaxeDeLuxe(premierParser);
            premierParser = new ParserTerrainConstructible(premierParser);


            FichierTerrain.lire(fichierTerrain, premierParser);

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    public void initialisationCarteCommunaute(){

        try {
            String fichierCommunaute = "data/CartesCommunaute.csv";



            ParserC premierParserC = null;
            premierParserC = new ParserAnniversaire(premierParserC);
            premierParserC = new ParserChanceC(premierParserC);
            premierParserC = new ParserDeplacement(premierParserC);
            premierParserC = new ParserEncaisser(premierParserC);
            premierParserC = new ParserLiberation(premierParserC);
            premierParserC = new ParserPayer(premierParserC);


            FichierCarte.lire(fichierCommunaute, premierParserC);

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    public void initialisationCarteChance(){

        try {
            String fichierChance = "data/CartesChance.csv";



            ParserC premierParserC = null;
            premierParserC = new ParserDeplacementC(premierParserC);
            premierParserC = new ParserEncaisserC(premierParserC);
            premierParserC = new ParserImpots(premierParserC);
            premierParserC = new ParserLiberationC(premierParserC);
            premierParserC = new ParserPayerC(premierParserC);
            premierParserC = new ParserReparations(premierParserC);


            FichierCarte.lire(fichierChance, premierParserC);

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }





    ////////////////////////////////////////////
    //
    //      Methodes relatifs aux Cases
    //
    ////////////////////////////////////////////
    public static void ajouterCases(Terrain cas) {
        if (cas == null)
            throw new IllegalArgumentException("Case erronee");

        cases.add(cas);

    }


    public int getNombreCase(){return cases.size();}

    public Terrain getCase(int i) {
        if (i<0 || i>40)
            throw new IllegalArgumentException("L'indice de la case est incorrect");

        return cases.get(i);
    }

    ////////////////////////////////////////////
    //
    //      Methodes relatifs aux Cartes Chance
    //
    ////////////////////////////////////////////
    public static void ajouterChance(Cartes cChance) {
        if (cChance == null)
            throw new IllegalArgumentException("Carte chance erronee");

        chance.add(cChance);

    }
    public int getNombreCarteChance(){return chance.size();}

    public Cartes getChance(int i) {
        if (i<0 || i>15)
            throw new IllegalArgumentException("L'indice de la carte est incorrect");

        return chance.get(i);
    }

    ////////////////////////////////////////////
    //
    //      Methodes relatifs aux Cartes Caisse de communauté
    //
    ////////////////////////////////////////////
    public static void ajouterCommunaute(Cartes cCommunaute) {
        if (cCommunaute == null)
            throw new IllegalArgumentException("Carte communaute erronee");

        caisseCommunaute.add(cCommunaute);

    }

    public int getNombreCarteCommunaute(){return caisseCommunaute.size();}

    public Cartes getCommunaute(int i) {
        if (i<0 || i>15)
            throw new IllegalArgumentException("L'indice de la carte est incorrect");

        return caisseCommunaute.get(i);
    }


    ////////////////////////////////////////////
    //
    //      Methodes relatifs aux Joueurs
    //
    ////////////////////////////////////////////
    public static void ajouterJoueur(Joueur joueur) {
        if (joueur == null)
            throw new IllegalArgumentException("joueur erroné");

        listeJoueurs.add(joueur);

    }

    public int getNombreJoueurs(){return listeJoueurs.size();}

    public static Joueur getJoueur(int i) {
        if (i<0 || i>NB_JOUEUR_MAX)
            throw new IllegalArgumentException("L'indice du joueur est incorrect");

        return listeJoueurs.get(i);
    }

}
