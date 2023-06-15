package com.application.saejeu.saejeu1.modele.Tourelle;

import com.application.saejeu.saejeu1.modele.Environnement;
import com.application.saejeu.saejeu1.modele.Projectile;
import com.application.saejeu.saejeu1.modele.ProjectileMitrailleuse;
import com.application.saejeu.saejeu1.modele.Zombie.Acteur;

import java.util.ArrayList;

public class TourelleMitrailleuse extends Tourelle {

    public TourelleMitrailleuse(int x, int y, Environnement env) {
        super(x, y, 100, 60, 5, env,"mitrailleuse.png", 400);
    }

    @Override
    public void attaquer() {
                creerProjectile();
            }

    @Override
    public Projectile creerProjectile() {
            Acteur a = ennemiPlusProche();
            if (a != null) {
                Projectile pro = new ProjectileMitrailleuse(this.getX() + 10, this.getY() - 30, a, environnement);
                environnement.ajouterProjectile(pro);
                return pro;
            }

        // La méthode creerProjectile() ne retourne rien, car vous ajoutez directement les projectiles à l'environnement
        return null;
    }
}