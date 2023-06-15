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
        super(x, y, 5 * 16, 60, 2, env, "tourelle-gele.png",200);
        ennemisGelés = new ArrayList<>(); // Initialisation de la liste des ennemis gelés
    }

    @Override
    public void attaquer() {
        ArrayList<Acteur> ennemisProches = ennemiPlusProche();
        for (Acteur ennemi : ennemisProches) {
            if (ennemi.estVivant()) {
                creerProjectile();
            }
            if (ennemi != null && estEnPortée(ennemi) && !ennemisGelés.contains(ennemi)) {
                // Vérifier si une cible est définie, si elle est dans la portée de la tourelle et si elle n'est pas déjà gelée
                ennemi.geler(CYCLES); // Geler l'ennemi pour le nombre de cycles spécifié
                ennemi.decrementerPv(getDégât()); // Réduire les points de vie de la cible
                ennemisGelés.add(ennemi); // Ajouter l'ennemi à la liste des ennemis gelés
                décrémenterPv(5); // Réduire les points de vie de la tourelle
                System.out.println("Tourelle gèle attaque l'ennemi !");
                this.environnement.setPièces(this.environnement.getPièces() + 40);
                System.out.println("viens de gagner une pièce par attaque d'une tourelle gèle");
            } else {
                // Aucune cible valide pour la tourelle gèle
                System.out.println("Aucune cible valide pour la tourelle gèle !");
            }
        }
    }

    @Override
    public Projectile creerProjectile() {
        System.out.println(ennemiPlusProche());
        for (int m = 0; m < ennemiPlusProche().size(); m++) {
            Projectile pro = new ProjectileGel(this.getX() + 10, this.getY() - 30, ennemiPlusProche().get(m), environnement);
            environnement.ajouterProjectile(pro);
        }
        // La méthode creerProjectile() ne retourne rien, car vous ajoutez directement les projectiles à l'environnement
        return null;
    }
}