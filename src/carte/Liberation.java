package carte;


import joueur.Joueur;
import mecanismeJeu.Action;
import plateau.Plateau;

public class Liberation implements Cartes {
    private String message;
    private Joueur proprietaire;



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Joueur getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(Joueur proptietaire) {
        this.proprietaire = proptietaire;
    }

    public Liberation(String message, Joueur proprietaire) {
        this.message = message;
        setProprietaire(proprietaire);
    }

    @Override
    public void action(Joueur joueur) {
        Plateau plateau = joueur.getPlateau();

        if(joueur.getCarteSortirDePrison()>0){
            joueur.setCarteSortirDePrison(joueur.getCarteSortirDePrison() - 1);
            Action.sortirDePrison(joueur);
            //Recherche dans la pile de chance
            for(Cartes ch:plateau.getChance()){
                if(ch instanceof Liberation){
                    if(joueur.equals(((Liberation) ch).getProprietaire())){
                        ((Liberation) ch).setProprietaire(null);
                    }
                }

            }
            //Recherche dans la pile de caisse de communauté
            for(Cartes cc:plateau.getCaisseCommunaute()){
                if(cc instanceof Liberation){
                    if(joueur.equals(((Liberation) cc).getProprietaire())){
                        ((Liberation) cc).setProprietaire(null);
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Liberation{" +
                "message='" + message + '\'' +
                '}';
    }
}
