package exception;

import joueur.Joueur;
import plateau.Plateau;
import application.ui.UIPlateau;

public class JoueurFailliteException extends Exception{
    public JoueurFailliteException(Joueur joueur){
        UIPlateau plateau = joueur.getPlateau();
        System.out.println("Le joueur"+joueur.getNomJoueur()+"est en faillite.");
        plateau.getListeJoueurs().remove(joueur);
        System.out.println("Le joueur"+joueur.getNomJoueur()+"a été retiré du plateau.");
    }
}
