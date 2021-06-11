package application;

import application.event.EventAcheterMaison;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import terrain.TerrainAchetable;
import terrain.TerrainConstructible;


public class FenetreTerrain extends Stage {

    private final Label info = new Label();
    private final Monopoly monopoly;
    private final TerrainAchetable terrain;


    public FenetreTerrain(Monopoly monopoly) {
        this.monopoly = monopoly;

        //On recupere le Terrain
        terrain = monopoly.getJoueurCourant().getProprietesJoueur().get(monopoly.getTerrainSelectionne());

        //boutons
        Button acheter = new Button();
        Button retour = new Button();

        //label
        Label nomTerrain = new Label();
        Label prixUneMaison = new Label();
        Label prixDeuxMaison = new Label();
        Label prixTroisMaison = new Label();
        Label prixQuatreMaison = new Label();
        Label prixHotel = new Label();

        VBox vbCenter = new VBox();


        //label
        vbCenter.getChildren().add(nomTerrain);
        nomTerrain.setText(terrain.getNomTerrain());
        nomTerrain.setFont(Font.font(25));

        if (!(terrain instanceof TerrainConstructible)) {
            //c'est une gare, on ne peut pas acheter de maisons
            acheter.setDisable(true);
            info.setText("Rien à acheter ici !");
        } else {
            //c'est un terrain constructible
            //on récupere la couleur pour l'afficher
            TerrainConstructible terrainC = (TerrainConstructible) terrain;
            switch (terrainC.getCouleur()) {
                case "MARRON":
                    nomTerrain.setTextFill(Color.BROWN);
                    break;
                case "BLEU CIEL":
                    nomTerrain.setTextFill(Color.BLUE);
                    break;
                case "VIOLET":
                    nomTerrain.setTextFill(Color.PURPLE);
                    break;
                case ("ORANGE"):
                    nomTerrain.setTextFill(Color.ORANGE);
                    break;
                case ("ROUGE"):
                    nomTerrain.setTextFill(Color.RED);
                    break;
                case ("JAUNE"):
                    nomTerrain.setTextFill(Color.GREENYELLOW);
                    break;
                case ("VERT"):
                    nomTerrain.setTextFill(Color.GREEN);
                    break;
                case ("BLEU FONCE"):
                    nomTerrain.setTextFill(Color.DARKBLUE);
                    break;
            }
            vbCenter.getChildren().addAll(prixUneMaison, prixDeuxMaison, prixTroisMaison, prixQuatreMaison, prixHotel);

            prixUneMaison.setText("Prix une maison :  " + terrainC.getLoyer().getPrixUnemaison());
            prixUneMaison.setFont(Font.font(15));

            prixDeuxMaison.setText("Prix deux maison :  " + terrainC.getLoyer().getPrixDeuxMaison());
            prixDeuxMaison.setFont(Font.font(15));

            prixTroisMaison.setText("Prix trois maison :  " + terrainC.getLoyer().getPrixTroisMaison());
            prixTroisMaison.setFont(Font.font(15));

            prixQuatreMaison.setText("Prix deux maison :  " + terrainC.getLoyer().getPrixQuatreMaison());
            prixQuatreMaison.setFont(Font.font(15));

            prixHotel.setText("Prix hotel :  " + terrainC.getLoyer().getPrixHotel());
            prixHotel.setFont(Font.font(15));


            int nbMaison = terrainC.getNombreMaison();
            if (nbMaison < 5)
                info.setText(String.format("Vous possédez %d %s", nbMaison, nbMaison > 1 ? "maisons." : "maison."));
            else
                info.setText("Vous possédez un Hôtel !");
        }

        vbCenter.getChildren().add(info);
        info.setWrapText(true);
        info.setTextFill(Color.GREEN);
        info.setContentDisplay(ContentDisplay.BOTTOM);
        info.setFont(Font.font(20));

        //zone inférieur des boutons
        HBox hbButtons = new HBox();

        //bouton acheter
        acheter.setText("Acheter");
        acheter.setOnAction(new EventAcheterMaison(this));
        hbButtons.getChildren().add(acheter);
        hbButtons.setAlignment(Pos.CENTER_RIGHT);

        //bouton retour
        retour.setText("Retour");
        retour.setOnAction(event -> close());
        hbButtons.getChildren().add(retour);
        hbButtons.setAlignment(Pos.CENTER_RIGHT);

        //root
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        root.setCenter(vbCenter);
        root.setBottom(hbButtons);

        Scene scene = new Scene(root, 360, 400);

        setTitle("Gestion des constructions");
        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);
        setScene(scene);
        show();
    }

    public Monopoly getMonopoly() {
        return monopoly;
    }

    public TerrainAchetable getTerrain() {
        return terrain;
    }

    public void setInfo(String text) {
        info.setText(text);
    }

}

