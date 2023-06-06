package com.application.saejeu.saejeu1.vue;

import com.application.saejeu.saejeu1.Main;
import com.application.saejeu.saejeu1.modele.ZombieLent;
import com.application.saejeu.saejeu1.modele.ZombieRapide;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import com.application.saejeu.saejeu1.modele.Acteur;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class VueEnnemi {

    private ArrayList<ImageView> imageViews;
    private Pane panneauJeu;
    private Acteur acteur;

    private Rectangle barreVie;
    private final double barreVieWidth = 20; // Largeur de la barre de vie

    private final double barreVieHeight = 5; // Hauteur de la barre de vie

    public VueEnnemi(Pane panneauJeu, Acteur acteur) {
        this.panneauJeu = panneauJeu;
        this.acteur = acteur;
        this.imageViews = new ArrayList<>();
        imageZombie();
        initialiserBarreVie(); // Ajouter l'initialisation de la barre de vie

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
            retirerBarreVie();
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

        double nouvelleLongueur = barreVieWidth * pourcentageVie;
        barreVie.setWidth(nouvelleLongueur);
    }
    private void initialiserBarreVie() {
        barreVie = new Rectangle(barreVieWidth, barreVieHeight);
        barreVie.setFill(Color.GREEN); // Couleur initiale de la barre de vie
        barreVie.setStroke(Color.BLACK);
        barreVie.setStrokeWidth(1.0);
        barreVie.translateXProperty().bind(acteur.xProperty().subtract(5)); // Décalage de 5 pixels vers la gauche
        barreVie.translateYProperty().bind(acteur.yProperty().subtract(10)); // Ajuster la position verticale de la barre de vie
        panneauJeu.getChildren().add(barreVie);
    }

    public void retirerBarreVie() {
        panneauJeu.getChildren().remove(barreVie);
    }



}
