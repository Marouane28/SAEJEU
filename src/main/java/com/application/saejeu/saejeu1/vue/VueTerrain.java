package com.application.saejeu.saejeu1.vue;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import com.application.saejeu.saejeu1.modele.Environnement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class VueTerrain {

    private Image imgTilep;
    private ImageView imgV;
    private Environnement terrain;
    private TilePane tilePane;
    private String nomFichier;

    public VueTerrain(Environnement environnement, TilePane tileP, String nomFichier) throws FileNotFoundException {
        this.terrain = environnement;
        this.tilePane = tileP;
        this.nomFichier = nomFichier;

        iniTerrain(); // Initialisation du terrain
    }

    void iniTerrain() {
        FileInputStream fichierTileSet = null;
        try {
            fichierTileSet = new FileInputStream(getClass().getResource("tileset1.jpg").getFile());
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.imgTilep = new Image(fichierTileSet); // Chargement de l'image du tileset

        for (int i = 0; i < 90; i++) {
            for (int j = 0; j < 90; j++) {
                int id = this.terrain.getTileMap().getMapDeJeu()[j][i]; // Récupération de l'ID de la tuile à afficher
                ImageView imgV = new ImageView(this.imgTilep); // Création d'une nouvelle ImageView pour la tuile
                ajouterTile(imgV, id); // Ajout de la tuile à l'ImageView
            }
        }
    }

    public void ajouterTile(ImageView img, int id) {
        int x;
        int y;
        x = id % ((int) imgTilep.getWidth() / 16); // Calcul de la position X de la tuile dans le tileset
        y = id / ((int) imgTilep.getWidth() / 16); // Calcul de la position Y de la tuile dans le tileset

        x = (x * 16); // Positionnement X de la tuile
        y = (y * 16); // Positionnement Y de la tuile

        img.setViewport(new Rectangle2D(x, y, 16, 16)); // Définition de la zone de découpage pour afficher la tuile correcte
        this.tilePane.getChildren().add(img); // Ajout de l'ImageView contenant la tuile au TilePane
    }
}