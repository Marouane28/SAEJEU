package com.application.saejeu.saejeu1.modele;

public class TourelleRepousse extends Tourelle {

    public TourelleRepousse(int x, int y, Environnement env) {
        super(x, y, 5*16, 20, 5, env);
    }

    public TourelleRepousse() {

    }

    @Override
    public void attaquer() {
        if (cible != null && estEnPortee(cible)) {
            // Effectuer l'attaque sur l'ennemi ciblé
            cible.decrementerPv(getDegat());
            System.out.println("Tourelle repousse attaque l'ennemi !");
        } else {
            // L'ennemi n'est plus à portée ou aucun ennemi n'est ciblé
            System.out.println("Aucune cible valide pour la tourelle repousse !");
        }
    }

}