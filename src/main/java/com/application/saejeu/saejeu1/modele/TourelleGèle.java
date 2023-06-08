package com.application.saejeu.saejeu1.modele;

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
        if (cible != null && estEnPortee(cible) && !ennemisGelés.contains(cible)) {
            // Vérifier si une cible est définie, si elle est dans la portée de la tourelle et si elle n'est pas déjà gelée
            cible.geler(CYCLES); // Geler l'ennemi pour le nombre de cycles spécifié
            cible.decrementerPv(getDegat()); // Réduire les points de vie de la cible
            ennemisGelés.add(cible); // Ajouter l'ennemi à la liste des ennemis gelés
            decrementerPv(5); // Réduire les points de vie de la tourelle
            System.out.println("Tourelle gèle attaque l'ennemi !");
            this.environnement.setPièces(this.environnement.getPièces() + 40);
            System.out.println("viens de gagner une pièce par attaque d'une tourelle gèle");
        } else {
            // Aucune cible valide pour la tourelle gèle
            System.out.println("Aucune cible valide pour la tourelle gèle !");
        }
    }


}