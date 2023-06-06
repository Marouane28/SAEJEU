package com.application.saejeu.saejeu1.modele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TileMap {

    private File fichier;
    private BufferedReader lectureDuFichier;
    private int[][] mapDeJeu;
    private int x, y;
    private final int NON_OBSTACLE_TILE = 400;
    private final int SIZE_TILE = 16;
    private String séparateur;

    public TileMap(String séparateur, String nomFichier) throws IOException {

        this.séparateur = séparateur;
        this.fichier = new File(getClass().getResource(nomFichier).getFile());
        this.lectureDuFichier = new BufferedReader(new FileReader(this.fichier));

        int lignes = 0;
        int colonnes = 0;

        String ligne = this.lectureDuFichier.readLine();
        while (ligne != null) {

            String[] valeurs = ligne.split(this.séparateur);
            colonnes = Math.max(colonnes, valeurs.length);
            lignes++;
            ligne = this.lectureDuFichier.readLine();
        }
        this.x = colonnes;
        this.y = lignes;
        this.mapDeJeu = new int[x][y];

        this.remplirLaMap();
    }
    public void remplirLaMap() throws IOException {

        // Création d'un lecteur de fichier pour lire le contenu du fichier
        BufferedReader lecteur = new BufferedReader(new FileReader(fichier));


        String ligne;
        int i = 0;
        while ((ligne = lecteur.readLine()) != null) {
            // Séparation de la ligne en valeurs individuelles en utilisant le séparateur spécifié
            String[] valeurs = ligne.split(this.séparateur);

            for (int j = 0; j < this.getX(); j++) {
                // Conversion de chaque valeur en entier et assignation à la position correspondante dans le tableau mapDeJeu
                //this.mapDeJeu[j][i] = Integer.parseInt(valeurs[j]);
                //System.out.println("Valeur à la position [" + i + "][" + j + "]: " + this.mapDeJeu[j][i]);
                if (!valeurs[j].trim().isEmpty()) {
                    this.mapDeJeu[j][i] = Integer.parseInt(valeurs[j].trim());
                }
            }
            i++;
        }
        // Fermeture du lecteur de fichier une fois la lecture terminée
        lecteur.close();
    }
    public int[][] getMapDeJeu() {

        return this.mapDeJeu;
    }

    public void setMapDeJeu(int[][] tab) {

        this.mapDeJeu = tab;
    }
    public int getTileSize() {

        return this.SIZE_TILE;
    }

    public boolean isNotObstacle(int x, int y) {

        return this.getMapDeJeu()[x][y] == this.NON_OBSTACLE_TILE;
    }

    public int getX() {

        return this.x;
    }

    public int getY() {

        return this.y;
    }
    public int getNON_OBSTACLE_TILE() {

        return this.NON_OBSTACLE_TILE;
    }

}