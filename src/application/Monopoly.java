package application;


import application.event.*;
import application.ui.Pion;
import application.ui.UIPlateau;
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

import java.util.ArrayList;
import java.util.Optional;


public class Monopoly extends Application {
    public final static String ACTION_ACHAT_TERRAIN = "Acheter le terrain";
    public final static String ACTION_GERER_MAISON = "Gérer les maisons et les hôtels";
    public final static String ACTION_PAYER_PRISON = "Payer pour sortir de prison";
    public final static String ACTION_LIBERATION = "Utiliser une carte de libération";
    public final static String ACTION_PASSER = "Passer au suivant";
    public final static String ACTION_JOUER = "Lancer les dés";


    private final ArrayList<ToggleButton> tabBoutonsJoueurs = new ArrayList<>();
    /**
     * YL : la liste des joueurs est représentée par une liste de noms, ainsi que la liste des pions.
     * --> A modifier !!
     */
    private final ArrayList<Joueur> listeJoueurs = new ArrayList<>();
    private final ArrayList<Pion> listePions = new ArrayList<>();
    private final FenetreTerrain fenetreTerrain = new FenetreTerrain();
    private UIPlateau uiPlateau;
    private Canvas grillePane;
    private Button bAvancer;
    private TextField tfDe1;
    private TextField tfDe2;
    private Label messageFooter;
    /**
     * YL : ListView peut contenir n'importe quel type d'objet. Pour l'instant, ce sont des String
     * --> A modifier !!
     */
    private ListView<TerrainAchetable> proprietesJoueurCourant;
    private Joueur joueurCourant;
    private int terrainSelectionne = -1;
    private TextField tfPorteMonnaie;
    private int nbDoubles = 0;

    public static void main(String[] args) {
        launch(args);
    }

    public ListView<TerrainAchetable> getZoneProprietes() {
        return proprietesJoueurCourant;
    }

    public ArrayList<ToggleButton> getTabBoutonsJoueurs() {
        return tabBoutonsJoueurs;
    }

    public ArrayList<Joueur> getListeJoueurs() {
        return listeJoueurs;
    }

    public TextField getTfValeurDe1() {
        return tfDe1;
    }

    public TextField getTfValeurDe2() {
        return tfDe2;
    }

    public TextField getTfPorteMonnaie() {
        return tfPorteMonnaie;
    }

    public UIPlateau getUiPlateau() {
        return uiPlateau;
    }

    public Canvas getGrillePane() {
        return grillePane;
    }

    @Override
    public void start(Stage primaryStage) {
        try {

            initPartie();


            BorderPane root = new BorderPane();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);

            initPanneauPlateau(root);

            initPanneauDroit(root);
            initFooter(root);


            uiPlateau.dessiner(grillePane);

            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

    private void initFooter(BorderPane root) {
        HBox footer = new HBox();
        footer.setAlignment(Pos.BASELINE_LEFT);

        messageFooter = new Label("");
        footer.getChildren().add(messageFooter);

        root.setBottom(footer);
    }

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

        proprietesJoueurCourant.getItems().addListener((ListChangeListener<TerrainAchetable>) arg0 -> {
            proprietesJoueurCourant.setPrefHeight(proprietesJoueurCourant.getItems().size() * 24 + 4); // 24 et 4 sont des nombres magiques...
        });

        proprietesJoueurCourant.setOnMouseClicked(arg0 -> {
            terrainSelectionne = proprietesJoueurCourant.getSelectionModel().getSelectedIndex();
            System.out.println("Item : " + proprietesJoueurCourant.getSelectionModel().getSelectedIndex());
        });

        panneauDroit.getChildren().add(proprietesJoueurCourant);
    }

    private void initActions(VBox panneauDroit) {
        VBox box = new VBox();

        bAvancer = new Button(ACTION_JOUER);
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

        for (Joueur joueur : listeJoueurs) {

            ToggleButton bJoueur = new ToggleButton(joueur.getNomJoueur());
            bJoueur.setToggleGroup(group);
            bJoueur.setOnAction(new EventChoixJoueur(this));
            bJoueur.setUserData(joueur);


            box.getChildren().add(bJoueur);
            tabBoutonsJoueurs.add(bJoueur);
        }

        panneau.getChildren().add(box);
    }

    private void initPanneauPlateau(BorderPane root) {
        Image image = uiPlateau.getImage();
        grillePane = new Canvas(image.getWidth(), image.getHeight());
        root.setCenter(grillePane);
    }

    private void initPartie() {

        uiPlateau = new UIPlateau();

        listeJoueurs.add(new Joueur("Han",uiPlateau));
        listePions.add(new Pion("Bateau"));

        listeJoueurs.add(new Joueur("Luke", uiPlateau));
        listePions.add(new Pion("Chien"));

        listeJoueurs.add(new Joueur("Yoda", uiPlateau));
        listePions.add(new Pion("Voiture"));

        //uiPlateau = new UIPlateau(/* ? */);
    }

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

    public void DialogInfo(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText("");
        alert.setContentText(message);
        alert.showAndWait();
    }

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

    public int getNbDoubles() {
        return nbDoubles;
    }

    public void setNbDoubles(int nbDoubles) {
        this.nbDoubles = nbDoubles;
    }

    public Label getMessageFooter() {
        return messageFooter;
    }

    public int getTerrainSelectionne() {
        return terrainSelectionne;
    }

    public FenetreTerrain getFenetreTerrain() {
        return fenetreTerrain;
    }

    public Joueur getJoueurCourant() {
        return joueurCourant;
    }

    public void setJoueurCourant(Joueur j) {
        joueurCourant = j;

    }

    public void setValueTfPorteMonnaie(String value) {
        tfPorteMonnaie.setText(value);
    }

    public void setValueTfPorteMonnaie(int value) {
        tfPorteMonnaie.setText(String.valueOf(value));
    }
}


