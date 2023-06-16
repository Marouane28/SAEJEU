package com.application.saejeu.saejeu1.modele;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.testng.Assert.*;

public class TileMapTest {

    private TileMap tileMap;

    @Before
    public void setUp() throws IOException {
        tileMap = new TileMap(",", "vraietilemap");
    }


    @Test
    public void testGetTileSize() {
        int expectedSize = 16;
        assertEquals(expectedSize, tileMap.getTileSize());
    }

    @Test
    public void testIsNotObstacle() {
        assertFalse(tileMap.isNotObstacle(0, 0));
        assertTrue(tileMap.isNotObstacle(0, 20));
    }

}




