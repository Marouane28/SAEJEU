package com.application.saejeu.saejeu1.modele;

import java.util.ArrayList;

public class Manche {
    private int nombreZombies; // Variable pour stocker le nombre de zombies pour chaque manche
    private int numeroManche; // Variable pour stocker le numéro de la manche
    private int compteurZombie; // Variable pour compter le nombre de zombies actuellement

    public Manche() {
        this.nombreZombies = 10; // Initialise le nombre de zombies à 10 pour la première manche
        numeroManche = 1; // Initialise le numéro de la première manche à 1
    }

    public void demarrerManche(Environnement environnement) {
        nombreZombies += 10; // Ajoute 10 zombies pour la prochaine manche
        numeroManche++; // Incrémente le numéro de la manche
    }

    public int getNumeroManche() {
        return numeroManche; // Renvoie le numéro de la manche actuelle
    }

    public int getNombreZombies() {
        return nombreZombies; // Renvoie le nombre de zombies pour la prochaine manche
    }

    public int getCompteurZombie() {
        return compteurZombie; // Renvoie le nombre de zombies actuellement comptés
    }

    public void setCompteurZombie() {
        this.compteurZombie++; // Incrémente le compteur de zombies
    }

    public void setCompteurZombie0() {
        this.compteurZombie = 0; // Réinitialise le compteur de zombies à 0
    }
}
