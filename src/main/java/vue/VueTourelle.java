package vue;

import com.application.saejeu.saejeu1.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import modele.Tourelle;
import modele.TourelleGèle;
import modele.TourelleMitrailleuse;

import java.net.URL;
import java.util.ArrayList;

public class VueTourelle {
    private Pane panneauJeu;
    private ArrayList<Tourelle> tourelles;

    public VueTourelle(Pane panneauJeu, ArrayList<Tourelle> tourelles) {
        this.panneauJeu = panneauJeu;
        this.tourelles = tourelles;

        for (Tourelle tourelle : this.tourelles) {
            ImageView imageView;

            if (tourelle instanceof TourelleGèle) {
                URL urlImageGele = Main.class.getResource("tourelle-gele.png");
                Image imageTourelleGele = new Image(String.valueOf(urlImageGele));
                imageView = new ImageView(imageTourelleGele);
            } else if(tourelle instanceof TourelleMitrailleuse){
                URL urlImageEnn = Main.class.getResource("mitrailleuse.png");
                Image imageTourelle = new Image(String.valueOf(urlImageEnn));
                imageView = new ImageView(imageTourelle);
            }else{
                URL urlImageEnn = Main.class.getResource("tourelleRepousse.png");
                Image imageTourelle = new Image(String.valueOf(urlImageEnn));
                imageView = new ImageView(imageTourelle);
            }

            imageView.translateXProperty().bind(tourelle.xProperty());
            imageView.translateYProperty().bind(tourelle.yProperty());
            this.panneauJeu.getChildren().add(imageView);
        }
    }
}
