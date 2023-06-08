package com.application.saejeu.saejeu1.modele;

import java.util.ArrayList;
import java.util.List;

public class TourelleGèle extends Tourelle {

    private static final int CYCLES = 10;
    private List<Acteur> ennemisGelés;


    public TourelleGèle(int x, int y, Environnement env) {
        super(x, y, 5*16, 60, 0, env,"tourelle-gele.png", 200);
        ennemisGelés = new ArrayList<>();

    }

    @Override
    public void attaquer() {
        if (cible != null && estEnPortee(cible) && !ennemisGelés.contains(cible)) {
            cible.geler(CYCLES);
            cible.decrementerPv(getDegat());
            ennemisGelés.add(cible);
            decrementerPv(5); // Décrémenter les points de vie de la tourelle

            System.out.println("Tourelle gèle attaque l'ennemi !");
            this.environnement.setPièces(this.environnement.getPièces() + 1);
            System.out.println("viens de gagner une pièce par attaque d'une tourelle gèle");
        } else {
            System.out.println("Aucune cible valide pour la tourelle gèle !");
        }
    }


}