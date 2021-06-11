package application.event;

import application.Monopoly;
import application.ui.UIPlateau;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import joueur.Joueur;
import mecanismeJeu.Action;
import terrain.Terrain;
import terrain.TerrainAchetable;

public class EventAchatTerrain implements EventHandler<ActionEvent> {

    private final Monopoly monopoly;

    public EventAchatTerrain(Monopoly monopoly) {
        this.monopoly = monopoly;
    }

    @Override
    public void handle(ActionEvent e) {
        UIPlateau UIP = monopoly.getJoueurCourant().getUIPlateau();
        Joueur joueur = monopoly.getJoueurCourant();
        Terrain t = UIP.getCaseP(joueur.getPositionJoueur());


        if (!t.estAchetable()) {
            monopoly.DialogAction("Ce terrain n'est pas achetable !!", true);
        } else {
            TerrainAchetable ta = (TerrainAchetable) t;
            if (ta.aUnProprietaire()) {
                monopoly.DialogAction("Ce terrain à déja un propriétaire !!", true);
            } else {
                if (ta.getPrixAchat() > joueur.getCapitalJoueur()) {
                    monopoly.DialogAction("Vous n'avez pas l'argent nécessaire !!", true);
                } else {
                    try {
                        Action.acheterPropriete(joueur, t);
                        monopoly.setValueTfPorteMonnaie(joueur.getCapitalJoueur());
                        monopoly.getProprietesJoueurCourant().getItems().add(((TerrainAchetable) t).getNomTerrain());
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
        }
    }
}

