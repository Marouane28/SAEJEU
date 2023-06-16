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
    }


    @Test
    public void testGetTileSize() {
        int expectedSize = 16;
        Assertions.assertEquals(expectedSize, tileMap.getTileSize());

    }

    @Test
    public void testIsNotObstacle() {
        Assertions.assertFalse(this.tileMap.isNotObstacle(0, 0));
        Assertions.assertFalse(this.tileMap.isNotObstacle(0, 1));
        Assertions.assertFalse(this.tileMap.isNotObstacle(1, 1));
        Assertions.assertTrue(this.tileMap.isNotObstacle(2, 1));


    }
    @Test
    public void testRemplirLaMap() throws IOException {

        setUp();
        int[][] expectedMap = { {1, 2, 3},
                                {1, 2, 400} };

        Assertions.assertEquals(expectedMap.length, this.tileMap.getY());
        Assertions.assertEquals(expectedMap[0].length, this.tileMap.getX());
    }
}




