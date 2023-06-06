package com.application.saejeu.saejeu1.modele;

import com.application.saejeu.saejeu1.vue.VueTourelle;
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
    private String nomImage;
    private VueTourelle vueTourelle;



    public Tourelle(int x, int y, int portee, int pv, int degat, Environnement env,String nomImage) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.portee = portee;
        this.pv = pv;
        this.degat = degat;
        this.environnement = env;
        this.nomImage=nomImage;
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
    public void setVueTourelle(VueTourelle vueTourelle) {
        this.vueTourelle = vueTourelle;
    }

    public void decrementerPv(int valeur) {
        pv -= valeur;
        vueTourelle.actualiserBarreEtat();
    }
    public boolean estEnMarche() {
        return pv > 0;
    }

}