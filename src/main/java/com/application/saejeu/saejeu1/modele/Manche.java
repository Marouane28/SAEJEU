package com.application.saejeu.saejeu1.modele;

import java.util.ArrayList;

public class Manche {
    private int nombreZombies; // nombre de zombie pour chaque manche
    private int numeroManche; // Variable pour stocker le numéro de la manche
    private int compteurZombie;

    public Manche() {
        this.nombreZombies = 10;
        numeroManche= 1;
    }

    public void demarrerManche(Environnement environnement) {
        nombreZombies += 10; // 10 zombies par manches
        numeroManche++;
    }

    public int getNumeroManche() {
        return numeroManche;
    }

    public int getNombreZombies() {
        return nombreZombies;
    }
    public int getCompteurZombie() {
        return compteurZombie;
    }
    public void setCompteurZombie() {
        this.compteurZombie++;
    }
    public void setCompteurZombie0() {
        this.compteurZombie=0;
    }

}
