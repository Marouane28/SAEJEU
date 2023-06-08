package com.application.saejeu.saejeu1.controleur;

import com.application.saejeu.saejeu1.modele.Acteur;
import com.application.saejeu.saejeu1.vue.VueEnnemi;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class ListObsActeur implements ListChangeListener<Acteur> {

    private Pane panneauJeu;
    private ArrayList<VueEnnemi> vuesEnnemis;

    public ListObsActeur(Pane panneauJeu) {
        this.panneauJeu = panneauJeu;
        this.vuesEnnemis = new ArrayList<>();
    }

    @Override
    public void onChanged(Change<? extends Acteur> change) {
        while (change.next()) {
            if (change.wasAdded()) {
                for (Acteur acteur : change.getAddedSubList()) {
                    ajouterVueEnnemi(acteur);
                }
            }
            if (change.wasRemoved()) {
                for (Acteur acteur : change.getRemoved()) {
                    retirerVueEnnemi(acteur);
                }
            }
        }
    }

    private void ajouterVueEnnemi(Acteur acteur) {
        VueEnnemi vueEnnemi = new VueEnnemi(panneauJeu, acteur);
        vuesEnnemis.add(vueEnnemi);
    }

    private void retirerVueEnnemi(Acteur acteur) {
        VueEnnemi vueEnnemiToRemove = null;
        for (VueEnnemi vueEnnemi : vuesEnnemis) {
            if (vueEnnemi.getActeur() == acteur) {
                vueEnnemiToRemove = vueEnnemi;
                break;
            }
        }

        if (vueEnnemiToRemove != null) {
            vueEnnemiToRemove.retirerImageEnnemi(acteur);
            vuesEnnemis.remove(vueEnnemiToRemove);
        }
    }
}