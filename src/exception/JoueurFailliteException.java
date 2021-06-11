package exception;

import application.Monopoly;
import application.ui.Pion;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleButton;
import joueur.Joueur;
import terrain.TerrainAchetable;
import terrain.TerrainConstructible;

import java.util.ArrayList;

public class JoueurFailliteException {
    public JoueurFailliteException(Joueur joueur) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("FAILLITE");
        alert.setContentText("Le joueur " + joueur.getNomJoueur() + " est en faillite.");
        alert.showAndWait();

        Monopoly monopoly = joueur.getUIPlateau().getMonopoly();

        //on fait passser son tour
        ArrayList<Joueur> lesJoueurs = monopoly.getUiPlateau().getListeJoueurs();
        int i = lesJoueurs.indexOf(joueur);
        int suivant = (i + 1) % lesJoueurs.size();

        ToggleButton buttonJoueurExclu = monopoly.getTabBoutonsJoueurs().get(i);
        ToggleButton button = monopoly.getTabBoutonsJoueurs().get(suivant);
        button.fire();
        monopoly.setValueTfPorteMonnaie(monopoly.getJoueurCourant().getCapitalJoueur());

        //on retire son boutton de la liste
        buttonJoueurExclu.setStyle("-fx-background-color:GREY");
        monopoly.getTabBoutonsJoueurs().remove(buttonJoueurExclu);

        //on retire ses propriétés
        ArrayList<TerrainAchetable> proprieteJoueur = joueur.getProprietesJoueur();

        int nb = proprieteJoueur.size();
        System.out.println("nb de propriété du joueur a suppr : " + nb);
        for (int iterator = 0; iterator < nb; iterator++) {
            TerrainAchetable t = proprieteJoueur.get(0);

            t.setProprietaire(null);
            if (t instanceof TerrainConstructible)
                ((TerrainConstructible) t).setNombreMaison(0);
            System.out.println("propriete reinitialise : " + t.getNomTerrain());
            proprieteJoueur.remove(t);
        }

        //on retire son pion
        int numJoueurCourant = monopoly.getUiPlateau().getListeJoueurs().indexOf(joueur);
        Pion pionJoueur = monopoly.getListePions().get(numJoueurCourant);
        try {
            monopoly.getUiPlateau().getCase(pionJoueur.getPosition()).enlever(pionJoueur);
        } catch (Exception e) {
            e.printStackTrace();
        }
        monopoly.getListePions().remove(pionJoueur);
        monopoly.getUiPlateau().dessiner(monopoly.getGrillePane());
        monopoly.getUiPlateau().dessiner(monopoly.getGrillePane());

        //on le supprime du jeu
        joueur.getUIPlateau().getListeJoueurs().remove(joueur);


        if (joueur.getUIPlateau().getListeJoueurs().size() < 2) {
            if (monopoly.DialogFinDePartie()) {
                Platform.exit();
                System.exit(0);
            } else {
                monopoly.redemarrerPartie(monopoly.getPrimaryStage());
            }

        }

        if (joueur.getProprietesJoueur().isEmpty())
            System.out.println("OK");
        else
            System.out.println("NOT OK");

    }
}
