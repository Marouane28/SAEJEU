package com.application.saejeu.saejeu1.test;

import com.application.saejeu.saejeu1.modele.Environnement;
import com.application.saejeu.saejeu1.modele.TileMap;
import com.application.saejeu.saejeu1.vue.VueTerrain;

import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import java.io.FileNotFoundException;
import java.io.IOException;


public class VueTerrainTest {
    private Environnement environnement;
    private TilePane tilePane;
    private String nomFichier;


    @Before
    public void setUp() throws IOException {
        environnement = new Environnement(new TileMap(",", "vraietilemap"));
        // Initialise l'objet Environnement pour les tests. Spécifiez ici les valeurs nécessaires.

        tilePane = new TilePane();
        // Initialise l'objet TilePane pour les tests. Spécifiez ici les valeurs nécessaires.

        nomFichier = "tileset1.jpg";
        // Spécifie le nom d'un fichier valide pour les tests.
    }

    @Test
    public void testIniTerrain() {
        try {
            VueTerrain vueTerrain = new VueTerrain(environnement, tilePane, nomFichier);
            // Crée une instance de VueTerrain avec les paramètres spécifiés.

            assertNotNull(vueTerrain.getImgTilep());
            // Vérifie si l'image du tileset a été chargée avec succès en s'assurant qu'elle n'est pas nulle.

        } catch (FileNotFoundException e) {
            fail("Le fichier du tileset n'a pas été trouvé.");
            // Échoue le test si le fichier du tileset n'a pas été trouvé.
        }
    }

    @Test
    public void testAjouterTile() throws FileNotFoundException {
        VueTerrain vueTerrain = new VueTerrain(environnement, tilePane, nomFichier);
        // Crée une instance de VueTerrain avec les paramètres spécifiés.

        int tileSize = this.environnement.getTileMap().getTileSize();
        int terrainWidth = this.environnement.getX();
        int terrainHeight = this.environnement.getY();
        // Obtient les dimensions du terrain et la taille des tuiles.

        for (int i = 0; i < terrainHeight / tileSize; i++) {
            for (int j = 0; j < terrainWidth / tileSize; j++) {
                ImageView imgV = new ImageView(vueTerrain.getImgTilep());
                // Crée une nouvelle ImageView avec l'image du tileset.

                int id = this.environnement.getTileMap().getMapDeJeu()[j][i];
                // Obtient l'ID de la tuile à ajouter.

                vueTerrain.ajouterTile(imgV, id);
                // Ajoute la tuile à l'ImageView.

                assertTrue(tilePane.getChildren().contains(imgV));
                // Vérifie si l'ImageView contenant la tuile a été ajoutée au TilePane.
            }
        }
    }
}
