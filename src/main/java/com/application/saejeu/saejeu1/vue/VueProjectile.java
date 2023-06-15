package com.application.saejeu.saejeu1.vue;
import com.application.saejeu.saejeu1.Main;
import com.application.saejeu.saejeu1.modele.Projectile;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
public class VueProjectile {
    private Pane pane;


    private ArrayList<ImageView> imageViews;
    Projectile projectile;

    public VueProjectile(Pane pane, Projectile projectile) {
        this.pane = pane;
        this.projectile = projectile;
        this.imageViews = new ArrayList<>();


        URL urlImageEnn = Main.class.getResource("projectile.png"); // Récupération de l'URL de l'image de l'ennemi
        Image imageEnn = new Image(String.valueOf(urlImageEnn)); // Chargement de l'image
        ImageView imageView = new ImageView(imageEnn); // Création de l'ImageView pour afficher l'image de l'ennemi
        imageView.translateXProperty().bind(projectile.xProperty()); // Lier la position horizontale de l'ImageView à la position horizontale de l'ennemi
        imageView.translateYProperty().bind(projectile.yProperty()); // Lier la position verticale de l'ImageView à la position verticale de l'ennemi
        this.pane.getChildren().add(imageView); // Ajout de l'ImageView au panneau de jeu
        this.imageViews.add(imageView); // Ajout de l'ImageView à la liste des ImageView
        // System.out.println("image id"+ imageView.getId());


        AnimationTimer timer = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {

                if (lastUpdate > 0) {

                    double elapsedTime = (now - lastUpdate) / 1000000000.0;

                    projectile.updatePosition(elapsedTime);
                }
                lastUpdate = now;
            }
        };
        timer.start();
    }

    public Projectile getProjectile() {
        return projectile;
    }

    public void retirerImageProjectile(Projectile p) {
        ImageView imageViewToRemove = null;
        for (ImageView imageView : imageViews) {
            if (imageView.getTranslateX() == p.getX() && imageView.getTranslateY() == p.getY()) {
                imageViewToRemove = imageView;
                break;
            }
        }

        if (imageViewToRemove != null) {
            pane.getChildren().remove(imageViewToRemove); // Retirer l'ImageView du panneau de jeu
            imageViews.remove(imageViewToRemove); // Retirer l'ImageView de la liste des ImageView

        }
    }
}