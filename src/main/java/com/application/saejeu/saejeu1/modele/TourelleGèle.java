package com.application.saejeu.saejeu1.modele;

import java.util.ArrayList;
import java.util.List;

public class TourelleGèle extends Tourelle {

    private static final int CYCLES = 10;
    private List<Acteur> ennemisGelés;


    public TourelleGèle(int x, int y, Environnement env) {
        super(x, y, 5*16, 20, 0, env,"tourelle-gele.png");
        ennemisGelés = new ArrayList<>();

    }

    @Override
    public void attaquer() {
        if (cible != null && estEnPortee(cible) && !ennemisGelés.contains(cible)) {
            cible.geler(CYCLES);
            cible.decrementerPv(getDegat());
            ennemisGelés.add(cible);
            System.out.println("Tourelle gèle attaque l'ennemi !");
        } else {
            System.out.println("Aucune cible valide pour la tourelle gèle !");
        }
    }


}