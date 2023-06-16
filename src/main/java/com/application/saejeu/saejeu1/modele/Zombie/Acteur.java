package com.application.saejeu.saejeu1.modele.Zombie;

import com.application.saejeu.saejeu1.modele.Environnement;
import com.application.saejeu.saejeu1.vue.VueEnnemi;
import com.application.saejeu.saejeu1.vue.VueTourelle;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public abstract class Acteur {

    private DoubleProperty x, y; // Les coordonnées (x, y) de l'acteur
    private Environnement environnement; // L'environnement dans lequel l'acteur évolue
    private int pv; // Les points de vie de l'acteur
    private boolean gele; // Indique si l'acteur est gelé ou non
    private int index; // L'index utilisé pour le déplacement sur le chemin
    private int cyclesRestants; // Le nombre de cycles restants pour gérer le gèle de l'acteur
    private String nomImage; // Le nom de l'image associée à l'acteur
    private VueEnnemi vueEnnemi; // La vue associée à l'acteur

    public Acteur(int x, int y, Environnement env, int pv, String nomImage) {
        this.pv = pv; // Initialisation des points de vie de l'acteur
        this.x = new SimpleDoubleProperty(x); // Initialisation de la propriété x de l'acteur
        this.y = new SimpleDoubleProperty(y); // Initialisation de la propriété y de l'acteur
        this.environnement = env; // Initialisation de l'environnement dans lequel l'acteur évolue
        cyclesRestants = 0; // Initialisation du nombre de cycles restants
        this.nomImage = nomImage; // Initialisation du nom de l'image associée à l'acteur
        this.index = 0; // Initialisation de l'index utilisé pour le déplacement sur le chemin
    }

    public String getNomImage() {
        return nomImage; // Renvoie le nom de l'image associée à l'acteur
    }

    public void setIndex(int index) {
        this.index = index; // Définit l'index de l'acteur
    }

    public DoubleProperty xProperty() {

        return this.x; // Renvoie la propriété x de l'acteur
    }

    public DoubleProperty yProperty() {

        return this.y; // Renvoie la propriété y de l'acteur
    }

    public final double getX() {

        return this.xProperty().getValue(); // Renvoie la valeur de la propriété x de l'acteur
    }

    public final void setX(double n) {

        this.xProperty().setValue(n); // Définit la valeur de la propriété x de l'acteur
    }

    public final double getY() {

        return this.yProperty().getValue(); // Renvoie la valeur de la propriété y de l'acteur
    }

    public final void setY(double n) {

        this.yProperty().setValue(n); // Définit la valeur de la propriété y de l'acteur
    }

    public boolean estVivant() {
        return this.pv > 0; // Vérifie si l'acteur est vivant en comparant ses points de vie à 0
    }

    public void setVueEnnemi(VueEnnemi vueEnnemi) {
        this.vueEnnemi = vueEnnemi; // Définit la vue associée à l'acteur
    }

    public void decrementerPv(int valeur) {
        pv -= valeur; // Décrémente les points de vie de l'acteur d'une certaine valeur
        vueEnnemi.actualiserBarreEtat(); // Actualise la barre d'état de l'acteur dans la vue
    }

    public int getPv() {
        return pv; // Renvoie les points de vie de l'acteur
    }

    @Override
    public String toString() {
        return "x = " + x + ", y = " + y; // Renvoie une représentation en chaîne de caractères des coordonnées (x, y) de l'acteur
    }

    public void deplacement() {
        if (index < this.environnement.getChemin().size() - 1) {
            index++; // Incrémente l'index pour passer à l'étape suivante du chemin
            setX(environnement.getChemin().get(index).getX() * 16); // Définit la nouvelle position en x de l'acteur en fonction du chemin
            setY(environnement.getChemin().get(index).getY() * 16); // Définit la nouvelle position en y de l'acteur en fonction du chemin
        }
    }

    public int getCyclesRestants() {
        return cyclesRestants; // Renvoie le nombre de cycles restants pour un état particulier de l'acteur
    }

    public void decrementerCyclesRestants() {
        if (cyclesRestants > 0) {
            cyclesRestants--; // Décrémente le nombre de cycles restants
        }
    }

    public void geler(int cycles) {
        cyclesRestants = cycles; // Définit le nombre de cycles restants pour l'état gelé de l'acteur
    }
    public void respawn() {
        setIndex(0); // Réinitialise l'index de l'acteur pour le faire réapparaître
    }
}

