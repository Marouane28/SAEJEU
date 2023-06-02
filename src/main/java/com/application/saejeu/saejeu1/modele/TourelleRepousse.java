package com.application.saejeu.saejeu1.modele;

import java.util.ArrayList;
import java.util.List;

public class TourelleRepousse extends Tourelle {

    private List<Acteur> acteursRepousses;
    public TourelleRepousse(int x, int y, Environnement env) {
        super(x, y, 5*16, 60, 0, env,"tourelleRepousse.png");
        acteursRepousses = new ArrayList<>();

    }

    @Override
    public void attaquer() {
        if (cible != null && estEnPortee(cible) && !acteursRepousses.contains(cible)) {
            // Effectuer l'attaque sur l'ennemi ciblé
            cible.decrementerPv(getDegat());
            decrementerPv(5);
            System.out.println("Tourelle repousse attaque l'ennemi !");

            // Repousser l'ennemi
            cible.respawn();
            acteursRepousses.add(cible);

        } else {
            // L'ennemi n'est plus à portée, aucun ennemi n'est ciblé ou l'ennemi a déjà été repoussé
            System.out.println("Aucune cible valide pour la tourelle repousse !");
        }
    }

}