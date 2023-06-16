package com.application.saejeu.saejeu1.modele.JUnit;

import com.application.saejeu.saejeu1.modele.BFS;
import com.application.saejeu.saejeu1.modele.Environnement;
import com.application.saejeu.saejeu1.modele.Sommet;
import com.application.saejeu.saejeu1.modele.TileMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class BFSTest {
    private Environnement map;
    private Sommet source;
    private Sommet sommetA;
    private Sommet sommetB;
    private Sommet sommetC;
    private Sommet sommetD;

    @BeforeEach
    public void setUp() throws IOException {
        // Création d'une map (graphe) de test
        map = new Environnement(new TileMap(",", "testtilemap"));

        // Création des sommets
        source = new Sommet(0, 0, 0.0);
        sommetA = new Sommet(1, 0, 0.0);
        sommetB = new Sommet(2, 0, 0.0);
        sommetC = new Sommet(1, 1, 0.0);
        sommetD = new Sommet(2, 1, 0.0);

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


}
