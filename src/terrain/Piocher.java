package terrain;

import application.Monopoly;
import carte.*;
import joueur.Joueur;
import mecanismeJeu.Action;

public class Piocher extends TerrainNonAchetable {

    /**
     * constructeur permettant de definir un terrain demandant au joueur de piocher
     * @param numeroTerrain le numero du terrain
     * @param nomTerrain le nome du terrain
     */
    public Piocher(int numeroTerrain, String nomTerrain) {
        super(numeroTerrain, nomTerrain);
    }

    @Override
    public void action(Joueur joueur) {
        Monopoly monopoly = joueur.getUIPlateau().getMonopoly();

        switch (this.getNomTerrain()) {
            case "CHANCE":
                monopoly.getMessageFooter().setText("Vous piochez une carte chance");

                Cartes chance = Action.piocherChance(joueur);
                utiliserCarteChance(chance, monopoly);

                break;

            case "CAISSE COMMUNAUTE":
                monopoly.getMessageFooter().setText("Vous piochez une carte communauté");
                Cartes communautee = Action.piocherCommunaute(joueur);
                if (communautee instanceof Chance) {
                    monopoly.getMessageFooter().setText(((Chance) communautee).getMessage());
                    Boolean payer = monopoly.DialogActionCarteChance();
                    //on doit le gerer comme ça pour que tout s'affiche correctement
                    if (payer)
                        Action.retirer(10, joueur);
                    else {
                        Cartes chances = Action.piocherChance(joueur);
                        utiliserCarteChance(chances, monopoly);
                    }
                } else if (communautee instanceof Anniversaire) {
                    Anniversaire c = (Anniversaire) communautee;
                    monopoly.getMessageFooter().setText(c.getMessage());
                } else if (communautee instanceof Deplacement) {
                    Deplacement c = (Deplacement) communautee;
                    monopoly.getMessageFooter().setText(c.getMessage());
                } else if (communautee instanceof Encaisser) {
                    Encaisser c = (Encaisser) communautee;
                    monopoly.getMessageFooter().setText(c.getMessage());
                } else if (communautee instanceof Liberation) {
                    Liberation c = (Liberation) communautee;
                    monopoly.getMessageFooter().setText(c.getMessage());
                } else if (communautee instanceof Payer) {
                    Payer c = (Payer) communautee;
                    monopoly.getMessageFooter().setText(c.getMessage());
                }
                try {
                    communautee.action(joueur);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }

    }

    private void utiliserCarteChance(Cartes chance, Monopoly monopoly) {
        if (chance instanceof Deplacement) {
            Deplacement c = (Deplacement) chance;
            monopoly.getMessageFooter().setText(c.getMessage());
        } else if (chance instanceof Encaisser) {
            Encaisser c = (Encaisser) chance;
            monopoly.getMessageFooter().setText(c.getMessage());
        } else if (chance instanceof Liberation) {
            Liberation c = (Liberation) chance;
            monopoly.getMessageFooter().setText(c.getMessage());
        } else if (chance instanceof Payer) {
            Payer c = (Payer) chance;
            monopoly.getMessageFooter().setText(c.getMessage());
        } else if (chance instanceof Impot) {
            Impot c = (Impot) chance;
            monopoly.getMessageFooter().setText(c.getMessage());
        } else if (chance instanceof Reparation) {
            Reparation c = (Reparation) chance;
            monopoly.getMessageFooter().setText(c.getMessage());
        }
        try {
            chance.action(monopoly.getJoueurCourant());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return chaine concatenee definissant piocher
     */
    @Override
    public String toString() {
        return "Piocher{} " + super.toString();
    }
}
