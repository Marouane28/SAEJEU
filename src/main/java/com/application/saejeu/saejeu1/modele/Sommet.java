package com.application.saejeu.saejeu1.modele;

public class Sommet {
    private int x;
    private int y;
    private double poids;



    public Sommet(int x, int y, double poids){
        this.x=x;
        this.y=y;
        this.poids=poids;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }
    @Override
    public String toString() {
        return "Sommet{" +
                "x=" + x +
                ", y=" + y +
                ", poids=" + poids +
                '}';
    }

}
