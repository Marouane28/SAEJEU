package com.application.saejeu.saejeu1.vue;

import com.application.saejeu.saejeu1.Main;
import com.application.saejeu.saejeu1.modele.ZombieLent;
import com.application.saejeu.saejeu1.modele.ZombieRapide;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import com.application.saejeu.saejeu1.modele.Acteur;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class VueEnnemi {

    private ArrayList<ImageView> imageViews;
    private Pane panneauJeu;
    private Acteur acteur;

    public VueEnnemi(Pane panneauJeu, Acteur acteur) {
        this.panneauJeu = panneauJeu;
        this.acteur = acteur;
        this.imageViews = new ArrayList<>();

        imageZombie();
    }

    public void imageZombie() {
            URL urlImageEnn = Main.class.getResource(acteur.getNomImage());
            Image imageEnn = new Image(String.valueOf(urlImageEnn));
            ImageView imageView = new ImageView(imageEnn);
            imageView.translateXProperty().bind(acteur.xProperty());
            imageView.translateYProperty().bind(acteur.yProperty());
            this.panneauJeu.getChildren().add(imageView);
            this.imageViews.add(imageView);
    }

    public void retirerImageEnnemi(Acteur ennemi) {
        ImageView imageViewToRemove = null;
        for (ImageView imageView : imageViews) {
            if (imageView.getTranslateX() == ennemi.getX() && imageView.getTranslateY() == ennemi.getY()) {
                imageViewToRemove = imageView;
                break;
            }
        }

        if (imageViewToRemove != null) {
            panneauJeu.getChildren().remove(imageViewToRemove);
            imageViews.remove(imageViewToRemove);
        }
    }

    public Acteur getActeur() {
        return acteur;
    }
}
