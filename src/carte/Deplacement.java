package carte;


import joueur.Joueur;
import mecanismeJeu.Action;

import static mecanismeJeu.Gestion.interactionCase;

public class Deplacement implements Cartes {
    private String message;
    private String destination;

    public Deplacement(String message, String destination) {
        setMessage(message);
        setDestination(destination);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        if (message == null || message.trim().isEmpty())
            throw new IllegalArgumentException("Message errone");
        this.message = message;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        if (destination == null || destination.trim().isEmpty())
            throw new IllegalArgumentException("Destination errone");
        this.destination = destination;
    }

    public void action(Joueur joueur) throws Exception {
        //joueur.deplacer(destination);
        switch (destination) {
            case "PRISON":
                Action.allerEnPrison(joueur);
                break;
            case "CASE DEPART":
                Action.deplacer(joueur, 0);
                break;
            case "RUE DE LA PAIX":
                Action.deplacer(joueur, 39);
                interactionCase(joueur);
                break;
            case "3 CASES":
                Action.deplacer(joueur, joueur.getPositionJoueur() - 3);
                interactionCase(joueur);
                break;
            case "GARE DE LYON":
                Action.deplacer(joueur, 15);
                interactionCase(joueur);
                break;
            case "AVENUE HENRI-MARTIN":
                Action.deplacer(joueur, 24);
                interactionCase(joueur);
                break;
            case "BOULEVARD DE LA VILLETTE":
                Action.deplacer(joueur, 11);
                interactionCase(joueur);
                break;
            case "BOULEVARD DE BELLEVILLE":
                Action.deplacer(joueur, 1);
                interactionCase(joueur);
                break;
        }

    }

    @Override
    public String toString() {
        return "Deplacement{" +
                "message='" + message + '\'' +
                ", destination='" + destination + '\'' +
                '}';
    }
}
//
