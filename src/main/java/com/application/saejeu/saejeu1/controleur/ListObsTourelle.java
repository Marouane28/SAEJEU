package com.application.saejeu.saejeu1.controleur;

import com.application.saejeu.saejeu1.modele.Tourelle;
import com.application.saejeu.saejeu1.vue.VueTourelle;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

public class ListObsTourelle implements ListChangeListener<Tourelle> {
    private Pane p;

    public ListObsTourelle(Pane pane) {
        p = pane;
    }

    @Override
    public void onChanged(Change<? extends Tourelle> change) {
        System.out.println("changement");
        while (change.next()){
            for (Tourelle t : change.getAddedSubList()){
                VueTourelle v = new VueTourelle(p,t);
            }
        }
    }
}