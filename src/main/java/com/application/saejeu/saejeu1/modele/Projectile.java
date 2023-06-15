package com.application.saejeu.saejeu1.modele;
import com.application.saejeu.saejeu1.modele.Zombie.Acteur;
import com.application.saejeu.saejeu1.vue.VueProjectile;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public abstract class Projectile {
    public static final double VITESSE = 100;

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
        // Calcul des distances entre le projectile et l'ennemi selon les axes X et Y
        double distanceX = ennemi.getX() - this.getX();
        double distanceY = ennemi.getY() - this.getY();

        // Calcul de la distance totale en utilisant le théorème de Pythagore
        double totalDistance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

        // Calcul des composantes de direction normalisées (valeurs entre -1 et 1)
        double directionX = distanceX / totalDistance;
        double directionY = distanceY / totalDistance;

        // Calcul des nouvelles positions en fonction de la vitesse du projectile et des directions
        double nouvellePositionX = this.getX() + (VITESSE * directionX);
        double nouvellePositionY = this.getY() + (VITESSE * directionY);

        // Mise à jour des coordonnées du projectile avec les nouvelles positions calculées
        this.setX(nouvellePositionX);
        this.setY(nouvellePositionY);
    }


    // Méthode qui est utlisé dans la méthode handle de la vueProjectile afin d'améliorer l'animation du projectile
    public void updatePosition(double elapsedTime) {
        // Calcul de la distance entre les coordonnées du projectile et de l'ennemi
        double distance = Math.sqrt(Math.pow(this.ennemi.getX()+10 - this.x.getValue(), 2) + Math.pow(this.ennemi.getY()+10 - this.y.getValue(), 2));

        // Calcul des composantes de direction normalisées (valeurs entre -1 et 1)
        double xDirection = (this.ennemi.getX()+10 - this.x.getValue()) / distance;
        double yDirection = (this.ennemi.getY()+10 - this.y.getValue()) / distance;

        // Calcul des déplacements en fonction de la vitesse du projectile, des directions et du temps écoulé
        double deltaX = xDirection * VITESSE * elapsedTime;
        double deltaY = yDirection * VITESSE * elapsedTime;

        // Mise à jour des coordonnées du projectile avec les déplacements calculés
        x.set(x.get() + deltaX);
        y.set(y.get() + deltaY);
    }



    public boolean atteintActeur() {
        // Coordonnées du projectile
        double xProjDroite = getX();
        double yProjDroite = getY();
        double xProjGauche = getX() + 20; // 20 = la taille de l'image en pixel
        double yProjGauche = getY() + 20;

        // Coordonnées de l'acteur (ennemi)
        double xActDroite = ennemi.getX();
        double yActDroite = ennemi.getY();
        double xActGauche = ennemi.getX() + 20; // 20 = la taille de l'image en pixel
        double yActGauche = ennemi.getY() + 20;

        // Vérifie si le projectile intersecte l'acteur
        if ((xProjDroite >= xActDroite && xProjDroite <= xActGauche && yProjDroite >= yActDroite && yProjDroite <= yActGauche) || // coin supérieur gauche du projectile
                (xProjDroite >= xActDroite && xProjDroite <= xActGauche && yProjGauche >= yActDroite && yProjGauche <= yActGauche) || // coin inférieur gauche du projectile
                (xProjGauche >= xActDroite && xProjGauche <= xActGauche && yProjDroite >= yActDroite && yProjDroite <= yActGauche) || //  coin supérieur droit du projectile
                (xProjGauche >= xActDroite && xProjGauche <= xActGauche && yProjGauche >= yActDroite && yProjGauche <= yActGauche)) { //  coin inférieur droit du projectile

            // Si l'intersection est vérifiée, retirer le projectile de l'environnement
            environnement.retirerProjectile(this);

            return true; // Le projectile a atteint l'acteur
        }

        return false; // Le projectile n'a pas atteint l'acteur
    }


    public String getNomImage() {
        return nomImage;
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