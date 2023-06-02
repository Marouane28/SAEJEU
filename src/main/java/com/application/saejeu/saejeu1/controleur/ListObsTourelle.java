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

    private Pane panneauJeu;
    private List<VueTourelle> vuesTourelles;

    public ListObsTourelle(Pane panneauJeu) {
        this.panneauJeu = panneauJeu;
        this.vuesTourelles = new ArrayList<>();
    }

    @Override
    public void onChanged(Change<? extends Tourelle> change) {
        while (change.next()) {
            if (change.wasAdded()) {
                for (Tourelle tourelle : change.getAddedSubList()) {
                    ajouterVueTourelle(tourelle);
                }
            }
            if (change.wasRemoved()) {
                for (Tourelle tourelle : change.getRemoved()) {
                    retirerVueTourelle(tourelle);
                }
            }
        }
    }

    private void ajouterVueTourelle(Tourelle tourelle) {
        VueTourelle vueTourelle = new VueTourelle(panneauJeu, tourelle);
        vuesTourelles.add(vueTourelle);
    }

    private void retirerVueTourelle(Tourelle tourelle) {
        VueTourelle vueTourelleToRemove = null;
        for (VueTourelle vueTourelle : vuesTourelles) {
            if (vueTourelle.getTourelle() == tourelle) {
                vueTourelleToRemove = vueTourelle;
                break;
            }
        }
        if (vueTourelleToRemove != null) {
            vueTourelleToRemove.retirerImageTourelle(tourelle);
            vuesTourelles.remove(vueTourelleToRemove);
        }
    }
}