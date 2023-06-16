package com.application.saejeu.saejeu1.modele.Tourelle;

import com.application.saejeu.saejeu1.modele.Environnement;
import com.application.saejeu.saejeu1.modele.Projectile;
import com.application.saejeu.saejeu1.modele.ProjectileGel;
import com.application.saejeu.saejeu1.modele.ProjectileMitrailleuse;
import com.application.saejeu.saejeu1.modele.Zombie.Acteur;

import java.util.ArrayList;
import java.util.List;

public class TourelleGèle extends Tourelle {

    private static final int CYCLES = 10; // Nombre de cycles pendant lesquels l'ennemi est gelé
    private List<Acteur> ennemisGelés; // Liste des ennemis déjà gelés

    public TourelleGèle(int x, int y, Environnement env) {
        super(x, y, 80, 60, 2, env, "tourelle-gele.png", 200);
        ennemisGelés = new ArrayList<>(); // Initialisation de la liste des ennemis gelés
    }

    @Override
    public void attaquer() {
        // Vérifier si elle est dans la portée de la tourelle et si elle n'est pas déjà gelée
        if (ennemiPlusProche() != null && !ennemisGelés.contains(ennemiPlusProche())) {
                creerProjectile();
                ennemiPlusProche().geler(CYCLES); // Geler l'ennemi pour le nombre de cycles spécifié
                ennemiPlusProche().decrementerPv(getDégât()); // Réduire les points de vie de la cible
                ennemisGelés.add(ennemiPlusProche()); // Ajouter l'ennemi à la liste des ennemis gelés
                décrémenterPv(5); // Réduire les points de vie de la tourelle
                System.out.println("Tourelle gèle attaque l'ennemi !");
                this.environnement.setPièces(this.environnement.getPièces() + 40);
                System.out.println("viens de gagner une pièce par attaque d'une tourelle gèle");
            } else {
                // Aucune cible valide pour la tourelle gèle
                System.out.println("Aucune cible valide pour la tourelle gèle !");
            }
        }

    @Override
    public Projectile creerProjectile() {
        // Recherche de l'ennemi le plus proche
        Acteur a = ennemiPlusProche();
        if (a != null) {
            // Création d'un nouveau projectile de type ProjectileGel avec les coordonnées de cet acteur,
            // l'ennemi cible et l'environnement
            Projectile pro = new ProjectileGel(this.getX(), this.getY(), a, environnement);
            // Ajout du projectile à l'environnement
            environnement.ajouterProjectile(pro);
            // Retourne le projectile créé
            return pro;
        }
        // Si aucun ennemi n'est trouvé, retourne null
        return null;
    }
}