package com.application.saejeu.saejeu1.modele.Zombie;

import com.application.saejeu.saejeu1.modele.Environnement;

public class ZombieGeant extends Acteur {
    private int deplacementCounter; // Ajoutez cette variable de compteur de déplacement

    public ZombieGeant(Environnement env) {
        super(0, 304, env, 50,"zombieGeant.png");
        deplacementCounter = 0; // Initialisez le compteur de déplacement à 0

    }

    @Override
    public void deplacement() {
        if (deplacementCounter % 3 == 0) { // Vérifiez si le compteur de déplacement est un multiple de trois
            // Effectuez le déplacement
            super.deplacement();
        }
        deplacementCounter++; // Incrémentez le compteur de déplacement à chaque appel de la méthode
    }

}
