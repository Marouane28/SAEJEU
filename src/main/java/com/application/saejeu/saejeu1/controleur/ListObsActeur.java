package com.application.saejeu.saejeu1.controleur;

import com.application.saejeu.saejeu1.modele.Acteur;
import com.application.saejeu.saejeu1.vue.VueEnnemi;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class ListObsActeur implements ListChangeListener<Acteur> {

    private Pane panneauJeu; // Le panneau du jeu où seront ajoutées les vues des ennemis
    private ArrayList<VueEnnemi> vuesEnnemis; // Liste des vues des ennemis

    public ListObsActeur(Pane panneauJeu) {
        this.panneauJeu = panneauJeu; // Initialise le panneau du jeu
        this.vuesEnnemis = new ArrayList<>(); // Initialise la liste des vues des ennemis
    }

    @Override
    public void onChanged(Change<? extends Acteur> change) {
        while (change.next()) {
            if (change.wasAdded()) {
                for (Acteur acteur : change.getAddedSubList()) {
                    ajouterVueEnnemi(acteur); // Ajoute la vue de chaque nouvel acteur ennemi ajouté
                }
            }
            if (change.wasRemoved()) {
                for (Acteur acteur : change.getRemoved()) {
                    retirerVueEnnemi(acteur); // Retire la vue de chaque acteur ennemi supprimé
                }
            }
        }
    }

    private void ajouterVueEnnemi(Acteur acteur) {
        VueEnnemi vueEnnemi = new VueEnnemi(panneauJeu, acteur); // Crée une nouvelle vue pour l'acteur ennemi
        vuesEnnemis.add(vueEnnemi); // Ajoute la vue à la liste des vues des ennemis
        acteur.setVueEnnemi(vueEnnemi); // Associe la vue à l'acteur ennemi
    }

    private void retirerVueEnnemi(Acteur acteur) {
        VueEnnemi vueEnnemiToRemove = null;
        for (VueEnnemi vueEnnemi : vuesEnnemis) {
            if (vueEnnemi.getActeur() == acteur) { // Recherche la vue correspondant à l'acteur ennemi supprimé
                vueEnnemiToRemove = vueEnnemi; // Stocke la vue à supprimer
                break;
            }
        }

        if (vueEnnemiToRemove != null) {
            vueEnnemiToRemove.retirerImageEnnemi(acteur); // Retire l'image de l'acteur ennemi dans la vue
            vuesEnnemis.remove(vueEnnemiToRemove); // Supprime la vue des ennemis
        }
    }
}