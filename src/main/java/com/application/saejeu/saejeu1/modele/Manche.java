package com.application.saejeu.saejeu1.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public class Manche {
    private int nombreZombies; // Variable pour stocker le nombre de zombies pour chaque manche
    IntegerProperty numeroManche; // Variable pour stocker le numéro de la manche
    private int compteurZombie; // Variable pour compter le nombre de zombies actuellement

    public Manche() {
        this.nombreZombies = 10; // Initialise le nombre de zombies à 10 pour la première manche
        this.numeroManche = new SimpleIntegerProperty(1); // Initialise le numéro de la première manche à 1

    }
    public IntegerProperty numeroMancheProperty() {
        return numeroManche;
    }

    public void setNumeroManche(int numeroManche) {
        this.numeroManche.set(numeroManche);
    }

    public void demarrerManche(Environnement environnement) {
        nombreZombies += 10; // Ajoute 10 zombies pour la prochaine manche
        setNumeroManche(numeroMancheProperty().get() + 1);; // Incrémente le numéro de la manche
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
