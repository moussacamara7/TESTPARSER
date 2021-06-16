package application;


import application.event.*;
import application.event.EventChoixJoueur;
import application.event.eventChangerJoueur.EventChangerJoueur;
import application.ui.Pion;
import application.ui.UICase;
import application.ui.UIPlateau;
import application.ui.nomPion;
import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import joueur.Joueur;
import terrain.TerrainAchetable;

import java.util.*;


public class Monopoly extends Application {
    public final static String ACTION_ACHAT_TERRAIN = "Acheter le terrain";
    public final static String ACTION_GERER_MAISON = "Gérer les maisons et les hôtels";
    public final static String ACTION_PAYER_PRISON = "Payer pour sortir de prison";
    public final static String ACTION_LIBERATION = "Utiliser une carte de libération";
    public final static String ACTION_PASSER = "Passer au suivant";
    public final static String ACTION_JOUER = "Lancer les dés";

    private ArrayList<ToggleButton> tabBoutonsJoueurs = new ArrayList<>();

    private ArrayList<Pion> listePions = new ArrayList<>();
    private UIPlateau uiPlateau;
    private Canvas grillePane;
    private TextField tfDe1;
    private TextField tfDe2;
    private Label messageFooter;

    private ListView<String> proprietesJoueurCourant;
    private boolean tourTermine = false;
    private Joueur joueurCourant;
    private int terrainSelectionne = -1;
    private TextField tfPorteMonnaie;
    private int nbDoubles = 0;
    private Stage primaryStage;

    private HashMap<String, nomPion> nouveauxJoueurs = new HashMap<>();

    /**
     * fonction principale
     * @param args arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * @return la ListView proprietesJoueurCourant
     */
    public ListView<String> getZoneProprietes() {
        return proprietesJoueurCourant;
    }

    /**
     * @return la liste tabBoutonsJoueurs
     */
    public ArrayList<ToggleButton> getTabBoutonsJoueurs() {
        return tabBoutonsJoueurs;
    }

    /**
     * @return le TextField tfDe1
     */
    public TextField getTfValeurDe1() {
        return tfDe1;
    }

    /**
     * @return le TextField tfDe2
     */
    public TextField getTfValeurDe2() {
        return tfDe2;
    }

    /**
     * Initialise le TextField tfDe1
     * @param tfDe1 TextField du premier de
     */
    public void setTfDe1(String tfDe1) {
        this.tfDe1.setText(tfDe1);
    }

    /**
     * Initialise le TextField tfDe2
     * @param tfDe2 TextField du deuxieme  de
     */
    public void setTfDe2(String tfDe2) {
        this.tfDe2.setText(tfDe2);
    }

    /**
     * @return le uiPlateau
     */
    public UIPlateau getUiPlateau() {
        return uiPlateau;
    }

    /**
     * @return la toile grillePane
     */
    public Canvas getGrillePane() {
        return grillePane;
    }

    /**
     * Fonction de creation la scene
     * @param primaryStage scene principale de l'interface
     */
    @Override
    public void start(Stage primaryStage) {
        startGame(primaryStage);

    }

    /**
     * Fonction pour creer une partie de jeu et initialiser l'interface
     * @param primaryStage scene principale de l'interface
     */
    public void startGame(Stage primaryStage) {
        try {
            initPartie();


            BorderPane root = new BorderPane();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);

            initPanneauPlateau(root);

            initPanneauDroit(root);
            initFooter(root);


            uiPlateau.dessiner(grillePane);
            setValueTfPorteMonnaie(joueurCourant.getCapitalJoueur());

            this.primaryStage = primaryStage;

            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * fonction d'initialisation du panneau droit de l'interface
     * @param root racine de la scene principale
     */
    private void initPanneauDroit(BorderPane root) {
        VBox panneauDroit = new VBox();
        panneauDroit.setAlignment(Pos.TOP_CENTER);

        initBoutonsJoueurs(panneauDroit);
        initDes(panneauDroit);
        initActions(panneauDroit);
        initZonePropriete(panneauDroit);

        tabBoutonsJoueurs.get(0).fire();

        root.setRight(panneauDroit);
    }

    /**
     * fonction d'initialisation de la partie inférieure de l'interface
     * @param root racine de la scene principale
     */
    private void initFooter(BorderPane root) {
        HBox footer = new HBox();
        footer.setAlignment(Pos.BASELINE_LEFT);

        messageFooter = new Label("");
        footer.getChildren().add(messageFooter);
        messageFooter.setMinWidth(555);

        Button redemarrer = new Button();
        redemarrer.setText("Redémarrer");
        redemarrer.setOnAction(new EventRedemarrer(this));
        footer.getChildren().add(redemarrer);


        Button changerJoueur = new Button();
        changerJoueur.setText("Nouveaux Joueurs");
        footer.getChildren().add(changerJoueur);
        changerJoueur.setOnAction(new EventChangerJoueur(this));

        Button quitter = new Button();
        quitter.setText("Quitter");
        footer.getChildren().add(quitter);
        quitter.setOnAction(new EventQuitter());

        root.setBottom(footer);
    }


    /**
     * fonction d'initialisation de la partie inferieure du panneau droit
     * @param panneauDroit panneau droit de l'interface
     */
    private void initZonePropriete(VBox panneauDroit) {
        panneauDroit.getChildren().add(new Label(" "));

        HBox hb = new HBox();
        hb.getChildren().add(new Label("Porte monnaie : "));
        tfPorteMonnaie = new TextField("0");
        tfPorteMonnaie.setEditable(false);
        hb.getChildren().add(tfPorteMonnaie);

        panneauDroit.getChildren().add(hb);

        panneauDroit.getChildren().add(new Label(" "));
        panneauDroit.getChildren().add(new Label("Liste des propriétés :"));


        proprietesJoueurCourant = new ListView<>();
        proprietesJoueurCourant.setPrefHeight(0);

        proprietesJoueurCourant.getItems().addListener((ListChangeListener<String>) arg0 -> {
            proprietesJoueurCourant.setPrefHeight(proprietesJoueurCourant.getItems().size() * 24 + 4); // 24 et 4 sont des nombres magiques...
        });

        proprietesJoueurCourant.setOnMouseClicked(arg0 -> {
            terrainSelectionne = proprietesJoueurCourant.getSelectionModel().getSelectedIndex();
            //System.out.println("Item : " + proprietesJoueurCourant.getSelectionModel().getSelectedIndex());
        });

        panneauDroit.getChildren().add(proprietesJoueurCourant);
    }

    /**
     * Initialise les boutons du panneau droit de l'interface
     * @param panneauDroit panneau droit de l'interface
     */
    private void initActions(VBox panneauDroit) {
        VBox box = new VBox();

        Button bAvancer = new Button(ACTION_JOUER);
        bAvancer.setOnAction(new EventJouer(this));
        box.getChildren().add(bAvancer);

        Button bPasser = new Button(ACTION_PASSER);
        bPasser.setOnAction(new EventPasser(this));
        box.getChildren().add(bPasser);

        Button acheterTerrain = new Button(ACTION_ACHAT_TERRAIN);
        acheterTerrain.setOnAction(new EventAchatTerrain(this));
        box.getChildren().add(acheterTerrain);

        Button gererMaisons = new Button(ACTION_GERER_MAISON);
        gererMaisons.setOnAction(new EventGererMaison(this));
        box.getChildren().add(gererMaisons);

        Button payerPrison = new Button(ACTION_PAYER_PRISON);
        payerPrison.setOnAction(new EventPayerPrison(this));
        box.getChildren().add(payerPrison);

        Button liberation = new Button(ACTION_LIBERATION);
        liberation.setOnAction(new EventLiberation(this));
        box.getChildren().add(liberation);

        panneauDroit.getChildren().add(box);
    }

    /**
     * Initialise la zone des des dans l'interface
     * @param panneau panneau supérieur du panneua droit de l'interface
     */
    private void initDes(VBox panneau) {
        Label des = new Label("Dés :");
        tfDe1 = new TextField();
        tfDe1.setPrefColumnCount(2);
        tfDe2 = new TextField();
        tfDe2.setPrefColumnCount(2);
        HBox hb = new HBox();

        hb.getChildren().addAll(des, tfDe1, tfDe2);
        hb.setSpacing(2);

        panneau.getChildren().add(hb);
    }

    private void initBoutonsJoueurs(VBox panneau) {
        ToggleGroup group = new ToggleGroup();

        HBox box = new HBox();
        box.setMouseTransparent(true);

        for (Joueur joueur : uiPlateau.getListeJoueurs()) {

            ToggleButton bJoueur = new ToggleButton(joueur.getNomJoueur());
            bJoueur.setToggleGroup(group);
            bJoueur.setOnAction(new EventChoixJoueur(this));
            bJoueur.setUserData(joueur);


            box.getChildren().add(bJoueur);
            tabBoutonsJoueurs.add(bJoueur);
        }

        panneau.getChildren().add(box);
    }

    /**
     * Initialise le plateau de jeu de l'interface
     * @param root racine de la scene principale
     */
    private void initPanneauPlateau(BorderPane root) {
        Image image = uiPlateau.getImage();
        grillePane = new Canvas(image.getWidth(), image.getHeight());
        root.setCenter(grillePane);
    }

    /**
     * Initialise le plateau et les joueurs de l'interface
     * Les joueurs par defauts sont Han Luke et Yoda, sinon l'utilisateur choisit
     * les noms et le nombre de joueurs avec leur pion, qui seront choisit en priorite
     */
    private void initPartie() {

        uiPlateau = new UIPlateau(this);
        if(nouveauxJoueurs.size() <2) {
            //par défaut
            creerJoueurEtAjouter("Han", uiPlateau, nomPion.DeACoudre);
            creerJoueurEtAjouter("Luke", uiPlateau, nomPion.Chien);
            creerJoueurEtAjouter("Yoda", uiPlateau, nomPion.Voiture);
        }else{
            Iterator it = nouveauxJoueurs.entrySet().iterator();
            while(it.hasNext()){
                Map.Entry e = (Map.Entry) it.next();
                creerJoueurEtAjouter((String) e.getKey(),uiPlateau,(nomPion)e.getValue());
            }
        }
    }

    /**
     * Ouvre une boite de dialogue relative a l'achat d'un terrain
     * @param message message a afficher
     * @param erreur vrai en cas d'erreur
     */
    public void DialogAction(String message, boolean erreur) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Achat d'un terrain");
        alert.setContentText(message);

        if (erreur) {
            alert.setHeaderText("Tu ne peux pas faire cette action !");
        } else {
            alert.setAlertType(AlertType.INFORMATION);
            alert.setHeaderText("Achat effectué");
        }

        alert.showAndWait();
    }

    /**
     * Ouvre une boite de dialogue d'information
     * @param message message a afficher
     */
    public void DialogInfo(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText("");
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Ouvre une boite de dialogue relative a la carte Chance
     * @return vrai si le joueur paye au lieu de piocher
     */
    public Boolean DialogActionCarteChance() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Carte Chance");
        alert.setContentText("Piochez une carte chance ou payer 10 ?");

        ButtonType payerButton = new ButtonType("Payer");
        ButtonType piocherButton = new ButtonType("Piocher");
        alert.getButtonTypes().setAll(payerButton, piocherButton);
        Optional<ButtonType> result = alert.showAndWait();
        
        return result.get().equals(payerButton);
    }

    /**
     * Ouvre une boite de dialogue relative a la fin de partie
     * Elle propose de Rejouer ou de quitter le programme
     * @return vrai si on souhaite quitter le programme
     */
    public boolean DialogFinDePartie() {
        Alert alert = new Alert(AlertType.NONE);
        alert.setTitle("Partie terminée");
        alert.setContentText("Le joueur " + getJoueurCourant().getNomJoueur() + " a gagnée la partie !");
        ButtonType Quitter = new ButtonType("Quitter");
        ButtonType Rejouer = new ButtonType("Rejouer");
        alert.getButtonTypes().setAll(Quitter, Rejouer);
        Optional<ButtonType> result = alert.showAndWait();

        return result.get().equals(Quitter);

    }

    /**
     * @return la listView proprietesJoueurCourant
     */
    public ListView<String> getProprietesJoueurCourant() {
        return proprietesJoueurCourant;
    }

    /**
     * mets a jour la liste des proprietes du joueur courant dans l'interface
     */
    public void updateProprieteJoueurCourant() {

        this.getProprietesJoueurCourant().getItems().removeAll();

        for (TerrainAchetable t : this.getJoueurCourant().getProprietesJoueur()) {
            String nomTerrain = t.getNomTerrain();
            this.getProprietesJoueurCourant().getItems().add(nomTerrain);
        }
    }

    /**
     * creer un joueur et l'ajoute au plateau avec son pion
     * @param nom le nom du joueur
     * @param plateau le plateau de jeu
     * @param nomPion le pion du joueur
     */
    public void creerJoueurEtAjouter(String nom, UIPlateau plateau, nomPion nomPion) {
        plateau.ajouterJoueur(new Joueur(nom, plateau));
        Pion pion = new Pion(nomPion.getNom());
        listePions.add(pion);
        UICase caseDepart = uiPlateau.getCase(0);
        caseDepart.poser(pion);
    }

    /**
     * @return la liste des pions
     */
    public ArrayList<Pion> getListePions() {
        return listePions;
    }

    /**
     * Relance une partie avec les memes joueurs
     */
    public void redemarrerPartie() {
        primaryStage.close();
        setTourTermine(false);
        setJoueurCourant(null);
        tabBoutonsJoueurs = new ArrayList<>();
        listePions = new ArrayList<>();

        startGame(primaryStage);
    }

    /**
     * @return la hashMap des nouveaux joueurs
     */
    public HashMap<String, nomPion> getNouveauxJoueurs() {
        return nouveauxJoueurs;
    }

    /**
     * @return l'etat du tour du joueur
     */
    public boolean isTourTermine() {
        return tourTermine;
    }

    /**
     * Initialise l'etat du tour du joueur
     * @param tourTermine vrai si le joueur ne peux plus lancer les des dans ce tour
     */
    public void setTourTermine(boolean tourTermine) {
        this.tourTermine = tourTermine;
    }

    /**
     * @return le nombre de double par le joueur ce tour ci
     */
    public int getNbDoubles() {
        return nbDoubles;
    }

    /**
     * Initialise le nombre de double par le joueur ce tour ci
     * @param nbDoubles le nombre de double par le joueur ce tour ci
     */
    public void setNbDoubles(int nbDoubles) {
        this.nbDoubles = nbDoubles;
    }

    /**
     * @return le Label du message de la partie inferieure de l'interface
     */
    public Label getMessageFooter() {
        return messageFooter;
    }

    /**
     * @return la valeur du terrain selectionne dans la ListView, -1 si aucun
     */
    public int getTerrainSelectionne() {
        return terrainSelectionne;
    }

    /**
     * Initialise le terrain selectionne dans la ListView
     * @param terrainSelectionne terrain selectionne dans la ListView
     */
    public void setTerrainSelectionne(int terrainSelectionne) {
        this.terrainSelectionne = terrainSelectionne;
    }

    /**
     * @return le joueur courant
     */
    public Joueur getJoueurCourant() {
        return joueurCourant;
    }

    /**
     * Initialise le joueur courant
     * @param j le joueur courant
     */
    public void setJoueurCourant(Joueur j) {
        joueurCourant = j;
    }

    /**
     * Initialise le TextField de la valeur du porte monnaie
     * @param value valeur du porte monnaie
     */
    public void setValueTfPorteMonnaie(int value) {
        tfPorteMonnaie.setText(String.valueOf(value));
    }
}


