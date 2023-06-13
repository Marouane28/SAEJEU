package com.application.saejeu.saejeu1.modele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TileMap {


    private File fichier; // Fichier contenant la carte du jeu
    private BufferedReader lectureDuFichier; // Lecteur pour lire le contenu du fichier
    private int[][] mapDeJeu; // Tableau représentant la carte du jeu
    private int x, y; // Dimensions de la carte
    private final int NON_OBSTACLE_TILE = 400; // Valeur représentant une tuile non obstruée
    private final int SIZE_TILE = 16; // Taille d'une tuile en pixels
    private String séparateur; // Séparateur utilisé pour lire le fichier

    public TileMap(String séparateur, String nomFichier) throws IOException {
        this.séparateur = séparateur;
        this.fichier = new File(getClass().getResource(nomFichier).getFile()); // Création d'un objet File à partir du nom du fichier
        this.lectureDuFichier = new BufferedReader(new FileReader(this.fichier)); // Création d'un lecteur de fichier pour lire le contenu

        int lignes = 0; // Compteur de lignes
        int colonnes = 0; // Compteur de colonnes

        String ligne = this.lectureDuFichier.readLine(); // Lecture de la première ligne du fichier
        while (ligne != null) { // Tant qu'il y a des lignes à lire
            String[] valeurs = ligne.split(this.séparateur); // Séparation de la ligne en valeurs individuelles en utilisant le séparateur
            colonnes = Math.max(colonnes, valeurs.length); // Mise à jour du nombre de colonnes maximum
            lignes++; // Incrémentation du nombre de lignes
            ligne = this.lectureDuFichier.readLine(); // Lecture de la ligne suivante
        }
        this.x = colonnes; // Assignation du nombre de colonnes
        this.y = lignes; // Assignation du nombre de lignes
        this.mapDeJeu = new int[x][y]; // Création du tableau représentant la carte du jeu avec les dimensions calculées

        this.remplirLaMap(); // Remplissage du tableau avec les valeurs du fichier
    }

    public void remplirLaMap() throws IOException {
        BufferedReader lecteur = new BufferedReader(new FileReader(fichier)); // Création d'un nouveau lecteur de fichier

        String ligne; // Variable pour stocker chaque ligne lue
        int i = 0; // Indice de ligne
        while ((ligne = lecteur.readLine()) != null) { // Tant qu'il y a des lignes à lire
            String[] valeurs = ligne.split(this.séparateur); // Séparation de la ligne en valeurs individuelles en utilisant le séparateur

            for (int j = 0; j < this.getX(); j++) { // Parcours des valeurs de chaque ligne
                if (!valeurs[j].trim().isEmpty()) { // Vérification si la valeur n'est pas vide
                    this.mapDeJeu[j][i] = Integer.parseInt(valeurs[j].trim()); // Conversion de la valeur en entier et assignation dans le tableau mapDeJeu
                }
            }
            i++; // Passage à la ligne suivante
        }
        lecteur.close(); // Fermeture du lecteur de fichier une fois la lecture terminée
    }

    public int[][] getMapDeJeu() {
        return this.mapDeJeu; // Renvoie le tableau représentant la carte du jeu
    }

    public int getTileSize() {
        return this.SIZE_TILE; // Renvoie la taille d'une tuile en pixels
    }

    public boolean isNotObstacle(int x, int y) {
        return this.getMapDeJeu()[x][y] == this.NON_OBSTACLE_TILE; // Vérifie si la tuile à la position (x, y) n'est pas un obstacle
    }

    public int getX() {
        return this.x; // Renvoie le nombre de colonnes de la carte
    }

    public int getY() {
        return this.y; // Renvoie le nombre de lignes de la carte
    }

}