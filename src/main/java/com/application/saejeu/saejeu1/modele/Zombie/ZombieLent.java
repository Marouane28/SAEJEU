package com.application.saejeu.saejeu1.modele.Zombie;

import com.application.saejeu.saejeu1.modele.Environnement;

public class ZombieLent extends Acteur {
    private int deplacementCounter; // Ajoutez cette variable de compteur de déplacement

    public ZombieLent(Environnement env) {
        super(0, 304, env, 40,"zombieLent.png");
        deplacementCounter = 0; // Initialisez le compteur de déplacement à 0
    }

    @Override
    public void deplacement() {
        if (deplacementCounter % 2 == 0) { // Vérifiez si le compteur de déplacement est un multiple de 2
            // Effectuez le déplacement
            super.deplacement();
        }
        deplacementCounter++; // Incrémentez le compteur de déplacement à chaque appel de la méthode
    }

}
