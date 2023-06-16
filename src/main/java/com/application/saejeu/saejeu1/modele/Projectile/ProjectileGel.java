package com.application.saejeu.saejeu1.modele.Projectile;

import com.application.saejeu.saejeu1.modele.Environnement;
import com.application.saejeu.saejeu1.modele.Zombie.Acteur;

public class ProjectileGel extends Projectile {
    public ProjectileGel(double x, double y, Acteur ennemi , Environnement environnement) {
        super(x, y,ennemi,environnement,"flocon.png");
    }
}