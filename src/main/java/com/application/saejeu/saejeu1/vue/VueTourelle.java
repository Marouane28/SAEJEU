package com.application.saejeu.saejeu1.vue;

import com.application.saejeu.saejeu1.Main;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import com.application.saejeu.saejeu1.modele.Tourelle.Tourelle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ArrayList;

public class VueTourelle {
    private Image image; // Image de la tourelle
    private Pane panneauJeu; // Panneau du jeu
    private ArrayList<ImageView> imageViews; // Liste des ImageView pour afficher la tourelle
    private Tourelle tourelle; // Instance de la classe Tourelle pour représenter la tourelle
    private Rectangle barreVie; // Barre de vie de la tourelle
    private final double barreVieWidth = 45; // Largeur de la barre de vie
    private ImageView imageView;
    private final double barreVieHeight = 5; // Hauteur de la barre de vie

    public VueTourelle(Pane panneauJeu, Tourelle tourelle) {
        this.panneauJeu = panneauJeu;
        this.tourelle = tourelle;
        this.imageViews = new ArrayList<>();
        imageTourelle(); // Affichage de l'image de la tourelle
        afficherRayonPortee(); // Affichage du rayon de portée de la tourelle
        initialiserBarreVie(); // Initialisation de la barre de vie de la tourelle

        setOnClickUpgrade(); // Définir le clic pour l'amélioration de la tourelle

    }

    // Définit le gestionnaire d'événements pour le clic de la tourelle
    public void setOnClickUpgrade() {
        this.imageView.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                // Vérifiez si la tourelle peut être améliorée
                if (!tourelle.isEstAméliorée()) {
                    // Appeler la méthode d'amélioration de la tourelle
                    tourelle.améliorerTourelle();
                }
            }
        });
    }

    // Méthode pour afficher l'image de la tourelle
    public void imageTourelle() {
        URL urlImageEnn = Main.class.getResource(tourelle.getNomImage()); // Récupération de l'URL de l'image de la tourelle
        image = new Image(String.valueOf(urlImageEnn)); // Chargement de l'image
        this.setImageView(new ImageView(this.image)); // Création de l'ImageView pour afficher l'image de la tourelle
        imageView.setFitHeight(20); // Ajuster la hauteur de l'ImageView
        imageView.setFitWidth(20); // Ajuster la largeur de l'ImageView
        imageView.translateXProperty().bind(tourelle.xProperty()); // Lier la position horizontale de l'ImageView à la position horizontale de la tourelle
        imageView.translateYProperty().bind(tourelle.yProperty()); // Lier la position verticale de l'ImageView à la position verticale de la tourelle
        this.panneauJeu.getChildren().add(imageView); // Ajout de l'ImageView au panneau de jeu
        this.imageViews.add(imageView); // Ajout de l'ImageView à la liste des ImageView

    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    // Méthode pour afficher le rayon de portée de la tourelle
    public void afficherRayonPortee() {
        Circle rayonPortee = new Circle(); // Création d'un cercle pour représenter le rayon de portée
        double tourelleLargeur = image.getWidth(); // Largeur de l'image de la tourelle
        double tourelleHauteur = image.getHeight(); // Hauteur de l'image de la tourelle
        double centerX = tourelle.getX() + tourelleLargeur / 16; // Calcul du centre du cercle en fonction de la position de la tourelle
        double centerY = tourelle.getY() + tourelleHauteur / 16;
        rayonPortee.setCenterX(centerX); // Positionnement du centre du cercle
        rayonPortee.setCenterY(centerY);
        rayonPortee.setRadius(tourelle.getPortée()); // Définition du rayon du cercle en fonction de la portée de la tourelle
        rayonPortee.setFill(null); // Aucun remplissage
        rayonPortee.setStroke(Color.rgb(255, 0, 0, 0.8)); // Couleur de contour du cercle
        rayonPortee.setStrokeWidth(2.0); // Épaisseur du contour

        DropShadow dropShadow = new DropShadow(10, Color.rgb(255, 0, 0, 0.4)); // Effet d'ombre portée pour le cercle
        rayonPortee.setEffect(dropShadow);

        panneauJeu.getChildren().add(rayonPortee); // Ajout du cercle au panneau de jeu
    }
    public void afficherRayonPorteeAmélioré() {
        Circle rayonPortee = new Circle(); // Création d'un cercle pour représenter le rayon de portée
        double tourelleLargeur = image.getWidth(); // Largeur de l'image de la tourelle
        double tourelleHauteur = image.getHeight(); // Hauteur de l'image de la tourelle
        double centerX = tourelle.getX() + tourelleLargeur / 16; // Calcul du centre du cercle en fonction de la position de la tourelle
        double centerY = tourelle.getY() + tourelleHauteur / 16;
        rayonPortee.setCenterX(centerX); // Positionnement du centre du cercle
        rayonPortee.setCenterY(centerY);
        rayonPortee.setRadius(tourelle.getPortée()); // Définition du rayon du cercle en fonction de la portée de la tourelle
        rayonPortee.setFill(null); // Aucun remplissage
        rayonPortee.setStroke(Color.rgb(0, 0, 255, 0.8)); // Couleur de contour du cercle
        rayonPortee.setStrokeWidth(2.0); // Épaisseur du contour

        DropShadow dropShadow = new DropShadow(10, Color.rgb(255, 0, 0, 0.4)); // Effet d'ombre portée pour le cercle
        rayonPortee.setEffect(dropShadow);

        panneauJeu.getChildren().add(rayonPortee); // Ajout du cercle au panneau de jeu
    }
    // Méthode pour retirer le rayon de portée de la tourelle
    public void retirerRayonPortee() {
        for (Node node : panneauJeu.getChildren()) {
            if (node instanceof Circle) {
                Circle circle = (Circle) node;
                if (circle.getRadius() == tourelle.getPortée()) { // Vérification si le cercle correspondant à la portée de la tourelle
                    panneauJeu.getChildren().remove(circle); // Retirer le cercle du panneau de jeu
                    break;
                }
            }
        }
    }
    // Méthode pour retirer l'image de la tourelle
    public void retirerImageTourelle(Tourelle tourelle) {
        ImageView imageViewToRemove = null;
        for (ImageView imageView : imageViews) {
            if (imageView.getTranslateX() == tourelle.getX() && imageView.getTranslateY() == tourelle.getY()) { // Vérification si l'ImageView correspond à la position de la tourelle
                imageViewToRemove = imageView;
                break;
            }
        }
        if (imageViewToRemove != null) {
            panneauJeu.getChildren().remove(imageViewToRemove); // Retirer l'ImageView du panneau de jeu
            retirerRayonPortee(); // Retirer le rayon de portée de la tourelle
            imageViews.remove(imageViewToRemove); // Retirer l'ImageView de la liste des ImageView
            retirerBarreVie(); // Retirer la barre de vie de la tourelle
        }
    }

    // Méthode pour récupérer la tourelle associée à cette vue
    public Tourelle getTourelle() {
        return tourelle;
    }

    // Méthode pour actualiser la barre d'état de la tourelle en fonction de sa vie
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
        barreVie.setWidth(nouvelleLongueur); // Ajuster la longueur de la barre de vie en fonction du pourcentage de vie restante
    }

    // Méthode pour initialiser la barre de vie de la tourelle
    private void initialiserBarreVie() {
        barreVie = new Rectangle(barreVieWidth, barreVieHeight); // Création d'un rectangle pour représenter la barre de vie
        barreVie.setFill(Color.GREEN); // Couleur initiale de la barre de vie
        barreVie.setStroke(Color.BLACK); // Couleur du contour du rectangle
        barreVie.setStrokeWidth(1.0); // Épaisseur du contour
        barreVie.translateXProperty().bind(tourelle.xProperty().subtract(5)); // Décalage de 5 pixels vers la gauche par rapport à la position de la tourelle
        barreVie.translateYProperty().bind(tourelle.yProperty().subtract(10)); // Ajuster la position verticale de la barre de vie par rapport à la position de la tourelle
        panneauJeu.getChildren().add(barreVie); // Ajout de la barre de vie au panneau de jeu
    }

    // Méthode pour retirer la barre de vie de la tourelle
    public void retirerBarreVie() {
        panneauJeu.getChildren().remove(barreVie); // Retirer la barre de vie du panneau de jeu
    }
}
