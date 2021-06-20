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

    /**
     * Constructeur EventAchatTerrain
     * @param monopoly instance monopoly
     */
    public EventAchatTerrain(Monopoly monopoly) {
        this.monopoly = monopoly;
    }

    /**
     * Fonction permettant de gerer l'achat d'un terrain
     * @param e evenement bouton acheter le terrain
     */
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
                        ta.acheter(joueur);
                        monopoly.setValueTfPorteMonnaie(joueur.getCapitalJoueur());
                        monopoly.getProprietesJoueurCourant().getItems().add(((TerrainAchetable) t).getNomTerrain());
                        monopoly.DialogAction(((TerrainAchetable) t).getNomTerrain(), false);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
        }
    }
}

