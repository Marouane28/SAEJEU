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

        iniTerrain();
    }

    void iniTerrain() {

        FileInputStream fichierTileSet = null;
        try {
            fichierTileSet = new FileInputStream(getClass().getResource("tileset1.jpg").getFile());
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.imgTilep = new Image(fichierTileSet);
        for (int i = 0; i < 90; i++) {
            for (int j = 0; j < 90; j++) {
                int id = this.terrain.getTileMap().getMapDeJeu()[j][i];
                ImageView imgV = new ImageView(this.imgTilep);
                ajouterTile(imgV, id);
            }
        }
    }


    public void ajouterTile(ImageView img, int id) {
        int x;
        int y;
        x = id % ((int) imgTilep.getWidth() / 16);
        y = id / ((int) imgTilep.getWidth() / 16);

        x = (x * 16);
        y = (y * 16);

        img.setViewport(new Rectangle2D(x, y, 16, 16));
        this.tilePane.getChildren().add(img);
    }
}
