package application;

import application.event.eventChangerJoueur.EventAjouter;
import application.event.eventChangerJoueur.EventAnnuler;
import application.event.eventChangerJoueur.EventValider;
import application.ui.nomPion;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.HashMap;

public class FenetreChangerJoueur extends Stage {
    private final TextField nomJoueur = new TextField();
    private final ListView<nomPion> lvPion = new ListView<>();
    private final Monopoly monopoly;
    private final Label nbNouveauxJoueurs = new Label();
    private final HashMap<String, nomPion> tempNouveauxJoueurs = new HashMap<>();
    private int pionSelectionne = -1;


    public FenetreChangerJoueur(Monopoly monopoly) {
        this.monopoly = monopoly;

        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);

        //boutons
        Button valider = new Button();
        Button annuler = new Button();


        VBox vbCenter = new VBox();


        //zone inférieure des boutons
        HBox hbButtons = new HBox();

        //bouton valider
        valider.setText("Valider");
        valider.setOnAction(new EventValider(this));
        hbButtons.getChildren().add(valider);
        hbButtons.setAlignment(Pos.CENTER_RIGHT);

        //bouton annuler
        annuler.setText("Annuler");
        annuler.setOnAction(new EventAnnuler(this));
        hbButtons.getChildren().add(annuler);
        hbButtons.setAlignment(Pos.CENTER_RIGHT);
        setTitle("Creation de joueurs");

        //champs
        vbCenter.getChildren().add(new Label("Nom du Joueur :"));
        nomJoueur.setPrefWidth(50);
        vbCenter.getChildren().add(nomJoueur);
        vbCenter.getChildren().add(new Label("Pion :"));
        vbCenter.getChildren().add(lvPion);

        //listView de pions
        for (nomPion p : nomPion.values())
            lvPion.getItems().add((p));

        lvPion.getItems().addListener((ListChangeListener<nomPion>) arg0 -> {
            lvPion.setPrefHeight(lvPion.getItems().size() * 24 + 4); // 24 et 4 sont des nombres magiques...
        });

        lvPion.setOnMouseClicked(arg0 -> {
            pionSelectionne = lvPion.getSelectionModel().getSelectedIndex();
            System.out.println("Item : " + lvPion.getSelectionModel().getSelectedIndex());
        });

        //bouton ajouter
        Button ajouter = new Button();
        ajouter.setText("ajouter");
        ajouter.setLineSpacing(30);
        ajouter.setOnAction(new EventAjouter(this));
        vbCenter.getChildren().add(ajouter);

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        root.setCenter(vbCenter);
        root.setBottom(hbButtons);

        HBox hb = new HBox();
        vbCenter.getChildren().add(hb);
        hb.getChildren().add(nbNouveauxJoueurs);
        updateLabelnbNouveauxJoueurs();

        Scene scene = new Scene(root, 600, 400);
        setScene(scene);
        show();

    }

    public void updateLabelnbNouveauxJoueurs() {
        nbNouveauxJoueurs.setText("Nombre de joueurs : " + tempNouveauxJoueurs.size());
    }

    public HashMap<String, nomPion> getTempNouveauxJoueurs() {
        return tempNouveauxJoueurs;
    }

    public Monopoly getMonopoly() {
        return monopoly;
    }

    public TextField getNomJoueur() {
        return nomJoueur;
    }

    public int getPionSelectionne() {
        return pionSelectionne;
    }
}
