package exception;

import application.Monopoly;
import application.ui.UIPlateau;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleButton;
import joueur.Joueur;

import java.util.ArrayList;

public class JoueurFailliteException {
    public JoueurFailliteException(Joueur joueur) {
        UIPlateau plateau = joueur.getUIPlateau();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("FAILLITE");
        alert.setContentText("Le joueur " + joueur.getNomJoueur() + " est en faillite.");
        alert.showAndWait();

        Monopoly monopoly = joueur.getUIPlateau().getMonopoly();

        //on fait passser son tour
        ArrayList<Joueur> lesJoueurs = monopoly.getUiPlateau().getListeJoueurs();
        Joueur jc = monopoly.getJoueurCourant();
        int i = lesJoueurs.indexOf(jc);
        int suivant = (i + 1) % lesJoueurs.size();

        ToggleButton buttonJoueurExclu = monopoly.getTabBoutonsJoueurs().get(i);
        ToggleButton button = monopoly.getTabBoutonsJoueurs().get(suivant);
        button.fire();
        monopoly.getTabBoutonsJoueurs().remove(buttonJoueurExclu);
        monopoly.setValueTfPorteMonnaie(monopoly.getJoueurCourant().getCapitalJoueur());

        //on le supprime du jeu
        joueur.getUIPlateau().getListeJoueurs().remove(joueur);

        //System.out.println("Le joueur"+joueur.getNomJoueur()+"est en faillite.");
        //System.out.println("Le joueur"+joueur.getNomJoueur()+"a été retiré du plateau.");
    }
}
