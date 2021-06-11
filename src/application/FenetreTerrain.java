package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import terrain.Terrain;
import terrain.TerrainAchetable;
import terrain.TerrainConstructible;


public class FenetreTerrain extends Stage {


    private final Monopoly monopoly;

    //boutons
    private final ToggleButton acheter = new ToggleButton();
    private final ToggleButton retour = new ToggleButton();

    //label
    private final Label nomTerrain = new Label();
    private final Label prixUneMaison = new Label();
    private final Label prixDeuxMaison = new Label();
    private final Label prixTroisMaison = new Label();
    private final Label prixQuatreMaison = new Label();
    private final Label prixHotel = new Label();
    private final Label info = new Label();



    public FenetreTerrain(Monopoly monopoly) {
        this.monopoly = monopoly;

        VBox vbCenter = new VBox();

        //On recupere le Terrain
        TerrainAchetable terrain = monopoly.getJoueurCourant().getProprietesJoueur().get(monopoly.getTerrainSelectionne());

        //label
        vbCenter.getChildren().add(nomTerrain);
        nomTerrain.setText(terrain.getNomTerrain());
        nomTerrain.setFont(Font.font(25));

        if(!(terrain instanceof TerrainConstructible)) {
            //c'est une gare, on ne peut pas acheter de maisons
            acheter.setDisable(true);
            info.setText("Rien à acheter ici !");
        }else{
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
            vbCenter.getChildren().add(prixUneMaison);
            prixUneMaison.setText("Prix une maison :  " +terrainC.getLoyer().getPrixUnemaison());
            prixUneMaison.setFont(Font.font(15));

            vbCenter.getChildren().add(prixDeuxMaison);
            prixDeuxMaison.setText("Prix deux maison :  " +terrainC.getLoyer().getPrixDeuxMaison());
            prixDeuxMaison.setFont(Font.font(15));


            vbCenter.getChildren().add(prixTroisMaison);
            prixTroisMaison.setText("Prix trois maison :  " +terrainC.getLoyer().getPrixTroisMaison());
            prixTroisMaison.setFont(Font.font(15));


            vbCenter.getChildren().add(prixQuatreMaison);
            prixQuatreMaison.setText("Prix deux maison :  " +terrainC.getLoyer().getPrixQuatreMaison());
            prixQuatreMaison.setFont(Font.font(15));


            vbCenter.getChildren().add(prixHotel);
            prixHotel.setText("Prix hotel :  " +terrainC.getLoyer().getPrixHotel());
            prixHotel.setFont(Font.font(15));



            if(terrainC.getNombreMaison()<5)
                info.setText("Vous possédez  " +terrainC.getNombreMaison() +" maison(s).");
            else
                info.setText("Vous possédez un Hotel !");
        }

        vbCenter.getChildren().add(info);
        info.setTextFill(Color.GREEN);
        info.setContentDisplay(ContentDisplay.BOTTOM);
        info.setFont(Font.font(20));

        //zone inférieur des boutons
        HBox hbButtons = new HBox();

        //bouton acheter
        acheter.setText("Acheter");
        acheter.setOnAction(acheterMaison());
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



    public EventHandler<ActionEvent> acheterMaison(){






        return null;
    }



}

