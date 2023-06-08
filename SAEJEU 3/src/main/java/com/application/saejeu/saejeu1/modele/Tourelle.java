package com.application.saejeu.saejeu1.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Tourelle {
    private IntegerProperty x, y;

    private int portee;
    private int pv;
    private int degat;
    protected Environnement environnement;
    protected Acteur cible; // Référence à l'ennemi actuellement ciblé

    private int coût;
    private String nomImage;



    public Tourelle(int x, int y, int portée, int pv, int dégât, Environnement env, String nomImage, int coût) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.portee = portée;
        this.pv = pv;
        this.degat = dégât;
        this.environnement = env;
        this.nomImage=nomImage;
        this.coût = coût;
    }

    public int getCoût() {

        return this.coût;
    }
    public String getNomImage() {
        return nomImage;
    }

    public IntegerProperty xProperty() {
        return x;
    }

    public IntegerProperty yProperty() {
        return y;
    }

    public final double getX() {
        return this.xProperty().getValue();
    }

    public final void setX(double n) {
        this.xProperty().setValue(n);
    }

    public final double getY() {
        return this.yProperty().getValue();
    }

    public final void setY(double n) {
        this.yProperty().setValue(n);
    }

    public int getPortee() {
        return portee;
    }
    public int getPv() {
        return pv;
    }


    public int getDegat() {
        return degat;
    }

    public void setDegat(int degat) {
        this.degat = degat;
    }

    public void setCible(Acteur cible) {
        this.cible = cible;
    }
    public Acteur getCible() {
        return cible;
    }
    public abstract void attaquer();

    // Méthode pour vérifier si l'ennemi est à portée de la tourelle
    public boolean estEnPortee(Acteur ennemi) {
        double distance = Math.sqrt(Math.pow(ennemi.getX() - getX(), 2) + Math.pow(ennemi.getY() - getY(), 2));
        return distance <= portee;
    }

    public void decrementerPv(int valeur) {
        pv -= valeur;
    }
    public boolean estEnMarche() {
        return pv > 0;
    }

}