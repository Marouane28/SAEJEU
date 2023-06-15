package com.application.saejeu.saejeu1.controleur;

import com.application.saejeu.saejeu1.modele.Pièce;
import com.application.saejeu.saejeu1.modele.Zombie.Acteur;
import com.application.saejeu.saejeu1.vue.VueEnnemi;
import com.application.saejeu.saejeu1.vue.VuePièce;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class ListObsPiece implements ListChangeListener<Pièce> {

    private Pane panneauJeu;
    private ArrayList<VuePièce> vuesPièces;

    public ListObsPiece(Pane panneauJeu) {

        this.panneauJeu = panneauJeu; // Initialise le panneau du jeu
        this.vuesPièces = new ArrayList<>(); // Initialise la liste des vues des ennemis
    }
    @Override
    public void onChanged(Change<? extends Pièce> change) {

        while (change.next()) {
            if (change.wasAdded()) {
                for (Pièce pièce : change.getAddedSubList()) {
                    ajouterVuePièce(pièce); // Ajoute la vue de chaque nouvelle pièce ajoutée
                    System.out.println("ajout pièce");
                }
            }
            if (change.wasRemoved()) {
                for (Pièce pièce : change.getRemoved()) {
                    retirerVuePièce(pièce); // Retire la vue de chaque pièce supprimée
                }
            }
        }
    }


    private void ajouterVuePièce(Pièce pièce) {

        VuePièce vuePièce = new VuePièce(this.panneauJeu, pièce); // Crée une nouvelle vue pour l'acteur ennemi
        this.vuesPièces.add(vuePièce); // Ajoute la vue à la liste des vues des ennemis
        pièce.setVuePièce(vuePièce); // Associe la vue à l'acteur ennemi
    }

    private void retirerVuePièce(Pièce pièce) {

        VuePièce vuePièceToRemove = null;
        for (VuePièce vuePièce : this.vuesPièces) {
            if (vuePièce.getPièce() == pièce) { // Recherche la vue correspondant à l'acteur ennemi supprimé
                vuePièceToRemove = vuePièce; // Stocke la vue à supprimer
                break;
            }
        }

        if (vuePièceToRemove != null) {
            vuePièceToRemove.retirerImagePièce(pièce); // Retire l'image de l'acteur ennemi dans la vue
            this.vuesPièces.remove(vuePièceToRemove); // Supprime la vue des ennemis
        }
    }
}
