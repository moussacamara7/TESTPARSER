package mecanismeJeu;

import application.ui.UIPlateau;
import carte.Cartes;
import exception.JoueurFailliteException;
import exception.MonopolyException;
import joueur.Joueur;
import terrain.Terrain;
import terrain.TerrainAchetable;
import terrain.TerrainConstructible;

import java.util.Random;


public class Action {

    /****************************
     *
     * actions :
     *
     *      payer un joueur
     *      acheter une propriété
     *      construire
     *      aller en prison
     *      sortir de prison
     *      se deplacer
     *      hypotequer
     *
     ****************************/


    /**
     * pour payer depuis la banque
     *
     * @param somme       somme a payer
     * @param destination le joueur recevant l'argent
     */
    public static void payer(int somme, Joueur destination) {
        if (somme <= 0)
            throw new IllegalArgumentException("somme invalide");
        destination.setCapitalJoueur(destination.getCapitalJoueur() + somme);
    }


    /**
     * pour retirer une somme à un joueur
     *
     * @param somme  somme a retirer
     * @param joueur joueur qui paye
     */
    public static void retirer(int somme, Joueur joueur) {
        if (somme < 0)
            throw new IllegalArgumentException("somme invalide");
        if (joueur.getCapitalJoueur() < somme)
            new JoueurFailliteException(joueur);

        joueur.setCapitalJoueur(joueur.getCapitalJoueur() - somme);
    }

    /**
     * Un joueur paye un autre joueur
     * on retire l'argent au joueur qui donne et on paye le joueur qui recoit
     *
     * @param somme       somme a payer
     * @param depart      joueur qui donne la somme
     * @param destination joueur qui recoit la somme
     * @throws MonopolyException lance l'exception quand les conditions de sont pas respectées
     */
    public static void payer(int somme, Joueur depart, Joueur destination) throws MonopolyException {
        if (somme <= 0)
            throw new IllegalArgumentException("somme invalide");
        //on ne considere pas l'exception ou depart = destination

        Action.retirer(somme, depart);
        Action.payer(somme, destination);
    }

    /**
     * Methode permettant a un joueur d'acheter une propriete
     *
     * @param joueur joueur qui achete
     * @param T      propriete a acheter, ca doit etre un terrain achetable
     * @throws Exception
     */
    public static void acheterPropriete(Joueur joueur, Terrain T) throws MonopolyException {

        if (!T.estAchetable())
            throw new IllegalArgumentException("terrain non achetable");

        //c'est bien un terrain achetable
        TerrainAchetable Ta = (TerrainAchetable) T;

        //on vérifie que le terrain n'ai pas de propriétaire
        if (Ta.aUnProprietaire())
            throw new MonopolyException("Le terrain a deja un proprietaire");

        //on vérifie si le joueur peut l'acheter
        if (joueur.getCapitalJoueur() < Ta.getPrixAchat())
            throw new MonopolyException("Capital insuffisant");

        //On retire l'argent au joueur et on lui ajoute la popriété
        joueur.setCapitalJoueur(joueur.getCapitalJoueur() - Ta.getPrixAchat());
        joueur.ajouterPropriete(Ta);
        ((TerrainAchetable) T).setProprietaire(joueur);
    }

    /**
     * Methode permettant de mettre un joueur en prison
     * Definie son etat prisonnier et le deplace case 40 (prison)
     *
     * @param joueur joueur qui va en prison
     */
    public static void allerEnPrison(Joueur joueur) {
        if (joueur.isEstprisonnier())
            throw new IllegalArgumentException("joueur deja prisonnier");
        joueur.setEstprisonnier(true);
        joueur.setPositionJoueur(40);
    }

    /**
     * Methode permettant de sortir un joueur de prison
     * Definie son etat prisonnier et le deplace case 10 (simple visite)
     *
     * @param joueur joueur qui sort de prison
     */
    public static void sortirDePrison(Joueur joueur) {
        if (!joueur.isEstprisonnier())
            throw new IllegalArgumentException("joueur non prisonnier");
        joueur.setEstprisonnier(false);
        joueur.setPositionJoueur(10);
    }

    /**
     * Deplace un joueur a la case position
     * donne 200 au joueur si il passe par la case depart
     *
     * @param joueur   joueur a deplacer
     * @param position nouvelle position du joueur
     */
    public static void deplacer(Joueur joueur, int position) {
        if (position < joueur.getPositionJoueur())
            payer(200, joueur);
        joueur.setPositionJoueur(position);
    }

    /**
     * Fait avancer un joueur d'un certains nombre de case
     * donne 200 au joueur si il passe par la case depart
     *
     * @param joueur        joueur a faire avancer
     * @param nbDeplacement nombre de cases
     */
    public static void avancerJoueur(Joueur joueur, int nbDeplacement) {
        if (nbDeplacement <= 0)
            throw new IllegalArgumentException("deplacement illegal");

        int nouvellePos;
        if (joueur.getPositionJoueur() + nbDeplacement > 39) {
            nouvellePos = joueur.getPositionJoueur() + nbDeplacement - 40;
            payer(200, joueur);
            //case depart
        } else {
            nouvellePos = joueur.getPositionJoueur() + nbDeplacement;
        }

        joueur.setPositionJoueur(nouvellePos);
    }

    /**
     * pour verifier si un joueur peut construire sur un terrain
     *
     * @param terrain terrain a verifier
     * @param plateau plateau du terrain
     * @return vrai si le proprietaire du terrain est capable de construire
     */
    public static boolean peutConstruire(int terrain, UIPlateau plateau) {

        if (!plateau.getCaseP(terrain).estAchetable())
            return false;
        //terrain non achetable

        TerrainAchetable ta = (TerrainAchetable) plateau.getCaseP(terrain);

        if (!ta.estConstructible())
            return false;
        //terrain non constructible

        if (!ta.aUnProprietaire())
            return false;
        //le terrain n'a pas de propriétaire


        TerrainConstructible tc = (TerrainConstructible) ta;
        String couleur = tc.getCouleur();
        Joueur propietaire = tc.getProprietaire();
        int nbMaison = tc.getNombreMaison();

        if(tc.getPrixAchatMaison() > propietaire.getCapitalJoueur())
            return false;
        //le proprietaire n'a pas l'argent

        for (Terrain n : plateau.getListeCasesP()) {
            if (n.estAchetable()) {
                TerrainAchetable na = (TerrainAchetable) n;
                if (na.estConstructible()) {
                    TerrainConstructible nc = (TerrainConstructible) na;
                    if (nc.getCouleur().equals(couleur)) {

                        if (!propietaire.equals(nc.getProprietaire()))
                            return false;
                        //le joueur ne possède pas tous les terrains de cette couleur
                        if (nc.getNombreMaison() < nbMaison)
                            return false;
                        //terrain non eligible a la construction
                        //le joueur n'a pas assez de construction sur les autres terrains de meme couleur
                    }
                }
            }
        }
        return true;
    }

    /**
     * Methode permettant d'ajouter une construction (maison ou hotel) sur un terrain
     *
     * @param terrain terrain a construire
     * @param plateau plateau du terrain
     */
    public static void construire(int terrain, UIPlateau plateau) {

        if (!Action.peutConstruire(terrain, plateau))
            throw new IllegalArgumentException(("terrain non eligible a la construction"));
        TerrainConstructible tc = (TerrainConstructible) plateau.getCaseP(terrain);
        Action.retirer(tc.getPrixAchatMaison(), tc.getProprietaire());
        tc.setNombreMaison(tc.getNombreMaison() + 1);
    }

    /**
     * @return une valeur aleatoire entre 1 et 6
     */
    public static int lancerDe() {
        return (int) Math.floor(Math.random() * (6) + 1);
    }

    /**
     * choisit aleatoire une carte Chance dans la liste de cartes chances
     *
     * @param joueur joueur qui pioche
     * @return renvoie la carte choisit aleatoirement dans la liste de cartes chances
     */
    public static Cartes piocherChance(Joueur joueur) {
        Random r = new Random();
        return joueur.getUIPlateau().getChance(r.nextInt(joueur.getUIPlateau().getNombreCarteChance() - 1));
    }

    /**
     * choisit aleatoire une carte Communaute dans la liste de cartes communautees
     *
     * @param joueur joueur qui pioche
     * @return renvoie la carte choisit aleatoirement dans la liste de cartes comunautees
     */
    public static Cartes piocherCommunaute(Joueur joueur) {
        Random r = new Random();
        return joueur.getUIPlateau().getCommunaute(r.nextInt(joueur.getUIPlateau().getNombreCarteCommunaute() - 1));
    }
}
