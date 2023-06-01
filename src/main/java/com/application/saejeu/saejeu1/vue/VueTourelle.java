package com.application.saejeu.saejeu1.vue;

import com.application.saejeu.saejeu1.Main;
import com.application.saejeu.saejeu1.modele.TourelleGèle;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import com.application.saejeu.saejeu1.modele.Tourelle;
import com.application.saejeu.saejeu1.modele.TourelleMitrailleuse;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ArrayList;

public class VueTourelle {
    private Image image;
    private Pane panneauJeu;
    private Tourelle tourelles;

    public VueTourelle(Pane panneauJeu, Tourelle tourelles) {
        this.panneauJeu = panneauJeu;
        this.tourelles = tourelles;
        imageTourelle();
        afficherRayonPortee();

    }

    public void imageTourelle(){
            URL urlImageEnn = Main.class.getResource(tourelles.getNomImage());
            image = new Image(String.valueOf(urlImageEnn));
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(16);
            imageView.setFitWidth(16);
            imageView.translateXProperty().bind(tourelles.xProperty());
            imageView.translateYProperty().bind(tourelles.yProperty());
            this.panneauJeu.getChildren().add(imageView);
    }
    public void afficherRayonPortee()   {
        Circle rayonPortee = new Circle();
        double tourelleLargeur = image.getWidth();  // Remplacez image par la variable contenant votre image de tourelle
        double tourelleHauteur = image.getHeight();  // Remplacez image par la variable contenant votre image de tourelle
        double centerX = tourelles.getX() + tourelleLargeur / 16;
        double centerY = tourelles.getY() + tourelleHauteur / 16;
        rayonPortee.setCenterX(centerX);
        rayonPortee.setCenterY(centerY);
        rayonPortee.setRadius(tourelles.getPortee());
        rayonPortee.setFill(Color.rgb(255, 0, 0, 0.2));  // Remplissage avec une couleur rouge transparente
        rayonPortee.setStroke(Color.rgb(255, 0, 0, 0.8));  // Bordure avec une couleur rouge semi-transparente
        rayonPortee.setStrokeWidth(2.0);

        // Appliquer un effet d'ombre au cercle de rayon de portée
        DropShadow dropShadow = new DropShadow(10, Color.rgb(255, 0, 0, 0.4));
        rayonPortee.setEffect(dropShadow);

        // Ajouter le cercle de rayon de portée au panneau de jeu
        panneauJeu.getChildren().add(rayonPortee);
    }


}
