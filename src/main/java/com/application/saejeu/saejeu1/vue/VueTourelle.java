package com.application.saejeu.saejeu1.vue;

import com.application.saejeu.saejeu1.Main;
import com.application.saejeu.saejeu1.modele.TourelleGèle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import com.application.saejeu.saejeu1.modele.Tourelle;
import com.application.saejeu.saejeu1.modele.TourelleMitrailleuse;

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

    }

    public void imageTourelle(){
        if (tourelles instanceof TourelleMitrailleuse){
            URL urlImageEnn = Main.class.getResource("mitrailleuse.png");
            image = new Image(String.valueOf(urlImageEnn));
            ImageView iv2 = new ImageView(image);
            iv2.translateXProperty().bind(tourelles.xProperty());
            iv2.translateYProperty().bind(tourelles.yProperty());
            this.panneauJeu.getChildren().add(iv2);
        }
        else if (tourelles instanceof TourelleGèle){
            URL urlImageEnn = Main.class.getResource("tourelle-gele.png");
            image = new Image(String.valueOf(urlImageEnn));
            ImageView iv2 = new ImageView(image);
            iv2.translateXProperty().bind(tourelles.xProperty());
            iv2.translateYProperty().bind(tourelles.yProperty());
            this.panneauJeu.getChildren().add(iv2);
        }
        else {
            URL urlImageEnn = Main.class.getResource("tourelleRepousse.png");
            image = new Image(String.valueOf(urlImageEnn));
            ImageView iv2 = new ImageView(image);
            iv2.translateXProperty().bind(tourelles.xProperty());
            iv2.translateYProperty().bind(tourelles.yProperty());
            this.panneauJeu.getChildren().add(iv2);
        }
    }
}
