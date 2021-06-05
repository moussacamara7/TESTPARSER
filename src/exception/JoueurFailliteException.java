package exception;

import joueur.Joueur;
import plateau.Plateau;

public class JoueurFailliteException extends Exception{
    public JoueurFailliteException(Joueur joueur){
        Plateau plateau = joueur.getPlateau();
        System.out.println("Le joueur"+joueur.getNomJoueur()+"est en faillite.");
        plateau.getListeJoueurs().remove(joueur);
        System.out.println("Le joueur"+joueur.getNomJoueur()+"a été retiré du plateau.");
    }
}
