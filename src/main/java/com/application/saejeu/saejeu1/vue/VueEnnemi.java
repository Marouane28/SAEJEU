package com.application.saejeu.saejeu1.vue;

import com.application.saejeu.saejeu1.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import com.application.saejeu.saejeu1.modele.Zombie.Acteur;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ArrayList;

public class VueEnnemi {
    private ArrayList<ImageView> imageViews; // Liste des ImageView pour afficher les ennemis
    private Pane panneauJeu; // Panneau du jeu
    private Acteur acteur; // Instance de la classe Acteur pour représenter l'ennemi
    private Rectangle barreVie; // Barre de vie de l'ennemi
    private final double BARRE_VIE_WIDTH = 20; // Largeur de la barre de vie
    private final double BARRE_VIE_HEIGHT = 5; // Hauteur de la barre de vie

    public VueEnnemi(Pane panneauJeu, Acteur acteur) {
        this.panneauJeu = panneauJeu;
        this.acteur = acteur;
        this.imageViews = new ArrayList<>();
        imageZombie(); // Affichage de l'image de l'ennemi
        initialiserBarreVie(); // Initialisation de la barre de vie de l'ennemi
    }

    public void imageZombie() {
        URL urlImageEnn = Main.class.getResource(acteur.getNomImage()); // Récupération de l'URL de l'image de l'ennemi
        Image imageEnn = new Image(String.valueOf(urlImageEnn)); // Chargement de l'image
        ImageView imageView = new ImageView(imageEnn); // Création de l'ImageView pour afficher l'image de l'ennemi
        imageView.translateXProperty().bind(acteur.xProperty()); // Lier la position horizontale de l'ImageView à la position horizontale de l'ennemi
        imageView.translateYProperty().bind(acteur.yProperty()); // Lier la position verticale de l'ImageView à la position verticale de l'ennemi
        this.panneauJeu.getChildren().add(imageView); // Ajout de l'ImageView au panneau de jeu
        this.imageViews.add(imageView); // Ajout de l'ImageView à la liste des ImageView
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
            panneauJeu.getChildren().remove(imageViewToRemove); // Retirer l'ImageView du panneau de jeu
            imageViews.remove(imageViewToRemove); // Retirer l'ImageView de la liste des ImageView
            retirerBarreVie(); // Retirer la barre de vie de l'ennemi
        }
    }

    public Acteur getActeur() {
        return acteur;
    }

    public void actualiserBarreEtat() {
        double pourcentageVie = (double) acteur.getPv() / 60; // Calculer le pourcentage de vie restante
        if (pourcentageVie > 0.5) {
            barreVie.setFill(Color.GREEN); // Plus de 50% de vie, couleur verte
        } else if (pourcentageVie > 0.2) {
            barreVie.setFill(Color.ORANGE); // Entre 20% et 50% de vie, couleur orange
        } else {
            barreVie.setFill(Color.RED); // Moins de 20% de vie, couleur rouge
        }
        double nouvelleLongueur = BARRE_VIE_WIDTH * pourcentageVie;
        barreVie.setWidth(nouvelleLongueur); // Ajuster la longueur de la barre de vie en fonction du pourcentage de vie restante
    }

    private void initialiserBarreVie() {
        barreVie = new Rectangle(BARRE_VIE_WIDTH, BARRE_VIE_HEIGHT); // Création de la barre de vie avec la largeur et la hauteur définies
        barreVie.setFill(Color.GREEN); // Couleur initiale de la barre de vie (verte)
        barreVie.setStroke(Color.BLACK);
        barreVie.setStrokeWidth(1.0);
        barreVie.translateXProperty().bind(acteur.xProperty().subtract(5)); // Décalage de 5 pixels vers la gauche par rapport à la position de l'ennemi
        barreVie.translateYProperty().bind(acteur.yProperty().subtract(10)); // Ajuster la position verticale de la barre de vie par rapport à la position de l'ennemi
        panneauJeu.getChildren().add(barreVie); // Ajout de la barre de vie au panneau de jeu
    }

    public void retirerBarreVie() {
        panneauJeu.getChildren().remove(barreVie); // Retirer la barre de vie du panneau de jeu
    }
}
