package com.application.saejeu.saejeu1.modele;

public class TourelleMitrailleuse extends Tourelle {

    public TourelleMitrailleuse(int x, int y, Environnement env) {
        super(x, y, 5*16, 60, 5, env,"mitrailleuse.png", 500);
    }


    @Override
    public void attaquer() {
        if (cible != null && estEnPortee(cible)) {
            // Effectuer l'attaque sur l'ennemi ciblé
            cible.decrementerPv(getDegat());
            decrementerPv(2);
            System.out.println("Tourelle mitrailleuse attaque l'ennemi !");

            this.environnement.setPièces(this.environnement.getPièces() + 5);
            System.out.println("viens de gagner cinq pièce par attaque d'une tourelle mitrailleuse");
        } else {
            // L'ennemi n'est plus à portée ou aucun ennemi n'est ciblé
            System.out.println("Aucune cible valide pour la tourelle mitrailleuse !");
        }
    }

}