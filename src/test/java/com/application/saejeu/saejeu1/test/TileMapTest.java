package com.application.saejeu.saejeu1.test;

import com.application.saejeu.saejeu1.modele.TileMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TileMapTest {

    private static TileMap tileMap;

    @BeforeAll
    public static void setUp() throws IOException {
        tileMap = new TileMap(",", "testtilemap1");
        // Initialise l'objet TileMap pour les tests. Spécifiez ici le séparateur de données et le nom du fichier.
    }

    @Test
    public void testGetTileSize() {
        int expectedSize = 16;
        // Taille attendue des tuiles

        Assertions.assertEquals(expectedSize, tileMap.getTileSize());
        // Vérifie si la taille des tuiles obtenue correspond à la taille attendue.
    }

    @Test
    public void testIsNotObstacle() {
        Assertions.assertFalse(this.tileMap.isNotObstacle(0, 0));
        // Vérifie si la tuile en position (0, 0) est considérée comme un obstacle et retourne false.

        Assertions.assertFalse(this.tileMap.isNotObstacle(0, 1));
        // Vérifie si la tuile en position (0, 1) est considérée comme un obstacle et retourne false.

        Assertions.assertFalse(this.tileMap.isNotObstacle(1, 1));
        // Vérifie si la tuile en position (1, 1) est considérée comme un obstacle et retourne false.

        Assertions.assertTrue(this.tileMap.isNotObstacle(2, 1));
        // Vérifie si la tuile en position (2, 1) n'est pas considérée comme un obstacle et retourne true.
    }

    @Test
    public void testRemplirLaMap() throws IOException {
        setUp();
        // Initialise à nouveau l'objet TileMap avant le test.

        int[][] expectedMap = { {1, 2, 3},
                {1, 2, 400} };
        // Map de jeu attendue avec des ID de tuiles.

        Assertions.assertEquals(expectedMap.length, this.tileMap.getY());
        // Vérifie si la hauteur de la map de jeu correspond à la hauteur attendue.

        Assertions.assertEquals(expectedMap[0].length, this.tileMap.getX());
        // Vérifie si la largeur de la map de jeu correspond à la largeur attendue.
    }
}





