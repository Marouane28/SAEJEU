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

    public void removeImageView(ImageView imageView) {
        panneauJeu.getChildren().remove(imageView);
        imageViews.remove(imageView);
    }

    public void imageZombie(){
        if (acteur instanceof ZombieRapide){
            URL urlImageEnn = Main.class.getResource("zombie.png");
            Image imageEnn = new Image(String.valueOf(urlImageEnn));
            ImageView iv2 = new ImageView(imageEnn);
            iv2.translateXProperty().bind(acteur.xProperty());
            iv2.translateYProperty().bind(acteur.yProperty());
            this.panneauJeu.getChildren().add(iv2);
            this.imageViews.add(iv2);
        }
        else if (acteur instanceof ZombieLent){
            URL urlImageEnn = Main.class.getResource("zombie.png");
            Image imageEnn = new Image(String.valueOf(urlImageEnn));
            ImageView iv2 = new ImageView(imageEnn);
            iv2.translateXProperty().bind(acteur.xProperty());
            iv2.translateYProperty().bind(acteur.yProperty());
            this.panneauJeu.getChildren().add(iv2);
            this.imageViews.add(iv2);
        }
        else {
            URL urlImageEnn = Main.class.getResource("zombie.png");
            Image imageEnn = new Image(String.valueOf(urlImageEnn));
            ImageView iv2 = new ImageView(imageEnn);
            iv2.translateXProperty().bind(acteur.xProperty());
            iv2.translateYProperty().bind(acteur.yProperty());
            this.panneauJeu.getChildren().add(iv2);
            this.imageViews.add(iv2);
        }
    }

    public void supprimerZombieMort(Acteur zombie){
        for (ImageView imageView : getImageViews()) {
            if (imageView.translateXProperty().isBound() && imageView.translateXProperty().get() == zombie.getX() &&
                    imageView.translateYProperty().isBound() && imageView.translateYProperty().get() == zombie.getY()) {
                removeImageView(imageView);
                System.out.println(" Un zombie est mort ! ");
                break;
            }
        }
    }

    public ArrayList<ImageView> getImageViews() {
        return imageViews;
    }
}

