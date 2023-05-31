package com.application.saejeu.saejeu1.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public abstract class Acteur {

    private DoubleProperty x, y;
    private DoubleProperty v; // vitesse de deplacement
    protected Environnement environnement;
    private int pv;
    private boolean gele;

    public Acteur(int x, int y, int v, Environnement env, int pv) {
        this.pv = pv;
        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);
        this.v = new SimpleDoubleProperty(v);
        this.environnement = env;
    }
    public DoubleProperty vitesseProperty() {
        return v;
    }

    public final double getVitesse(){
        return this.vitesseProperty().getValue();
    }

    public final void setVitesse(double n){
        this.vitesseProperty().setValue(n);
    }


    public DoubleProperty xProperty() {
        return x;
    }

    public DoubleProperty yProperty() {
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

    public boolean estVivant() {
        return this.pv > 0;
    }

    public void meurt() {
        this.pv = 0;
    }

    public void decrementerPv(int n) {
        this.pv -= n;
    }

    @Override
    public String toString() {
        return "x = " + x + ", y = " + y;
    }

    public boolean estGele() {
        return gele;
    }

    public void setGele(boolean gele) {
        this.gele = gele;
    }

}

