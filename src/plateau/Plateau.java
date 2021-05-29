package plateau;

import carte.Cartes;
import fichier.LectureFichier;
import joueur.Joueur;
import parser.Parser;
import parser.parserCarteChance.*;
import parser.parserCarteCommunaute.*;
import parser.parserTerrain.*;
import terrain.Terrain;

import java.io.IOException;
import java.util.ArrayList;

public class Plateau {
    private static final int NB_JOUEUR_MAX = 10;
    private final ArrayList<Joueur> listeJoueurs = new ArrayList<>();
    private final ArrayList<Terrain> cases = new ArrayList<>();
    private final ArrayList<Cartes> chance = new ArrayList<>();
    private final ArrayList<Cartes> caisseCommunaute = new ArrayList<>();
//////////////////////////////////////////////////////////////////////////////
//      On mettra la plupart des fonctions de cette classe en "static"      //
//      On va analyser cela ensemble                                        //
//////////////////////////////////////////////////////////////////////////////
    public Plateau() throws IOException {
        initialisationTerrain();
        initialisationCarteCommunaute();
        initialisationCarteChance();
    }

    public void initialisationTerrain() {

        try {
            String fichierTerrain = "data/Terrains.csv";

            Parser premierParser = new ParserAllezEnPrison(null);
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


            LectureFichier.lire(fichierTerrain, premierParser, this);

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    public void initialisationCarteCommunaute() {

        try {
            String fichierCommunaute = "data/CartesCommunaute.csv";


            Parser premierParser = new ParserCommunauteAnniversaire(null);
            premierParser = new ParserCommunauteImpots(premierParser);
            premierParser = new ParserCommunauteReparations(premierParser);
            premierParser = new ParserCommunauteDeplacement(premierParser);
            premierParser = new ParserCommunauteEncaisser(premierParser);
            premierParser = new ParserCommunauteLiberation(premierParser);
            premierParser = new ParserCommunautePayer(premierParser);
            premierParser = new ParserCommunauteChance(premierParser);


            LectureFichier.lire(fichierCommunaute, premierParser, this);

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    public void initialisationCarteChance() {

        try {
            String fichierChance = "data/CartesChance.csv";


            Parser premierParser = new ParserChanceAnniversaire(null);
            premierParser = new ParserChanceImpots(premierParser);
            premierParser = new ParserChanceReparations(premierParser);
            premierParser = new ParserChanceDeplacement(premierParser);
            premierParser = new ParserChanceEncaisser(premierParser);
            premierParser = new ParserChanceLiberation(premierParser);
            premierParser = new ParserChancePayer(premierParser);


            LectureFichier.lire(fichierChance, premierParser, this);

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }


    ////////////////////////////////////////////
    //
    //      Methodes relatifs aux Cases
    //
    ////////////////////////////////////////////
    public void ajouterCases(Terrain cas) {
        if (cas == null)
            throw new IllegalArgumentException("Case erronee");

        cases.add(cas);

    }


    public int getNombreCase() {
        return cases.size();
    }

    public Terrain getCase(int i) {
        if (i < 0 || i > 40)
            throw new IllegalArgumentException("L'indice de la case est incorrect");

        return cases.get(i);
    }

    public ArrayList<Terrain> getListeCases(){
        return this.cases;
    }


    ////////////////////////////////////////////
    //
    //      Methodes relatifs aux Cartes Chance
    //
    ////////////////////////////////////////////
    public void ajouterChance(Cartes cChance) {
        if (cChance == null)
            throw new IllegalArgumentException("Carte chance erronee");

        chance.add(cChance);

    }

    public int getNombreCarteChance() {
        return chance.size();
    }

    public Cartes getChance(int i) {
        if (i < 0 || i > 15)
            throw new IllegalArgumentException("L'indice de la carte est incorrect");

        return chance.get(i);
    }

    ////////////////////////////////////////////
    //
    //      Methodes relatifs aux Cartes Caisse de communauté
    //
    ////////////////////////////////////////////
    public void ajouterCommunaute(Cartes cCommunaute) {
        if (cCommunaute == null)
            throw new IllegalArgumentException("Carte communaute erronee");

        caisseCommunaute.add(cCommunaute);

    }

    public int getNombreCarteCommunaute() {
        return caisseCommunaute.size();
    }

    public Cartes getCommunaute(int i) {
        if (i < 0 || i > 15)
            throw new IllegalArgumentException("L'indice de la carte est incorrect");

        return caisseCommunaute.get(i);
    }


    ////////////////////////////////////////////
    //
    //      Methodes relatifs aux Joueurs
    //
    ////////////////////////////////////////////
    public void ajouterJoueur(Joueur joueur) {
        if (joueur == null)
            throw new IllegalArgumentException("joueur erroné");

        listeJoueurs.add(joueur);

    }

    public int getNombreJoueurs() {
        return listeJoueurs.size();
    }

    public Joueur getJoueur(int i) {
        if (i < 0 || i > NB_JOUEUR_MAX)
            throw new IllegalArgumentException("L'indice du joueur est incorrect");

        return listeJoueurs.get(i);
    }
    ////////////////////////////////////////////////////////////////////////
    //                                                                    //
    //              Jouer pourrait être directement dans le main          //
    //                                                                    //
    ////////////////////////////////////////////////////////////////////////
    public void jouer(){
        //jeu

    }

}
