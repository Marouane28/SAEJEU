package com.application.saejeu.saejeu1.modele;
import com.application.saejeu.saejeu1.modele.Zombie.Acteur;
import com.application.saejeu.saejeu1.vue.VueProjectile;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public abstract class Projectile {
    public static final double VITESSE = 30;

    DoubleProperty x,y;

    private Environnement environnement;
    private Acteur ennemi;
    private VueProjectile vueProjectile;
    private String nomImage;



    public Projectile(double x, double y,Acteur ennemi ,Environnement environnement,String nomImage) {

        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);
        this.environnement = environnement;
        this.ennemi = ennemi;
        this.nomImage = nomImage;
    }

    public void lancerProjectile() {

        double distanceX = ennemi.getX() - this.getX();
        double distanceY = ennemi.getY() - this.getY();

        // Calcule la distance total
        double totalDistance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);


        double directionX = distanceX / totalDistance;
        double directionY = distanceY / totalDistance;

        // Calculer les nouvelles positions
        double nouvellePositionX = this.getX() + (VITESSE * directionX);
        double nouvellePositionY = this.getY() + (VITESSE * directionY);


        this.setX(nouvellePositionX);
        this.setY(nouvellePositionY);



        if (atteintActeur()){

            ennemi.decrementerPv(1);
            environnement.getProjectiles().remove(this);


        }

    }
    public void updatePosition(double elapsedTime) {

        double distance = Math.sqrt(Math.pow(this.ennemi.getX()+10 - this.x.getValue(), 2) + Math.pow(this.ennemi.getY()+10 - this.y.getValue(), 2));
        double xDirection = (this.ennemi.getX()+10 - this.x.getValue()) / distance;
        double yDirection = (this.ennemi.getY()+10 - this.y.getValue()) / distance;

        double deltaX = xDirection * VITESSE * elapsedTime;
        double deltaY = yDirection * VITESSE * elapsedTime;

        x.set(x.get() + deltaX);
        y.set(y.get() + deltaY);

    }


    public boolean atteintActeur() {
        // Les coins du projectile
        double x1 = getX();
        double y1 = getY();
        double x2 = getX() + 20;
        double y2 = getY() + 20;

        // Les coins de l'acteur
        double aX1 = ennemi.getX();
        double aY1 = ennemi.getY();
        double aX2 = ennemi.getX() + 16;
        double aY2 = ennemi.getY() + 16;

        // Vérifie si l'un des coins du projectile est à l'intérieur de la hitbox de l'acteur
        if ((x1 >= aX1 && x1 <= aX2 && y1 >= aY1 && y1 <= aY2) ||  // Coin supérieur gauche
                (x1 >= aX1 && x1 <= aX2 && y2 >= aY1 && y2 <= aY2) ||  // Coin inférieur gauche
                (x2 >= aX1 && x2 <= aX2 && y1 >= aY1 && y1 <= aY2) ||  // Coin supérieur droit
                (x2 >= aX1 && x2 <= aX2 && y2 >= aY1 && y2 <= aY2)) {  // Coin inférieur droit
            environnement.retirerProjectile(this);
            return true;
        }
        return false;
    }



    public double getX() {
        return x.get();
    }

    public DoubleProperty xProperty() {
        return x;
    }

    public void setX(double x) {
        this.x.set(x);
    }

    public double getY() {
        return y.get();
    }

    public DoubleProperty yProperty() {
        return y;
    }

    public void setY(double y) {
        this.y.set(y);
    }
    public void setVueProjectile(VueProjectile vueProjectile) {
        this.vueProjectile = vueProjectile;
    }

}