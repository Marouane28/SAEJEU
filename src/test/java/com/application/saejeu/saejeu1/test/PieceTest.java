package com.application.saejeu.saejeu1.test;

import com.application.saejeu.saejeu1.modele.Environnement;
import com.application.saejeu.saejeu1.modele.Pièce;
import com.application.saejeu.saejeu1.modele.TileMap;
import com.application.saejeu.saejeu1.vue.VuePièce;
import javafx.scene.layout.Pane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class PieceTest {
    private Environnement environnement; // Déclaration de la variable environnement
    private TileMap tileMap; // Déclaration de la variable tileMap
    private Pièce piece, piece2; // Déclaration des variables piece et piece2
    private Pane panneauJeu; // Déclaration de la variable panneauJeu

    @BeforeEach
    void setUp() throws IOException {
        tileMap = new TileMap(",", "vraietilemap"); // Initialisation de la variable tileMap avec une instance de TileMap
        environnement = new Environnement(tileMap); // Initialisation de la variable environnement avec une instance de Environnement
        piece = new Pièce(2, environnement); // Initialisation de la variable piece avec une instance de Pièce, en passant la valeur 2 et la référence de environnement comme paramètres
        piece2 = new Pièce(2, environnement); // Initialisation de la variable piece2 avec une instance de Pièce, en passant la valeur 2 et la référence de environnement comme paramètres

        // Ajoutez les pièces à la liste listePièces de l'environnement
        environnement.ajouterPièce(piece2);
    }

    @Test
    public void testCoordonneesAleatoiresAvecGestionCollision() {
        // Générez des coordonnées aléatoires avec gestion de collision pour la première pièce
        piece.coordonnéesXEtYAléatoireAvecGestionCollision();

        // Obtenez les coordonnées générées pour la première pièce
        int x = piece.getX();
        int y = piece.getY();

        // Placez la deuxième pièce aux mêmes coordonnées que la première pièce
        piece2.setX(x);
        piece2.setY(y);

        // Vérifiez que les coordonnées sont valides (pas d'obstacle, plage correcte, pas déjà occupées)
        assertTrue(environnement.emplacementDéjàPrisParUnePièce(x, y));
        assertFalse(environnement.emplacementDéjàPrisParUneTourelle(x, y));
    }
}