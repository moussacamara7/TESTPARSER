package application.event;

import application.FenetreTerrain;
import application.Monopoly;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;
import joueur.Joueur;
import mecanismeJeu.Action;
import terrain.TerrainAchetable;
import terrain.TerrainConstructible;

public class EventAcheterMaison implements EventHandler<ActionEvent> {
    private final FenetreTerrain fenetreTerrain;

    public EventAcheterMaison(FenetreTerrain fenetreTerrain){
        this.fenetreTerrain = fenetreTerrain;
    }


    @Override
    public void handle(ActionEvent e) {
        Monopoly monopoly = fenetreTerrain.getMonopoly();
        Joueur joueur = monopoly.getJoueurCourant();

        //on sait qu'il est constructible
        TerrainConstructible terrain = (TerrainConstructible) fenetreTerrain.getTerrain();

        if(! Action.peutConstruire(terrain.getNumeroTerrain(),monopoly.getUiPlateau()))
            monopoly.DialogInfo("Tu ne peux pas acheter de maisons !");
        else{
            Action.construire(terrain.getNumeroTerrain(), monopoly.getUiPlateau());
            monopoly.setValueTfPorteMonnaie(joueur.getCapitalJoueur());

            int nbMaison = terrain.getNombreMaison();;
            if(nbMaison<5)
                fenetreTerrain.setInfo(String.format("Vous possédez %d %s", nbMaison, nbMaison>1 ? "maisons." : "maison."));
            else
                fenetreTerrain.setInfo(("Vous possédez un Hôtel !"));
        }
    }
}