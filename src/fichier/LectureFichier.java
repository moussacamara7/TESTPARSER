package fichier;

import parser.Parser;
import exception.*;
import plateau.Plateau;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LectureFichier {
    /**
     * Cette méthode ouvre le fichier (en faisant tous les contrôles nécessaires). Puis, elle boucle sur
     * chaque ligne et confie au parser le traitement de la ligne. S'il n'y a pas de parser,
     * la ligne est tout simplement affichée dans la console
     * @param nomFichier nom du fichier à lire et à parser
     * @param parser c'est le premier parser de la liste
     * @param plateau plateau du jeu
     */
    public static void lire(String nomFichier, Parser parser, Plateau plateau) {
        if (nomFichier == null)
            throw new IllegalArgumentException("Fichier Carte Illegal !!!");

        File fichier = new File(nomFichier);

        if (! fichier.isFile())
            throw new IllegalArgumentException("Fichier Carte Inexistant!!!");

        BufferedReader reader;
        String ligne;

        try {
            reader = new BufferedReader(new FileReader(fichier));

            while ((ligne = reader.readLine()) != null) {
                if (parser==null)
                    System.out.println("Ligne : "+ligne);
                else
                    try {
                        parser.traiter(ligne.split(";"), plateau);
                    }
                    catch (ParserManquantException e) {
                        System.err.println("Aucun parser n'existe pour la ligne "+ligne);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
