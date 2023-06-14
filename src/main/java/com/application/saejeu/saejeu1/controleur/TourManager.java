package com.application.saejeu.saejeu1.controleur;

import com.application.saejeu.saejeu1.controleur.Controleur;
import com.application.saejeu.saejeu1.modele.Environnement;
import com.application.saejeu.saejeu1.modele.Manche;
import com.application.saejeu.saejeu1.modele.Pièce;
import com.application.saejeu.saejeu1.modele.Tourelle.Tourelle;
import com.application.saejeu.saejeu1.modele.Zombie.Acteur;
import javafx.animation.Timeline;

import java.util.ArrayList;

public class TourManager {
    private Environnement environnement; // Référence vers l'environnement du jeu
    private Manche manche; // Référence vers la manche en cours
    private Timeline gameLoop; // La boucle de jeu pour la gestion des tours
    private int nb_manche; // Le nombre total de manches dans le jeu
    private Controleur controleur; // Référence vers le contrôleur du jeu

    public TourManager(Environnement environnement, Manche manche, int nb_manche, Controleur controleur,Timeline gameLoop) {
        this.environnement = environnement; // Initialise la référence vers l'environnement avec la valeur fournie en paramètre
        this.manche = manche; // Initialise la référence vers la manche avec la valeur fournie en paramètre
        this.nb_manche = nb_manche; // Initialise le nombre total de manches avec la valeur fournie en paramètre
        this.controleur = controleur; // Initialise la référence vers le contrôleur avec la valeur fournie en paramètre
        this.gameLoop = gameLoop; // Initialise la variable d'instance avec la valeur fournie en paramètre

    }


    // Le centre de contrôle pour les tours
    public void effectuerTour() {
        controleur.mettreAJourAffichageZombies(environnement.getActeurs().size()); // Met à jour l'affichage du nombre de zombies
        controleur.mettreAJourAffichageTourelles(environnement.getTourelles().size()); // Met à jour l'affichage du nombre de tourelle
        //System.out.println("un tour"); // Affiche un message indiquant le début d'un tour
        ArrayList<Acteur> acteursCopy = new ArrayList<>(environnement.getActeurs()); // Crée une copie de la liste des acteurs dans l'environnement
        for (Acteur zombie : acteursCopy) {
            if (zombie.getCyclesRestants() == 0) {
                zombie.deplacement(); // Effectue le déplacement du zombie s'il n'a plus de cycles restants
            } else {
                zombie.decrementerCyclesRestants(); // Décrémente le nombre de cycles restants du zombie
            }

            effectuerTourTourelles(zombie); // Appelle la méthode effectuerTourTourelles() pour gérer les actions des tourelles pendant le tour

            if (!zombie.estVivant()) {
                environnement.getActeurs().remove(zombie); // Supprime le zombie de la liste des acteurs s'il n'est plus vivant
            }

            if (environnement.getActeurs().isEmpty()) {
                terminerManche(); // Termine la manche si tous les zombies ont été éliminés
            }

            if (zombie.getY() == 34 * 16 && zombie.getX() == 89 * 16) {
                gérerCollision(zombie); // Gère la collision lorsque le zombie atteint sa cible
            }
        }
        if (this.controleur.getTemps() % 5 == 0) {

            effectuerTourPièces();
        }
    }

    public void ajouterZombie() {
        System.out.println("Ajout zombie"); // Affiche un message indiquant l'ajout d'un zombie
        environnement.créerZombie(); // Appelle la méthode créerZombie() de l'environnement pour créer un nouveau zombie
        manche.setCompteurZombie(); // Met à jour le compteur de zombies de la manche
    }

    // Permet aux tourelles d'attaquer l'ennemi et de supprimer la tourelle quand elle ne fonctionne plus
    public void effectuerTourTourelles(Acteur zombie) {
        ArrayList<Tourelle> tourCopy = new ArrayList<>(environnement.getTourelles()); // Crée une copie de la liste des tourelles dans l'environnement
        for (Tourelle tour : tourCopy) {
            tour.setCible(zombie); // Définit le zombie comme cible de la tourelle
            tour.attaquer(); // Fait attaquer la tourelle

            if (!tour.estEnMarche()) {
                environnement.getTourelles().remove(tour); // Supprime la tourelle de la liste des tourelles si elle n'est plus en marche
            }
        }
    }
    public void effectuerTourPièces() {

        ArrayList<Pièce> piècesCopy = new ArrayList<>(this.environnement.getListePièces());
        if (piècesCopy.size() < 10) {

            this.environnement.créerUnCertainsNombreDePièce(1);
        }
        for (Pièce pièce : piècesCopy) {

            mouvementDePièce(pièce);
        }
    }
    public void mouvementDePièce(Pièce pièce) {

        pièce.getVuePièce().retirerImagePièce(pièce);
        pièce.setX(pièce.getX() + this.environnement.getTileMap().getTileSize());
        pièce.setY(pièce.getY() + this.environnement.getTileMap().getTileSize());
        pièce.getVuePièce().imagePièce();
    }
    public void terminerManche() {
        System.out.println("Tous les zombies ont été éliminés !"); // Affiche un message indiquant que tous les zombies ont été éliminés
        if (manche.numeroMancheProperty().get() < nb_manche) {
            System.out.println("Début de la prochaine manche..."); // Affiche un message indiquant le début de la prochaine manche
            manche.demarrerManche(environnement); // Démarre la prochaine manche en utilisant l'environnement actuel
            manche.setCompteurZombie0(); // Réinitialise le compteur de zombies de la manche à zéro
            this.environnement.gagnerUnCertainNombreDePièce(100); // gagner 100 pièces à la fin de chaque manche
        } else {
            System.out.println("Vous avez terminé toutes les manches !"); // Affiche un message indiquant que toutes les manches ont été terminées
            gameLoop.stop(); // Arrête la boucle de jeu
            controleur.afficherWinJeuScene();
        }
    }
    // Gère la perte de vie lorsque le zombie atteint la cible
    public void gérerCollision(Acteur zombie) {
        environnement.décrémenterVies(); // Décrémente le nombre de vies de l'environnement
        environnement.getActeurs().remove(zombie); // Supprime le zombie de la liste des acteurs
        int viesRestantes = environnement.getVies(); // Obtient le nombre de vies restantes

        if (manche.numeroMancheProperty().get() < nb_manche && viesRestantes != 0 && environnement.getActeurs().isEmpty()) {
            System.out.println("Début de la prochaine manche..."); // Affiche un message indiquant le début de la prochaine manche
            manche.demarrerManche(environnement); // Démarre la prochaine manche en utilisant l'environnement actuel
            manche.setCompteurZombie0(); // Réinitialise le compteur de zombies de la manche à zéro
            this.environnement.gagnerUnCertainNombreDePièce(100); // gagner 100 pièces à la fin de chaque manche
        }
        else if (viesRestantes == 0) {
            System.out.println("Vous avez perdu !");
            gameLoop.stop(); // Arrête la boucle de jeu
            controleur.afficherGameOverScene(); // Affiche l'écran de fin de jeu en utilisant le contrôleur
        } else if (viesRestantes >= 1 && manche.numeroMancheProperty().get() == nb_manche && environnement.getActeurs().isEmpty()) {
            // Victoire
            System.out.println("Vous avez gagné !");
            gameLoop.stop(); // Arrête la boucle de jeu
            controleur.afficherWinJeuScene(); // Affiche l'écran de victoire
        }
    }
}

