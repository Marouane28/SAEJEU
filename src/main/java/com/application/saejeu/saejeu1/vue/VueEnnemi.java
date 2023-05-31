package com.application.saejeu.saejeu1.vue;

import com.application.saejeu.saejeu1.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import com.application.saejeu.saejeu1.modele.Acteur;

import java.net.URL;
import java.util.ArrayList;

public class VueEnnemi {

    private ArrayList<ImageView> imageViews;
    private Pane panneauJeu;
    private ArrayList<Acteur> acteurs;

    public VueEnnemi(Pane panneauJeu, ArrayList<Acteur> acteurs) {
        this.panneauJeu = panneauJeu;
        this.acteurs = acteurs;
        this.imageViews = new ArrayList<>();

        for (Acteur g : this.acteurs) {
            URL urlImageEnn = Main.class.getResource("zombie.png");
            Image imageEnn = new Image(String.valueOf(urlImageEnn));
            ImageView iv2 = new ImageView(imageEnn);
            iv2.translateXProperty().bind(g.xProperty());
            iv2.translateYProperty().bind(g.yProperty());
            this.panneauJeu.getChildren().add(iv2);
            this.imageViews.add(iv2);
        }
    }

    public void removeImageView(ImageView imageView) {
        panneauJeu.getChildren().remove(imageView);
        imageViews.remove(imageView);
    }

    public ArrayList<ImageView> getImageViews() {
        return imageViews;
    }
}

