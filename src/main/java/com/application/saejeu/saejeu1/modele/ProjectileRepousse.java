package com.application.saejeu.saejeu1.modele;

import com.application.saejeu.saejeu1.modele.Zombie.Acteur;

public class ProjectileRepousse extends Projectile {
    public ProjectileRepousse(double x, double y, Acteur ennemi , Environnement environnement) {
        super(x, y,ennemi,environnement,"vent.png");
    }
}