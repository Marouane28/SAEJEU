package com.application.saejeu.saejeu1.modele.Tourelle;

import com.application.saejeu.saejeu1.modele.Environnement;
import com.application.saejeu.saejeu1.modele.Projectile;
import com.application.saejeu.saejeu1.modele.ProjectileMitrailleuse;
import com.application.saejeu.saejeu1.modele.ProjectileRepousse;
import com.application.saejeu.saejeu1.modele.Zombie.Acteur;

import java.util.ArrayList;
import java.util.List;

public class TourelleRepousse extends Tourelle {

    private List<Acteur> acteursRepousses;

    public TourelleRepousse(int x, int y, Environnement env) {
        super(x, y, 80, 60, 2, env, "tourelleRepousse.png", 300);
        acteursRepousses = new ArrayList<>(); // Initialisation de la liste des acteurs repoussés
    }

    @Override
    public void attaquer() {
        // Vérifier si elle est dans la portée de la tourelle et si elle n'a pas déjà été repoussée
        if (ennemiPlusProche() != null && !acteursRepousses.contains(ennemiPlusProche())) {
                creerProjectile();
                ennemiPlusProche().decrementerPv(getDégât()); // Réduire les points de vie de la cible
                décrémenterPv(5); // Réduire les points de vie de la tourelle
                System.out.println("Tourelle repousse attaque l'ennemi !");

                // Repousser l'ennemi en le faisant réapparaître
                ennemiPlusProche().respawn();
                acteursRepousses.add(ennemiPlusProche()); // Ajouter la cible à la liste des acteurs repoussés
                this.environnement.setPièces(this.environnement.getPièces() + 50);
                System.out.println("viens de gagner deux pièce par attaque d'une tourelle repousse");

            } else {
                // Aucune cible valide pour la tourelle repousse
                System.out.println("Aucune cible valide pour la tourelle repousse !");
            }
        }


    @Override
    public Projectile creerProjectile() {
        // Recherche de l'ennemi le plus proche
        Acteur a = ennemiPlusProche();
        if (a != null) {
            // Création d'un nouveau projectile de type ProjectileRepousse avec les coordonnées de cet acteur,
            // l'ennemi cible et l'environnement
            Projectile pro = new ProjectileRepousse(this.getX(), this.getY(), a, environnement);
            // Ajout du projectile à l'environnement
            environnement.ajouterProjectile(pro);
            // Retourne le projectile créé
            return pro;
        }
        // Si aucun ennemi n'est trouvé, retourne null
        return null;
    }
}