package com.application.saejeu.saejeu1.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.application.saejeu.saejeu1.modele.BFS;
import com.application.saejeu.saejeu1.modele.Environnement;
import com.application.saejeu.saejeu1.modele.Sommet;
import com.application.saejeu.saejeu1.modele.TileMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BFSTest {
    private Environnement map;
    private Sommet source;
    private Sommet sommetA;
    private Sommet sommetB;
    private Sommet sommetC;
    private Sommet sommetD;

    @BeforeEach
    public void setUp() throws IOException {
        // Création d'une map (graphe) de test à partir du fichier testtilemap1
        map = new Environnement(new TileMap(",", "testtilemap1"));

        // Chargement des sommets et des poids à partir du fichier
        ArrayList<ArrayList<Double>> tileData = loadTileData("testtilemap1");

        // Création des sommets avec les poids spécifiés
        source = new Sommet(0, 0, tileData.get(0).get(0));
        sommetA = new Sommet(1, 0, tileData.get(0).get(1));
        sommetB = new Sommet(2, 0, tileData.get(0).get(2));
        sommetC = new Sommet(1, 1, tileData.get(1).get(0));
        sommetD = new Sommet(2, 1, tileData.get(1).get(1));

        // Ajout des arêtes (liaisons entre sommets)
        map.ajouterArete(source, sommetA);
        map.ajouterArete(sommetA, sommetB);
        map.ajouterArete(sommetB, sommetC);
        map.ajouterArete(sommetC, sommetD);
    }

    @Test
    public void testAlgoBFS() {
        BFS bfs = new BFS(map, source);
        Sommet cible = sommetD; // Définissez la variable cible avec un sommet approprié de votre graphe

        // Vérification du parcours obtenu
        ArrayList<Sommet> parcoursAttendu = bfs.cheminVersSource(cible);
        Assertions.assertEquals(parcoursAttendu, bfs.getParcours());

        // Vérification des prédécesseurs
        HashMap<Sommet, Sommet> predecesseursAttendus = new HashMap<>();
        for (Sommet sommet : parcoursAttendu) {
            predecesseursAttendus.put(sommet, bfs.getPredecesseur(sommet));
        }
        Assertions.assertEquals(predecesseursAttendus, bfs.getPredecesseurs());
    }

    @Test
    public void testCheminVersSource() {
        BFS bfs = new BFS(map, source);
        ArrayList<Sommet> cheminAttendu = new ArrayList<>(Arrays.asList(source, sommetA, sommetB, sommetC, sommetD));
        Assertions.assertEquals(cheminAttendu, bfs.cheminVersSource(sommetD));
    }

    private ArrayList<ArrayList<Double>> loadTileData(String filename) throws IOException {
        ArrayList<ArrayList<Double>> tileData = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] values = line.split(",");
            ArrayList<Double> row = new ArrayList<>();
            for (String value : values) {
                row.add(Double.parseDouble(value));
            }
            tileData.add(row);
        }
        reader.close();
        return tileData;
    }
}
