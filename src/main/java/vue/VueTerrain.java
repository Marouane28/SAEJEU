package vue;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import modele.Environnement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class VueTerrain {

    private Image imgTilep;
    private ImageView imgV;

    private Environnement terrain;

    private TilePane tilePane;

    public VueTerrain(Environnement t, TilePane tileP) throws FileNotFoundException {
        this.terrain = t;
        this.tilePane = tileP;
        iniTerrain();
    }


    void iniTerrain() {

        FileInputStream fichierTileSet = null;
        try {
            fichierTileSet = new FileInputStream("/home/etudiants/info/mirhbira/prive/saeJeu1/src/main/resources/tileset1.jpg" );
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.imgTilep = new Image(fichierTileSet);
        for (int i = 0; i < terrain.getTileMap().length; i++) {
            for (int j = 0; j < terrain.getTileMap()[i].length; j++) {
                imgV = new ImageView(this.imgTilep);
                ajouterTile(imgV, this.terrain.getTileMap()[j][i]);

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
