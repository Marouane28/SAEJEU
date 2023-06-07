package com.application.saejeu.saejeu1.controleur;

import com.application.saejeu.saejeu1.modele.Acteur;
import com.application.saejeu.saejeu1.modele.Tourelle;
import com.application.saejeu.saejeu1.vue.VueEnnemi;
import com.application.saejeu.saejeu1.vue.VueTourelle;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class ListObsTourelle implements ListChangeListener<Tourelle> {

    private Pane panneauJeu; // Le panneau du jeu où seront ajoutées les vues des tourelles
    private List<VueTourelle> vuesTourelles; // Liste des vues des tourelles

    public ListObsTourelle(Pane panneauJeu) {
        this.panneauJeu = panneauJeu; // Initialise le panneau du jeu
        this.vuesTourelles = new ArrayList<>(); // Initialise la liste des vues des tourelles
    }

    @Override
    public void onChanged(Change<? extends Tourelle> change) {
        while (change.next()) {
            if (change.wasAdded()) {
                for (Tourelle tourelle : change.getAddedSubList()) {
                    ajouterVueTourelle(tourelle); // Ajoute la vue de chaque nouvelle tourelle ajoutée
                }
            }
            if (change.wasRemoved()) {
                for (Tourelle tourelle : change.getRemoved()) {
                    retirerVueTourelle(tourelle); // Retire la vue de chaque tourelle supprimée
                }
            }
        }
    }

    private void ajouterVueTourelle(Tourelle tourelle) {
        VueTourelle vueTourelle = new VueTourelle(panneauJeu, tourelle); // Crée une nouvelle vue pour la tourelle
        vuesTourelles.add(vueTourelle); // Ajoute la vue à la liste des vues des tourelles
        tourelle.setVueTourelle(vueTourelle); // Associe la vue à la tourelle
    }

    private void retirerVueTourelle(Tourelle tourelle) {
        VueTourelle vueTourelleToRemove = null;
        for (VueTourelle vueTourelle : vuesTourelles) {
            if (vueTourelle.getTourelle() == tourelle) { // Recherche la vue correspondant à la tourelle supprimée
                vueTourelleToRemove = vueTourelle; // Stocke la vue à supprimer
                break;
            }
        }
        if (vueTourelleToRemove != null) {
            vueTourelleToRemove.retirerImageTourelle(tourelle); // Retire l'image de la tourelle dans la vue
            vuesTourelles.remove(vueTourelleToRemove); // Supprime la vue des tourelles
        }
    }
}