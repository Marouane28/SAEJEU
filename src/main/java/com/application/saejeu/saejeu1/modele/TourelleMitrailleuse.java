package com.application.saejeu.saejeu1.modele;

public class TourelleMitrailleuse extends Tourelle {

    public TourelleMitrailleuse(double x, double y, Environnement env) {
        super(x, y, 5*16, 50, 3, env);
    }

    public TourelleMitrailleuse() {
        // Initialisation sans paramètres
    }

    @Override
    public void attaquer() {
        if (cible != null && estEnPortee(cible)) {
            // Effectuer l'attaque sur l'ennemi ciblé
            cible.decrementerPv(getDegat());
            System.out.println("Tourelle mitrailleuse attaque l'ennemi !");
        } else {
            // L'ennemi n'est plus à portée ou aucun ennemi n'est ciblé
            System.out.println("Aucune cible valide pour la tourelle mitrailleuse !");
        }
    }

}