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
    private Pièce pièce;
    private ImageView imageView;
    private Pane panneauJeu;
    private ArrayList<ImageView> imageViews;
    private Image image;

    public VuePièce(Pane panneauJeu, Pièce pièce) {

        this.panneauJeu = panneauJeu;
        this.pièce = pièce;
        this.imageViews = new ArrayList<>();
        imagePièce();

        this.imageView.setOnMouseClicked(event -> {

            System.out.println("bonjour");
        });
    }

    public void updateMouseClickedListener() {

        this.imageView.setOnMouseClicked(event -> {
            System.out.println("bonjour");
        });
    }

    public Pièce getPièce() {

        return this.pièce;
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
