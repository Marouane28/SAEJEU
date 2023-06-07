package com.application.saejeu.saejeu1.modele;

import com.application.saejeu.saejeu1.vue.VueTourelle;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Tourelle {
    private IntegerProperty x, y; // Coordonnées x et y de la tourelle

    private int portee; // Portée d'attaque de la tourelle
    private int pv; // Points de vie de la tourelle
    private int degat; // Dégâts infligés par la tourelle
    protected Environnement environnement; // Référence à l'environnement du jeu
    protected Acteur cible; // Référence à l'ennemi actuellement ciblé
    private String nomImage; // Nom du fichier d'image associé à la tourelle
    private VueTourelle vueTourelle; // Référence à la vue de la tourelle

    public Tourelle(int x, int y, int portee, int pv, int degat, Environnement env, String nomImage) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.portee = portee;
        this.pv = pv;
        this.degat = degat;
        this.environnement = env;
        this.nomImage = nomImage;
    }

    // Méthode pour obtenir le nom du fichier d'image associé à la tourelle
    public String getNomImage() {
        return nomImage;
    }

    // Méthodes pour accéder aux propriétés x et y de la tourelle
    public IntegerProperty xProperty() {
        return x;
    }

    public IntegerProperty yProperty() {
        return y;
    }

    // Méthodes pour obtenir et définir la valeur de la coordonnée x de la tourelle
    public final double getX() {
        return this.xProperty().getValue();
    }

    public final void setX(double n) {
        this.xProperty().setValue(n);
    }

    // Méthodes pour obtenir et définir la valeur de la coordonnée y de la tourelle
    public final double getY() {
        return this.yProperty().getValue();
    }

    public final void setY(double n) {
        this.yProperty().setValue(n);
    }

    // Méthode pour obtenir la portée d'attaque de la tourelle
    public int getPortee() {
        return portee;
    }

    // Méthode pour obtenir les points de vie de la tourelle
    public int getPv() {
        return pv;
    }

    // Méthode pour obtenir les dégâts infligés par la tourelle
    public int getDegat() {
        return degat;
    }

    // Méthode pour définir les dégâts infligés par la tourelle
    public void setDegat(int degat) {
        this.degat = degat;
    }

    // Méthode pour définir la cible de la tourelle
    public void setCible(Acteur cible) {
        this.cible = cible;
    }

    // Méthode pour obtenir la cible de la tourelle
    public Acteur getCible() {
        return cible;
    }

    // Méthode abstraite pour effectuer l'attaque de la tourelle (à implémenter dans les sous-classes)
    public abstract void attaquer();

    // Méthode pour vérifier si l'ennemi est à portée de la tourelle
    public boolean estEnPortee(Acteur ennemi) {
        double distance = Math.sqrt(Math.pow(ennemi.getX() - getX(), 2) + Math.pow(ennemi.getY() - getY(), 2));
        return distance <= portee;
    }

    // Méthode pour définir la vue de la tourelle
    public void setVueTourelle(VueTourelle vueTourelle) {
        this.vueTourelle = vueTourelle;
    }

    // Méthode pour décrémenter les points de vie de la tourelle et actualiser la barre d'état correspondante dans la vue
    public void decrementerPv(int valeur) {
        pv -= valeur;
        vueTourelle.actualiserBarreEtat();
    }

    // Méthode pour vérifier si la tourelle est en marche (a encore des points de vie)
    public boolean estEnMarche() {
        return pv > 0;
    }
}