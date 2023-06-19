package com.application.saejeu.saejeu1.modele;


import com.application.saejeu.saejeu1.vue.VueEnnemi;
import com.application.saejeu.saejeu1.vue.VuePièce;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Random;

public class Pièce {
    private int valeur; // Valeur de la pièce
    private String urlImage; // URL de l'image de la pièce
    private IntegerProperty x, y; // Propriétés de coordonnées x et y de la pièce
    private VuePièce vuePièce; // Vue associée à la pièce
    private Environnement environnement; // Environnement auquel la pièce appartient

    public Pièce(int valeur, Environnement environnement) {
        this.valeur = valeur; // Définit la valeur de la pièce
        this.urlImage = "image-pieces.png"; // Définit l'URL de l'image de la pièce
        this.environnement = environnement; // Définit l'environnement auquel la pièce appartient
        this.x = new SimpleIntegerProperty(); // Initialise la propriété x de la pièce
        this.y = new SimpleIntegerProperty(); // Initialise la propriété y de la pièce
        this.coordonnéesXEtYAléatoireAvecGestionCollision(); // Génère des coordonnées aléatoires avec gestion de collision
    }

    public int getValeur() {
        return this.valeur; // Renvoie la valeur de la pièce
    }

    public VuePièce getVuePièce() {
        return this.vuePièce; // Renvoie la vue associée à la pièce
    }

    public void coordonnéesXEtYAléatoireAvecGestionCollision() {
        Random r = new Random(); // Initialise un générateur de nombres aléatoires
        int caseY;
        int caseX;
        do {
            int x = r.nextInt(1440); // Génère une valeur aléatoire pour la coordonnée x
            int y = r.nextInt(800); // Génère une valeur aléatoire pour la coordonnée y

            int coordX = x - (x % this.environnement.getTileMap().getTileSize()); // Calcule la coordonnée x avec gestion de la taille des cases
            int coordY = y - (y % this.environnement.getTileMap().getTileSize()); // Calcule la coordonnée y avec gestion de la taille des cases
            // Condition pour ne pas la placer sur le chemin des zombies
            caseX = coordX / this.environnement.getTileMap().getTileSize(); // Calcule la case x correspondante
            caseY = coordY / this.environnement.getTileMap().getTileSize(); // Calcule la case y correspondante

            this.x.setValue(coordX); // Définit la valeur de la propriété x
            this.y.setValue(coordY); // Définit la valeur de la propriété y
        } while (this.environnement.getTileMap().isNotObstacle(caseX, caseY) || this.getY() >= 650 || this.getY() <= 150 || this.environnement.emplacementDéjàPrisParUnePièce(this.getX(), this.getY()) || this.environnement.emplacementDéjàPrisParUneTourelle(this.getX(), this.getY()));
    }

    public void mouvementDePièce(Pièce pièce) {
        int tileSize = this.environnement.getTileMap().getTileSize();
        Random r = new Random();

        int deltaX = 0;
        int deltaY = 0;

        // Générer une direction aléatoire
        int randomNumber = r.nextInt(4);
        switch (randomNumber) {
            case 0:
                deltaX = tileSize; // Déplacement vers la droite
                break;
            case 1:
                deltaX = -tileSize; // Déplacement vers la gauche
                break;
            case 2:
                deltaY = tileSize; // Déplacement vers le bas
                break;
            case 3:
                deltaY = -tileSize; // Déplacement vers le haut
                break;
        }

        int prochainX = pièce.getX() + deltaX;
        int prochainY = pièce.getY() + deltaY;

        int nextCaseX = prochainX / tileSize;
        int nextCaseY = prochainY / tileSize;

        if (!this.environnement.emplacementDéjàPrisParUnePièce(prochainX, prochainY) && !this.environnement.emplacementDéjàPrisParUneTourelle(prochainX, prochainY) && this.environnement.dansGrille(prochainX, prochainY) && !this.environnement.getTileMap().isNotObstacle(nextCaseX, nextCaseY) && !(prochainY >= 800)) {
            pièce.getVuePièce().retirerImagePièce(pièce);
            pièce.setX(prochainX);
            pièce.setY(prochainY);
            pièce.getVuePièce().imagePièce();

            // Mettre à jour l'écouteur d'événements
            pièce.getVuePièce().updateMouseClickedListener();
        }
    }

    public String getUrlImage() {
        return this.urlImage; // Renvoie l'URL de l'image de la pièce
    }

    public void setVuePièce(VuePièce vuePièce) {
        this.vuePièce = vuePièce; // Définit la vue associée à l'acteur
    }

    public Environnement getEnvironnement() {
        return this.environnement; // Renvoie l'environnement auquel la pièce appartient
    }

    public final int getX() {
        return this.x.getValue(); // Renvoie la valeur de la propriété x de la pièce
    }

    public final int getY() {
        return this.y.getValue(); // Renvoie la valeur de la propriété y de la pièce
    }

    public void setX(int i) {
        this.x.setValue(i); // Définit la valeur de la propriété x
    }

    public void setY(int i) {
        this.y.setValue(i); // Définit la valeur de la propriété y
    }

    public IntegerProperty getXProperty() {
        return this.x; // Renvoie la propriété x de la pièce
    }

    public IntegerProperty getYProperty() {
        return this.y; // Renvoie la propriété y de la pièce
    }
}


