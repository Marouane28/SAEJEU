package com.application.saejeu.saejeu1.modele;

import java.util.ArrayList;
import java.util.List;

public class TourelleRepousse extends Tourelle {

    private List<Acteur> acteursRepousses;
    public TourelleRepousse(int x, int y, Environnement env) {
        super(x, y, 5*16, 60, 0, env,"tourelleRepousse.png",350);
        acteursRepousses = new ArrayList<>(); // Initialisation de la liste des acteurs repoussés
    }

    @Override
    public void attaquer() {
        if (cible != null && estEnPortee(cible) && !acteursRepousses.contains(cible)) {
            // Vérifier si une cible est définie, si elle est dans la portée de la tourelle et si elle n'a pas déjà été repoussée
            cible.decrementerPv(getDegat()); // Réduire les points de vie de la cible
            decrementerPv(5); // Réduire les points de vie de la tourelle
            System.out.println("Tourelle repousse attaque l'ennemi !");

            // Repousser l'ennemi en le faisant réapparaître
            cible.respawn();
            acteursRepousses.add(cible); // Ajouter la cible à la liste des acteurs repoussés
            this.environnement.setPièces(this.environnement.getPièces() + 2);
            System.out.println("viens de gagner deux pièce par attaque d'une tourelle repousse");

        } else {
            // Aucune cible valide pour la tourelle repousse
            System.out.println("Aucune cible valide pour la tourelle repousse !");
        }
    }
}