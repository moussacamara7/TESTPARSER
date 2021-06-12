package application.event.eventChangerJoueur;

import application.Monopoly;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class EventValider implements EventHandler<ActionEvent> {
    private final Monopoly monopoly;

    public EventValider(Monopoly monopoly){
        this.monopoly = monopoly;
    }

    @Override
    public void handle(ActionEvent event) {
        if(monopoly.getNouveauxJoueurs().size()<2)
            monopoly.DialogInfo("Il faut au moins deux joueurs !!");
        else
            monopoly.redemarrerPartie(monopoly.getPrimaryStage());
    }
}
