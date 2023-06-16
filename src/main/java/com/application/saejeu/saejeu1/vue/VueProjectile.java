package com.application.saejeu.saejeu1.vue;
import com.application.saejeu.saejeu1.Main;
import com.application.saejeu.saejeu1.modele.Projectile.Projectile;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
public class VueProjectile {

    private Pane pane;  // La référence vers le conteneur de la vue (un Pane)
    private ArrayList<ImageView> imageViews;  // Une liste d'ImageView pour afficher les images du projectile
    private Projectile projectile;  // Le projectile à afficher

    public VueProjectile(Pane pane, Projectile projectile) {
        this.pane = pane;
        this.projectile = projectile;
        this.imageViews = new ArrayList<>();  // Initialise la liste d'ImageView

        imageProjectile();  // Appelle une méthode pour configurer les images du projectile

        AnimationTimer timer = new AnimationTimer() {
            private long lastUpdate = 0;
            @Override
            public void handle(long now) {
                // Vérifie si lastUpdate est supérieur à 0, ce qui signifie que le temps précédent a été enregistré
                if (lastUpdate > 0) {
                    // Calcule le temps écoulé depuis la dernière mise à jour en secondes
                    double elapsedTime = (now - lastUpdate) / 1000000000.0;

                    // Met à jour la position du projectile en utilisant le temps écoulé
                    projectile.updatePosition(elapsedTime);
                }
                // Met à jour lastUpdate avec la valeur actuelle du temps
                lastUpdate = now;
            }
        };
        // Démarre le timer d'animation
        timer.start();

    }

    public Projectile getProjectile() {
        return projectile;
    }

    public void imageProjectile(){
        URL urlImage = Main.class.getResource(projectile.getNomImage()); // Récupération de l'URL de l'image du projectile
        Image image = new Image(String.valueOf(urlImage)); // Chargement de l'image
        ImageView imageView = new ImageView(image); // Création de l'ImageView pour afficher l'image du projectile
        imageView.translateXProperty().bind(projectile.xProperty()); // Lier la position horizontale de l'ImageView à la position horizontale du projectile
        imageView.translateYProperty().bind(projectile.yProperty()); // Lier la position verticale de l'ImageView à la position verticale du projectile
        this.pane.getChildren().add(imageView); // Ajout de l'ImageView au panneau de jeu
        this.imageViews.add(imageView); // Ajout de l'ImageView à la liste des ImageView
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