package com.application.saejeu.saejeu1.vue;

import com.application.saejeu.saejeu1.Main;
import com.application.saejeu.saejeu1.modele.Pièce;
import com.application.saejeu.saejeu1.modele.Zombie.Acteur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;

public class VuePièce {
    private Pièce pièce; // La pièce associée à la vue
    private ImageView imageView; // L'imageView utilisée pour afficher l'image de la pièce
    private Pane panneauJeu; // Le panneau de jeu contenant la vue de la pièce
    private ArrayList<ImageView> imageViews; // Une liste d'ImageViews
    private Image image; // L'image de la pièce

    public VuePièce(Pane panneauJeu, Pièce pièce) {
        this.panneauJeu = panneauJeu; // Initialisation du panneau de jeu
        this.pièce = pièce; // Initialisation de la pièce associée à la vue
        this.imageViews = new ArrayList<>(); // Initialisation de la liste d'ImageViews
        imagePièce(); // Appel à la méthode pour afficher l'image de la pièce
        updateMouseClickedListener(); // Appel à la méthode pour définir l'écouteur d'événements pour le clic de souris
    }

    public void updateMouseClickedListener() {
        // Définition d'un écouteur d'événements pour le clic de souris sur l'image de la pièce
        this.imageView.setOnMouseClicked(event -> {
            // Lorsque l'image de la pièce est cliquée
            // Augmenter le nombre de pièces de l'environnement de la pièce en utilisant la valeur de la pièce
            this.pièce.getEnvironnement().gagnerUnCertainNombreDePièce(this.pièce.getValeur());
            // Supprimer la pièce de l'environnement
            this.pièce.getEnvironnement().suppPièce(this.pièce);
        });
    }

    public Pièce getPièce() {
        return this.pièce; // Renvoie la pièce associée à la vue
    }


    public void imagePièce() {

        URL urlImageEnn = Main.class.getResource(this.pièce.getUrlImage()); // Récupération de l'URL de l'image de la tourelle
        this.image = new Image(String.valueOf(urlImageEnn)); // Chargement de l'image
        this.setImageView(new ImageView(this.image)); // Création de l'ImageView pour afficher l'image de la tourelle
        imageView.setFitHeight(20); // Ajuster la hauteur de l'ImageView
        this.imageView.setFitWidth(20); // Ajuster la largeur de l'ImageView
        this.imageView.translateXProperty().bind(this.pièce.getXProperty());
        this.imageView.translateYProperty().bind(this.pièce.getYProperty());
        this.panneauJeu.getChildren().add(this.imageView); // Ajout de l'ImageView au panneau de jeu
        this.imageViews.add(this.imageView); // Ajout de l'ImageView à la liste des ImageView
    }

    private void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public void retirerImagePièce(Pièce pièce) {

        ImageView imageViewToRemove = null;
        for (ImageView imageView : this.imageViews) {
            if (imageView.getTranslateX() == pièce.getX() && imageView.getTranslateY() == pièce.getY()) {
                imageViewToRemove = imageView;
                break;
            }
        }

        if (imageViewToRemove != null) {
            this.panneauJeu.getChildren().remove(imageViewToRemove); // Retirer l'ImageView du panneau de jeu
            this.imageViews.remove(imageViewToRemove); // Retirer l'ImageView de la liste des ImageView
        }
    }
}
