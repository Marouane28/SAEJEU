package com.application.saejeu.saejeu1.vue;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import com.application.saejeu.saejeu1.modele.Environnement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class VueTerrain {

    private Image imgTilep; // Image du tileset
    private Environnement terrain; // Environnement du terrain
    private TilePane tilePane; // Conteneur pour les tuiles
    private String nomFichier; // Nom du fichier du tileset

    public VueTerrain(Environnement environnement, TilePane tileP, String nomFichier) throws FileNotFoundException {
        this.terrain = environnement; // Assigner l'environnement passé en paramètre
        this.tilePane = tileP; // Assigner le TilePane passé en paramètre
        this.nomFichier = nomFichier; // Assigner le nom du fichier du tileset passé en paramètre

        iniTerrain(); // Initialisation du terrain
    }

    public void iniTerrain() {
        FileInputStream fichierTileSet = null; // Initialiser le flux de fichier
        try {
            fichierTileSet = new FileInputStream(getClass().getResource(this.nomFichier).getFile()); // Ouvrir le fichier du tileset
        } catch (Exception e) {
            e.printStackTrace(); // Afficher la trace d'erreur s'il y a un problème avec le fichier
        }
        this.imgTilep = new Image(fichierTileSet); // Chargement de l'image du tileset

        for (int i = 0; i < this.terrain.getY() / this.terrain.getTileMap().getTileSize(); i++) { // parcours des lignes
            for (int j = 0; j < this.terrain.getX() / this.terrain.getTileMap().getTileSize(); j++) { // parcours des colonnes
                int id = this.terrain.getTileMap().getMapDeJeu()[j][i]; // Récupération de l'ID de la tuile à afficher
                ImageView imgV = new ImageView(this.imgTilep); // Création d'une nouvelle ImageView pour la tuile
                ajouterTile(imgV, id); // Ajout de la tuile à l'ImageView
            }
        }
    }

    public void ajouterTile(ImageView img, int id) {
        int x;
        int y;
        x = id % ((int) imgTilep.getWidth() / this.terrain.getTileMap().getTileSize()); // Calcul de la position X de la tuile dans le tileset
        y = id / ((int) imgTilep.getWidth() / this.terrain.getTileMap().getTileSize()); // Calcul de la position Y de la tuile dans le tileset

        x = (x * this.terrain.getTileMap().getTileSize()); // Positionnement X de la tuile
        y = (y * this.terrain.getTileMap().getTileSize()); // Positionnement Y de la tuile

        img.setViewport(new Rectangle2D(x, y, this.terrain.getTileMap().getTileSize(), this.terrain.getTileMap().getTileSize())); // Définition de la zone de découpage pour afficher la tuile correcte
        this.tilePane.getChildren().add(img); // Ajout de l'ImageView contenant la tuile au TilePane
    }

    public Image getImgTilep() {
        return imgTilep;
    }

}