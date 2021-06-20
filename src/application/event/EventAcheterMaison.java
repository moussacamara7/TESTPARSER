package application.event;

import application.FenetreTerrain;
import application.Monopoly;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import joueur.Joueur;
import mecanismeJeu.Action;
import terrain.TerrainConstructible;

public class EventAcheterMaison implements EventHandler<ActionEvent> {
    private final FenetreTerrain fenetreTerrain;

    /**
     * Constructeur EventAcheterMaison
     * @param fenetreTerrain fenetre de gestion de terrain
     */
    public EventAcheterMaison(FenetreTerrain fenetreTerrain) {
        this.fenetreTerrain = fenetreTerrain;
    }


    /**
     * gere l'achat de maisons
     * @param e evenement de clique sur acheter
     */
    @Override
    public void handle(ActionEvent e) {
        Monopoly monopoly = fenetreTerrain.getMonopoly();
        Joueur joueur = monopoly.getJoueurCourant();

        //on sait qu'il est constructible
        TerrainConstructible terrain = (TerrainConstructible) fenetreTerrain.getTerrain();

        if (!Action.peutConstruire(terrain.getNumeroTerrain(), monopoly.getUiPlateau())) {
            fenetreTerrain.setInfo("Tu ne peux pas acheter pour l'instant!");
        } else {
            terrain.construire(joueur);
            monopoly.setValueTfPorteMonnaie(joueur.getCapitalJoueur());

            int nbMaison = terrain.getNombreMaison();
            if (nbMaison < 5)
                fenetreTerrain.setInfo(String.format("Vous possédez %d %s", nbMaison, nbMaison > 1 ? "maisons." : "maison."));
            else
                fenetreTerrain.setInfo(("Vous possédez un Hôtel !"));
        }
    }
}
