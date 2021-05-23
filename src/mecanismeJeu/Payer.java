package mecanismeJeu;

public class Payer extends Action{
    public Payer(Action suivant) {
        super(suivant);
    }

    public void fait(int ligne) throws Exception{
        throw new Exception("Arguments insuffisants");
    }



    public void fait(int somme,joueur.Joueur depart, joueur.Joueur destination) throws Exception{
        if(depart.getCapitalJoueur() < somme)
            throw new Exception("Capital insuffisant");

        //On retire l'argent au Joueur qui donne
        depart.setCapitalJoueur(depart.getCapitalJoueur() - somme);
        //On ajoute l'argent au joueur qui le recoit
        destination.setCapitalJoueur(destination.getCapitalJoueur() + somme);
    }

    public boolean saitFaire(int ligne) {
        if(ligne == 3)
            return true;
        return false;
    }
}
