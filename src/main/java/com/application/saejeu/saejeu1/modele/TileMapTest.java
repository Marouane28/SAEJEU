package com.application.saejeu.saejeu1.modele;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.testng.Assert.*;

public class TileMapTest {

    private TileMap tileMap;

    @Before
    public void setUp() throws IOException {
        tileMap = new TileMap(",", "testtilemap");
    }


    @Test
    public void testGetTileSize() {
        int expectedSize = 16;
        assertEquals(expectedSize, tileMap.getTileSize());
    }

    @Test
    public void testIsNotObstacle() {
        assertFalse(this.tileMap.isNotObstacle(0, 0));
        assertFalse(this.tileMap.isNotObstacle(0, 1));
        assertFalse(this.tileMap.isNotObstacle(1, 1));
        assertTrue(this.tileMap.isNotObstacle(2, 1));


    }
    @Test
    public void testRemplirLaMap() throws IOException {

        setUp();
        int[][] expectedMap = { {1, 2, 3},
                                {1, 2, 400} };

        assertEquals(expectedMap.length, this.tileMap.getY());
        assertEquals(expectedMap[0].length, this.tileMap.getX());
    }
}




