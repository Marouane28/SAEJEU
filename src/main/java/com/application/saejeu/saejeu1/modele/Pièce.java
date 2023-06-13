package com.application.saejeu.saejeu1.modele;


import com.application.saejeu.saejeu1.vue.VueEnnemi;
import com.application.saejeu.saejeu1.vue.VuePièce;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Random;

public class Pièce {
    private int valeur;
    private String urlImage;
    private int x, y;
    private VuePièce vuePièce;
    private Environnement environnement;
    public Pièce(int valeur, Environnement environnement) {

        this.valeur = valeur;
        this.urlImage = "image-pieces.png";
        this.environnement = environnement;
        this.coordonnéesXEtYAléatoireAvecGestionCollision();
    }
    public int getValeur() {

        return this.valeur;
    }
    public VuePièce getVuePièce() {

        return this.vuePièce;
    }
    public void coordonnéesXEtYAléatoireAvecGestionCollision() {

        Random r = new Random();
        int caseY;
        int caseX;
        do {
            int x = r.nextInt(this.environnement.getX());
            int y = r.nextInt(this.environnement.getY());

            int coordX = x - (x % this.environnement.getTileMap().getTileSize());
            int coordY = y - (y % this.environnement.getTileMap().getTileSize());
            // Condition pour ne pas la placer sur le chemin des zombies
            caseX = coordX / this.environnement.getTileMap().getTileSize();
            caseY = coordY / this.environnement.getTileMap().getTileSize();

            this.x = coordX; this.y = coordY;
        } while (this.environnement.getTileMap().isNotObstacle(caseX, caseY) || this.y <= 200 || this.environnement.emplacementDéjàPrisParUnePièce(this.x, this.y) || this.environnement.emplacementDéjàPrisParUneTourelle(this.x, this.y));
    }
    public String getUrlImage() {

        return this.urlImage;
    }
    public void setVuePièce(VuePièce vuePièce) {

        this.vuePièce = vuePièce; // Définit la vue associée à l'acteur
    }
    public int getX() {

        return this.x; // Renvoie la valeur de la propriété x de la pièce
    }
    public final double getY() {

        return this.y; // Renvoie la valeur de la propriété y de la pièce
    }

}
