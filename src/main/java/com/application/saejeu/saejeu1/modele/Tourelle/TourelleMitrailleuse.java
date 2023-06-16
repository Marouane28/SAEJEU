package com.application.saejeu.saejeu1.modele.Tourelle;

import com.application.saejeu.saejeu1.modele.Environnement;
import com.application.saejeu.saejeu1.modele.Projectile.Projectile;
import com.application.saejeu.saejeu1.modele.Projectile.ProjectileMitrailleuse;
import com.application.saejeu.saejeu1.modele.Zombie.Acteur;

public class TourelleMitrailleuse extends Tourelle {

    public TourelleMitrailleuse(int x, int y, Environnement env) {
        super(x, y, 80, 60, 5, env,"mitrailleuse.png", 400);
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
        // Recherche de l'ennemi le plus proche
        Acteur a = ennemiPlusProche();
        if (a != null) {
            // Création d'un nouveau projectile de type ProjectileMitrailleuse avec les coordonnées de cet acteur,
            // l'ennemi cible et l'environnement
            Projectile pro = new ProjectileMitrailleuse(this.getX(), this.getY(), a, environnement);
            // Ajout du projectile à l'environnement
            environnement.ajouterProjectile(pro);
            // Retourne le projectile créé
            return pro;
        }
        // Si aucun ennemi n'est trouvé, retourne null
        return null;
    }

}