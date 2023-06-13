package com.application.saejeu.saejeu1.modele.Tourelle;

import com.application.saejeu.saejeu1.modele.Environnement;

public class TourelleMitrailleuse extends Tourelle {

    public TourelleMitrailleuse(int x, int y, Environnement env) {
        super(x, y, 5*16, 60, 5, env,"mitrailleuse.png", 400);
    }



    @Override
    public void attaquer() {
        if (cible != null && estEnPortée(cible)) {
            // Vérifier si une cible est définie et si elle est dans la portée de la tourelle
            cible.decrementerPv(getDégât()); // Réduire les points de vie de la cible
            décrémenterPv(2); // Réduire les points de vie de la tourelle
            System.out.println("Tourelle mitrailleuse attaque l'ennemi !");

            this.environnement.setPièces(this.environnement.getPièces() + 10);
            System.out.println("viens de gagner cinq pièce par attaque d'une tourelle mitrailleuse");
        } else {
            // Aucune cible valide pour la tourelle mitrailleuse
            System.out.println("Aucune cible valide pour la tourelle mitrailleuse !");
        }
    }

}