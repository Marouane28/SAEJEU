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
        ArrayList<Acteur> ennemisProches = ennemiPlusProche();
        for (Acteur ennemi : ennemisProches) {
            if (ennemi.estVivant()) {
                creerProjectile();
            }
        }
    }

    @Override
    public Projectile creerProjectile() {
        System.out.println(ennemiPlusProche());
        for (int m = 0; m < ennemiPlusProche().size(); m++) {
            Projectile pro = new ProjectileMitrailleuse(this.getX() + 10, this.getY() - 30, ennemiPlusProche().get(m), environnement);
            environnement.ajouterProjectile(pro);
        }
        // La méthode creerProjectile() ne retourne rien, car vous ajoutez directement les projectiles à l'environnement
        return null;
    }
}