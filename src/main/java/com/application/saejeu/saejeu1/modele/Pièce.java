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
    private IntegerProperty x, y;
    private VuePièce vuePièce;
    private Environnement environnement;
    public Pièce(int valeur, Environnement environnement) {

        this.valeur = valeur;
        this.urlImage = "image-pieces.png";
        this.environnement = environnement;
        this.x = new SimpleIntegerProperty();
        this.y = new SimpleIntegerProperty();
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
            int x = r.nextInt(1441);
            int y = r.nextInt(801);

            int coordX = x - (x % this.environnement.getTileMap().getTileSize());
            int coordY = y - (y % this.environnement.getTileMap().getTileSize());
            // Condition pour ne pas la placer sur le chemin des zombies
            caseX = coordX / this.environnement.getTileMap().getTileSize();
            caseY = coordY / this.environnement.getTileMap().getTileSize();

            this.x.setValue(coordX); this.y.setValue(coordY);
        } while (this.environnement.getTileMap().isNotObstacle(caseX, caseY) || this.getY() >= 650 || this.getY() <= 150 || this.environnement.emplacementDéjàPrisParUnePièce(this.getX(), this.getY()) || this.environnement.emplacementDéjàPrisParUneTourelle(this.getX(), this.getY()));
    }
    public String getUrlImage() {

        return this.urlImage;
    }
    public void setVuePièce(VuePièce vuePièce) {

        this.vuePièce = vuePièce; // Définit la vue associée à l'acteur
    }
    public final int getX() {

        return this.x.getValue(); // Renvoie la valeur de la propriété x de la pièce
    }
    public final int getY() {

        return this.y.getValue(); // Renvoie la valeur de la propriété y de la pièce
    }
    public void setX(int i) {

        this.x.setValue(i);
    }
    public void setY(int i) {

        this.y.setValue(i);
    }
    public IntegerProperty getXProperty() {

        return this.x;
    }
    public IntegerProperty getYProperty() {

        return this.y;
    }
}
