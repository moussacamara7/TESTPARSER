package application.ui;

import application.Monopoly;
import carte.Cartes;
import fichier.LectureFichier;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import joueur.Joueur;
import parser.CoordoneesCases.ParserCoordoneesCases;
import parser.Parser;
import parser.parserCarteChance.*;
import parser.parserCarteCommunaute.*;
import parser.parserTerrain.*;
import terrain.Terrain;

import java.util.ArrayList;
import java.util.HashMap;

public class UIPlateau {

    final static String PLATEAU = "file:image/Plateau2.jpg";
    final static String COORDONNEES = "data/CoordonneesCases.csv";
    final static String TERRAIN = "data/Terrains.csv";
    final static String CARTE_CHANCE = "data/CartesChance.csv";
    final static String CARTE_CAISSECOMMUNAUTE = "data/CartesCommunaute.csv";
    private static final int NOMBRE_CASES = 40;

    ////////////////////////////////////////////////////////
    //  Champs relatifs au "Plateau"
    ////////////////////////////////////////////////////////
    private final HashMap<Integer, UICase> cases = new HashMap<>();
    private final HashMap<Pion, Image> imagesPions = new HashMap<>();

    private final Monopoly monopoly;
    private final ArrayList<Joueur> listeJoueurs = new ArrayList<>();
    private final ArrayList<Terrain> casesP = new ArrayList<>();
    private final ArrayList<Cartes> chance = new ArrayList<>();
    private final ArrayList<Cartes> caisseCommunaute = new ArrayList<>();
    private Image imagePlateau;


    /**
     * Constructeur de UIPlateau
     * @param monopoly instance monopoly
     */
    public UIPlateau(Monopoly monopoly) {

        for (int i = 0; i < 41; i++)
            cases.put(i, new UICase());

        initImagePlateau(PLATEAU);
        initCoordonnees(COORDONNEES);
        initialisationTerrain(TERRAIN);
        initialisationCarteCommunaute(CARTE_CAISSECOMMUNAUTE);
        initialisationCarteChance(CARTE_CHANCE);
        initImagesPions();
        this.monopoly = monopoly;
    }

    /**
     * Initialise les images des pions dans la hashMap imagesPions
     */
    private void initImagesPions() {
        imagesPions.put(new Pion("Chien"), new Image("file:image/Chien.png"));
        imagesPions.put(new Pion("Bateau"), new Image("file:image/Bateau.png"));
        imagesPions.put(new Pion("Brouette"), new Image("file:image/Brouette.png"));
        imagesPions.put(new Pion("Chapeau"), new Image("file:image/Chapeau.png"));
        imagesPions.put(new Pion("Chat"), new Image("file:image/Chat.png"));
        imagesPions.put(new Pion("Chaussure"), new Image("file:image/Chaussure.png"));
        imagesPions.put(new Pion("DeACoudre"), new Image("file:image/DeACoudre.png"));
        imagesPions.put(new Pion("Voiture"), new Image("file:image/Voiture.png"));
    }

    /**
     * @return l'image du plateau
     */
    public Image getImage() {
        return imagePlateau;
    }

    /**
     * @return l'instance monopoly
     */
    public Monopoly getMonopoly() {
        return monopoly;
    }

    /**
     * Initialise l'image du plateau
     * @param nomFichierPlateau le chemin de l'image
     */
    private void initImagePlateau(String nomFichierPlateau) {
        System.out.println("Dossier : " + nomFichierPlateau + "\n");
        imagePlateau = new Image(nomFichierPlateau);
    }

    /**
     * @param numCase le numero de la case
     * @return une la case du numero numCase
     */
    public UICase getCase(int numCase) {
        if (numCase < 0 || numCase > NOMBRE_CASES)
            throw new IllegalArgumentException("Le numéro de la case est incorrect");

        return cases.get(numCase);
    }

    /**
     * initCase lit un fichier au format .csv. Une ligne doit avoir le format "n;x1;y1;x2;y2;" où n,x1,y1,x2,y2
     * sont des entiers. En cas de non respect de ce format, le programme est interrompu.
     *
     * @param nf nom du fichier contenant les coordonnées des cases du plateau. Ces coordonnées
     *           sont celles pour le plateau 800x800 pixels
     *           <p>
     */
    private void initCoordonnees(String nf) {

        Parser premierParser = new ParserCoordoneesCases(null);
        System.out.println("Fin parser de coordonnees\n");
        System.out.println("Dossier : " + nf + "\n");
        LectureFichier.lire(nf, premierParser, this);
        System.out.println("Fin Dossier : " + nf + "\n");
    }


    /**
     * dessine le palteau
     * retire toutes les images des pions du plateau puis les replace sur leur case
     * @param grillePane la grille du plateau
     */
    public void dessiner(Canvas grillePane) {
        for (int i = 0; i <= NOMBRE_CASES; i++) {
            cases.get(i).vider();
        }

        for (Pion p : monopoly.getListePions()) {
            cases.get(p.getPosition()).poser(p);
        }

        grillePane.getGraphicsContext2D().drawImage(imagePlateau, 0, 0);
        for (int i = 0; i <= NOMBRE_CASES; i++) {
            UICase c = cases.get(i);
            for (int p = 0; p < c.getNombrePion(); p++) {
                Image imagePion = imagesPions.get(c.getListePions().get(p));
                grillePane.getGraphicsContext2D().drawImage(imagePion, c.x1 + 30 * (p % 2), c.y1 + 30 * (p / 2));
            }
        }

    }

    //////////////////////////////////////////////////////////////////////////////////
    //
    //      Espace de rajout des fonctions prises dans le "Plateau" classique
    //
    //////////////////////////////////////////////////////////////////////////////////

    /**
     * Initialise le terrain
     * @param nf nom du fichier contenant le terrain
     */
    public void initialisationTerrain(String nf) {

        try {

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


            LectureFichier.lire(nf, premierParser, this);

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Initialise les cartes communautees
     * @param nf nom du fichier contenant les cartes communautees
     */
    public void initialisationCarteCommunaute(String nf) {

        try {

            Parser premierParser = new ParserCommunauteAnniversaire(null);
            premierParser = new ParserCommunauteImpots(premierParser);
            premierParser = new ParserCommunauteReparations(premierParser);
            premierParser = new ParserCommunauteDeplacement(premierParser);
            premierParser = new ParserCommunauteEncaisser(premierParser);
            premierParser = new ParserCommunauteLiberation(premierParser);
            premierParser = new ParserCommunautePayer(premierParser);
            premierParser = new ParserCommunauteChance(premierParser);


            LectureFichier.lire(nf, premierParser, this);

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Initialise les cartes chances
     * @param nf nom du fichier contenant les cartes chances
     */
    public void initialisationCarteChance(String nf) {

        try {
            //String fichierChance = "data/CartesChance.csv";


            Parser premierParser = new ParserChanceAnniversaire(null);
            premierParser = new ParserChanceImpots(premierParser);
            premierParser = new ParserChanceReparations(premierParser);
            premierParser = new ParserChanceDeplacement(premierParser);
            premierParser = new ParserChanceEncaisser(premierParser);
            premierParser = new ParserChanceLiberation(premierParser);
            premierParser = new ParserChancePayer(premierParser);


            LectureFichier.lire(nf, premierParser, this);

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }


    ////////////////////////////////////////////
    //
    //      Methodes relatives aux Cases
    //
    ////////////////////////////////////////////

    /**
     * ajoute une case
     * @param cas case a ajouter
     */
    public void ajouterCasesP(Terrain cas) {
        if (cas == null)
            throw new IllegalArgumentException("Case erronee");

        casesP.add(cas);

    }

    /**
     * @param i le numero de la case
     * @return la case du numero indique
     */
    public Terrain getCaseP(int i) {
        if (i < 0 || i > 40)
            throw new IllegalArgumentException("L'indice de la case est incorrect");

        return casesP.get(i);
    }

    /**
     * @return la liste de cases
     */
    public ArrayList<Terrain> getListeCasesP() {
        return this.casesP;
    }


    ////////////////////////////////////////////
    //
    //      Methodes relatifs aux Cartes Chance
    //
    ////////////////////////////////////////////

    /**
     * ajoute une carte chance a la liste chance
     * @param cChance la carte a ajouter
     */
    public void ajouterChance(Cartes cChance) {
        if (cChance == null)
            throw new IllegalArgumentException("Carte chance erronee");

        chance.add(cChance);

    }

    /**
     * @return la liste de cartes chance
     */
    public ArrayList<Cartes> getChance() {
        return chance;
    }


    /**
     * @return le nombre de cartes chances dans la liste chance
     */
    public int getNombreCarteChance() {
        return chance.size();
    }

    /**
     * @param i indice de la carte
     * @return la carte chance d'indice i dans la liste chance
     */
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

    /**
     * ajouter une carte communaute a la liste caisseCommunaute
     * @param cCommunaute la carte a ajouter
     */
    public void ajouterCommunaute(Cartes cCommunaute) {
        if (cCommunaute == null)
            throw new IllegalArgumentException("Carte communaute erronee");

        caisseCommunaute.add(cCommunaute);

    }

    /**
     * @return le nombre de cartes communautees dans la liste caisseCommunaute
     */
    public int getNombreCarteCommunaute() {
        return caisseCommunaute.size();
    }

    public Cartes getCommunaute(int i) {
        if (i < 0 || i > 15)
            throw new IllegalArgumentException("L'indice de la carte est incorrect");

        return caisseCommunaute.get(i);
    }

    /**
     * @return la liste des joueurs
     */
    public ArrayList<Joueur> getListeJoueurs() {
        return listeJoueurs;
    }

    ////////////////////////////////////////////
    //
    //      Methodes relatifs aux Joueurs
    //
    ////////////////////////////////////////////

    /**
     * Ajoute un joueur a la liste de joueurs du plateau
     * @param joueur le joueur a ajouter
     */
    public void ajouterJoueur(Joueur joueur) {
        if (joueur == null)
            throw new IllegalArgumentException("joueur erroné");

        listeJoueurs.add(joueur);

    }

}
