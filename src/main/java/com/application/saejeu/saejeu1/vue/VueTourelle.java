package com.application.saejeu.saejeu1.vue;

import com.application.saejeu.saejeu1.Main;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import com.application.saejeu.saejeu1.modele.Tourelle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ArrayList;

public class VueTourelle {
    private Image image;
    private Pane panneauJeu;
    private ArrayList<ImageView> imageViews;
    private Tourelle tourelle;
    private Rectangle barreVie;
    private final double barreVieWidth = 45; // Largeur de la barre de vie

    private final double barreVieHeight = 5; // Hauteur de la barre de vie



    public VueTourelle(Pane panneauJeu, Tourelle tourelle) {
        this.panneauJeu = panneauJeu;
        this.tourelle = tourelle;
        this.imageViews = new ArrayList<>();
        imageTourelle();
        afficherRayonPortee();
        initialiserBarreVie(); // Ajouter l'initialisation de la barre de vie

    }
    public void imageTourelle(){
            URL urlImageEnn = Main.class.getResource(tourelle.getNomImage());
            image = new Image(String.valueOf(urlImageEnn));
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(16);
            imageView.setFitWidth(16);
            imageView.translateXProperty().bind(tourelle.xProperty());
            imageView.translateYProperty().bind(tourelle.yProperty());
            this.panneauJeu.getChildren().add(imageView);
            this.imageViews.add(imageView);
    }

    public void afficherRayonPortee() {
        Circle rayonPortee = new Circle();
        double tourelleLargeur = image.getWidth();
        double tourelleHauteur = image.getHeight();
        double centerX = tourelle.getX() + tourelleLargeur / 16;
        double centerY = tourelle.getY() + tourelleHauteur / 16;
        rayonPortee.setCenterX(centerX);
        rayonPortee.setCenterY(centerY);
        rayonPortee.setRadius(tourelle.getPortee());
        rayonPortee.setFill(null);  // Aucun remplissage
        rayonPortee.setStroke(Color.rgb(255, 0, 0, 0.8));
        rayonPortee.setStrokeWidth(2.0);

        DropShadow dropShadow = new DropShadow(10, Color.rgb(255, 0, 0, 0.4));
        rayonPortee.setEffect(dropShadow);

        panneauJeu.getChildren().add(rayonPortee);
    }
    public void retirerRayonPortee() {
        for (Node node : panneauJeu.getChildren()) {
            if (node instanceof Circle) {
                Circle circle = (Circle) node;
                if (circle.getRadius() == tourelle.getPortee()) {
                    panneauJeu.getChildren().remove(circle);
                    break;
                }
            }
        }
    }

    public void retirerImageTourelle(Tourelle tourelle) {
        ImageView imageViewToRemove = null;
        for (ImageView imageView : imageViews) {
            if (imageView.getTranslateX() == tourelle.getX() && imageView.getTranslateY() == tourelle.getY()) {
                imageViewToRemove = imageView;
                break;
            }
        }
        if (imageViewToRemove != null) {
            panneauJeu.getChildren().remove(imageViewToRemove);
            retirerRayonPortee();
            imageViews.remove(imageViewToRemove);
            retirerBarreVie();
        }
    }

    public Tourelle getTourelle() {
        return tourelle;
    }
    public void actualiserBarreEtat() {
        double pourcentageVie = (double) tourelle.getPv() / 60; // Calculer le pourcentage de vie restante

        if (pourcentageVie > 0.5) {
            barreVie.setFill(Color.GREEN); // Plus de 50% de vie, couleur verte
        } else if (pourcentageVie > 0.2) {
            barreVie.setFill(Color.ORANGE); // Entre 20% et 50% de vie, couleur orange
        } else {
            barreVie.setFill(Color.RED); // Moins de 20% de vie, couleur rouge
        }

        double nouvelleLongueur = barreVieWidth * pourcentageVie;
        barreVie.setWidth(nouvelleLongueur);
    }
    private void initialiserBarreVie() {
        barreVie = new Rectangle(barreVieWidth, barreVieHeight);
        barreVie.setFill(Color.GREEN); // Couleur initiale de la barre de vie
        barreVie.setStroke(Color.BLACK);
        barreVie.setStrokeWidth(1.0);
        barreVie.translateXProperty().bind(tourelle.xProperty().subtract(5)); // Décalage de 5 pixels vers la gauche
        barreVie.translateYProperty().bind(tourelle.yProperty().subtract(10)); // Ajuster la position verticale de la barre de vie
        panneauJeu.getChildren().add(barreVie);
    }

    public void retirerBarreVie() {
        panneauJeu.getChildren().remove(barreVie);
    }

}
