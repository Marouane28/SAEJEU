package com.application.saejeu.saejeu1.controleur;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import com.application.saejeu.saejeu1.modele.*;
import com.application.saejeu.saejeu1.vue.VueEnnemi;
import com.application.saejeu.saejeu1.vue.VueTerrain;
import com.application.saejeu.saejeu1.vue.VueTourelle;

public class Controleur implements Initializable {

    @FXML
    private TilePane tilePane; // le terrain
    @FXML
    private Pane PaneauDeJeu;
    private Acteur zombie; // définit un ennemi
    private Tourelle tourelle;

    // permet de definir l'animation
    private Timeline gameLoop;
    private int temps;
    private TourelleMitrailleuse tourelleMitrailleuse;
    private TourelleGèle tourelleGèle;
    private TourelleRepousse tourelleRepousse;

    private VueTourelle vueTourelle; // com.application.saejeu.saejeu1.vue tourelle
    private VueTerrain vueTerrain; // com.application.saejeu.saejeu1.vue terrain
    private VueEnnemi vueEnnemi; // com.application.saejeu.saejeu1.vue ennemi
    private Environnement environnement;


    Sommet source, cible;

    ArrayList<Sommet> chemin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            environnement = new Environnement(90, 90);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            environnement.readMap();
        } catch (IOException e) {
            e.printStackTrace();
        }

        reglerTaille();

        zombie = new ZombieRapide(16, environnement, 16);
        environnement.ajouterActeur(zombie);
        vueEnnemi = new VueEnnemi(PaneauDeJeu,environnement.getActeurs());

        tourelleMitrailleuse = new TourelleMitrailleuse(57*16,20*16,15*16,50,10,environnement);
        tourelleMitrailleuse.setCible(zombie);
        environnement.ajouterTourelle(tourelleMitrailleuse);

       tourelleGèle= new TourelleGèle(25*16,18*16,10*16,50,0,environnement);
       tourelleGèle.setCible(zombie);
       environnement.ajouterTourelle(tourelleGèle);

        tourelleRepousse= new TourelleRepousse(17*16,14*16,5*16,50,0,environnement);
        tourelleRepousse.setCible(zombie);
        environnement.ajouterTourelle(tourelleRepousse);

        vueTourelle = new VueTourelle(PaneauDeJeu,environnement.getTourelles());

        bfs();
        gameLaunche();
        initAnimation();
        // Démarrer l'animation
        gameLoop.play();
    }


    public void bfs(){
        BFS bfs;
        source = environnement.getSommet(0, 20);

        System.out.println("source sommet " + source);

        System.out.println("poid de sommet source " + source.getPoids());


        cible = environnement.getSommet(89, 34);
        Circle circle = new Circle(89 * 16, 34 * 16, 10, Color.BLACK);
        PaneauDeJeu.getChildren().add(circle);

        System.out.println("sommet cible poid " + cible.getPoids());

        bfs = new BFS(environnement, source);

        this.chemin = bfs.cheminVersSource(cible);

        System.out.println("longeur chemin " + chemin);

//        for (Sommet s : chemin) {
//            PaneauDeJeu.getChildren().add(new Circle(s.getX() * 16, s.getY() * 16, 5, Color.RED));
//        }
    }

    public void reglerTaille(){
        this.tilePane.setMinSize(environnement.getX() * 16, environnement.getY() * 16);
        this.tilePane.setMaxSize(environnement.getX() * 16, environnement.getY() * 16);
        this.tilePane.setPrefSize(environnement.getX() * 16, environnement.getY() * 16);
        this.PaneauDeJeu.setMinSize(environnement.getX() * 16, environnement.getY() * 16);
        this.PaneauDeJeu.setMaxSize(environnement.getX() * 16, environnement.getY() * 16);
        this.PaneauDeJeu.setPrefSize(environnement.getX() * 16, environnement.getY() * 16);
    }

    public void gameLaunche() {
        try {
            this.vueTerrain = new VueTerrain(environnement, tilePane);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initAnimation() {
        gameLoop = new Timeline();
        temps = 0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        AtomicInteger i = new AtomicInteger();
        AtomicInteger k = new AtomicInteger();

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.1),

                (ev -> {
                    if (temps == 10000) {
                        gameLoop.stop();
                    } else if (temps % 2 == 0) {
                        System.out.println("un tour");
                        Sommet sommet = chemin.get(i.getAndIncrement());
                        zombie.setX(sommet.getX() * 16);
                        zombie.setY(sommet.getY() * 16);
                        tourelleGèle.attaquer();
                        tourelleMitrailleuse.attaquer();
                        tourelleRepousse.attaquer();
                        System.out.println(zombie.getVitesse());

                            // verifie si le zombie est mort, si il est mort on l'enleve de la map
                        if (!zombie.estVivant()) {
                            for (ImageView imageView : vueEnnemi.getImageViews()) {
                                if (imageView.translateXProperty().isBound() && imageView.translateXProperty().get() == zombie.getX() &&
                                        imageView.translateYProperty().isBound() && imageView.translateYProperty().get() == zombie.getY()) {
                                    vueEnnemi.removeImageView(imageView);
                                    break;
                                }
                            }
                            gameLoop.stop();
                        }

                        // Vérifier si le zombie a atteint la cible
                        if (sommet.getY() == cible.getY() && sommet.getX() == cible.getX()) {
                            System.out.println("Vous avez perdu !");
                            gameLoop.stop();
                        }
                    }
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

}