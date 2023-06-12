package com.application.saejeu.saejeu1.modele;

public class Sommet {
    private int x; // Coordonnée x du sommet
    private int y; // Coordonnée y du sommet
    private double poids; // Poids associé au sommet

    public Sommet(int x, int y, double poids) {
        this.x = x; // Assignation de la coordonnée x
        this.y = y; // Assignation de la coordonnée y
        this.poids = poids; // Assignation du poids
    }

    public int getX() {
        return x; // Renvoie la coordonnée x du sommet
    }

    public int getY() {
        return y; // Renvoie la coordonnée y du sommet
    }

    public double getPoids() {
        return poids; // Renvoie le poids associé au sommet
    }

    public void setPoids(double poids) {
        this.poids = poids; // Modifie le poids associé au sommet
    }

    @Override
    public String toString() {
        return "Sommet{" +
                "x=" + x +
                ", y=" + y +
                ", poids=" + poids +
                '}'; // Renvoie une représentation textuelle du sommet sous forme de chaîne de caractères
    }
}