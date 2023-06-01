package com.application.saejeu.saejeu1.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public abstract class Acteur {

    private DoubleProperty x, y;
    private Environnement environnement;
    private int pv;
    private boolean gele;
    private int index;
    private int cyclesRestants;
    private String nomImage;


    public Acteur(int x, int y,Environnement env, int pv,String nomImage) {
        this.pv = pv;
        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);
        this.environnement = env;
        cyclesRestants = 0;
        this.nomImage=nomImage;
        this.index=0;
    }
    public String getNomImage() {
        return nomImage;
    }

    public void setIndex(int index) {
        this.index = index;
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

    public void deplacement(){
        if( index< this.environnement.getChemin().size()-1){
            index++;
            setX(environnement.getChemin().get(index).getX()*16);
            setY(environnement.getChemin().get(index).getY()*16);
        }
    }
    public int getCyclesRestants() {
        return cyclesRestants;
    }

    public void decrementerCyclesRestants() {
        if (cyclesRestants > 0) {
            cyclesRestants--;
        }
    }
    public void geler(int cycles) {
        cyclesRestants = cycles;

    }

    public void respawn() {
       setIndex(0);
    }



}

