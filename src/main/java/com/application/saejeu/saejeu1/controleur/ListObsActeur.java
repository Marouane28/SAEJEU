package com.application.saejeu.saejeu1.controleur;

import com.application.saejeu.saejeu1.modele.Acteur;
import com.application.saejeu.saejeu1.vue.VueEnnemi;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

public class ListObsActeur implements ListChangeListener<Acteur> {

    private Pane panneauJeu;

    public ListObsActeur(Pane pan) {
        panneauJeu = pan;
    }

    @Override
    public void onChanged(Change<? extends Acteur> change) {
        System.out.println("changement");
        while (change.next()){
            for (Acteur a : change.getAddedSubList()){
                VueEnnemi v = new VueEnnemi(panneauJeu,a);
            }
        }
    }
}