package com.application.saejeu.saejeu1.modele;

import com.application.saejeu.saejeu1.modele.Zombie.Acteur;

public class ProjectileMitrailleuse extends Projectile {
    public ProjectileMitrailleuse(double x, double y, Acteur ennemi , Environnement environnement) {
        super(x, y,ennemi,environnement,"projectile.png");
    }
}