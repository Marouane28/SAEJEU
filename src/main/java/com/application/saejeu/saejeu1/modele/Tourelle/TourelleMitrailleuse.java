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
        // Vérifier si une cible est définie et si elle est dans la portée de la tourelle
        if (ennemiPlusProche() != null) {
            creerProjectile();
            ennemiPlusProche().decrementerPv(getDégât()); // Réduire les points de vie de la cible
            décrémenterPv(2); // Réduire les points de vie de la tourelle
            System.out.println("Tourelle mitrailleuse attaque l'ennemi !");
            this.environnement.setPièces(this.environnement.getPièces() + 10);
            System.out.println("viens de gagner cinq pièce par attaque d'une tourelle mitrailleuse");
        } else {
            // Aucune cible valide pour la tourelle mitrailleuse
            System.out.println("Aucune cible valide pour la tourelle mitrailleuse !");
        }
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