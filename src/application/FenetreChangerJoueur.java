package application;

import application.Monopoly;
import application.event.EventAcheterMaison;
import application.event.eventChangerJoueur.EventAjouter;
import application.event.eventChangerJoueur.EventValider;
import application.ui.nomPion;
import com.sun.javafx.scene.traversal.Direction;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;

public class FenetreChangerJoueur extends Stage {
    private TextField nomJoueur = new TextField();
    private ListView<nomPion> lvPion = new ListView<>();
    private  int pionSelectionne = -1;
    private Monopoly monopoly;
    private Label nbNouveauxJoueurs = new Label();



    public FenetreChangerJoueur(Monopoly monopoly){
        this.monopoly = monopoly;

        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);

        //boutons
        Button valider = new Button();
        Button annuler = new Button();


        VBox vbCenter = new VBox();


        //zone inférieur des boutons
        HBox hbButtons = new HBox();

        //bouton valider
        valider.setText("Valider");
        valider.setOnAction(new EventValider(monopoly));
        hbButtons.getChildren().add(valider);
        hbButtons.setAlignment(Pos.CENTER_RIGHT);

        //bouton annuler
        annuler.setText("Annuler");
        annuler.setOnAction(event -> close());
        hbButtons.getChildren().add(annuler);
        hbButtons.setAlignment(Pos.CENTER_RIGHT);
        setTitle("Creation de joueurs");

        //champs
        vbCenter.getChildren().add(new Label("Nom du Joueur :"));
        nomJoueur.setPrefWidth(50);
        vbCenter.getChildren().add(nomJoueur);
        vbCenter.getChildren().add(new Label("Pion :"));
        vbCenter.getChildren().add(lvPion);


        for(nomPion p : nomPion.values())
            lvPion.getItems().add((p));

        lvPion.getItems().addListener((ListChangeListener<nomPion>) arg0 -> {
            lvPion.setPrefHeight(lvPion.getItems().size() * 24 + 4); // 24 et 4 sont des nombres magiques...
        });

        lvPion.setOnMouseClicked(arg0 -> {
            pionSelectionne = lvPion.getSelectionModel().getSelectedIndex();
            System.out.println("Item : " + lvPion.getSelectionModel().getSelectedIndex());
        });

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

    public void updateLabelnbNouveauxJoueurs(){
        nbNouveauxJoueurs.setText("Nombre de joueurs " +getMonopoly().getNouveauxJoueurs().size());
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
