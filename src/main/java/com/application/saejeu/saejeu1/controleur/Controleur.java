package com.application.saejeu.saejeu1.controleur;
import javafx.animation.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.util.Duration;
import com.application.saejeu.saejeu1.modele.*;
import com.application.saejeu.saejeu1.vue.VueEnnemi;
import com.application.saejeu.saejeu1.vue.VueTerrain;
import com.application.saejeu.saejeu1.vue.VueTourelle;

public class Controleur implements Initializable {

    @FXML
    private TilePane tilePane; // le terrain
    @FXML
    private Pane panneauDeJeu;
    private ObservableList<Acteur> zombies; // définit un ennemi
    private ObservableList<Tourelle> tours; // définit une tour

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

    ListChangeListener<Acteur> listenerActeur;
    ListChangeListener<Tourelle> listenerTourelle;

    Sommet source, cible;

    ArrayList<Sommet> chemin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        zombies = FXCollections.observableArrayList();
        tours = FXCollections.observableArrayList();
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

        Acteur z = environnement.créerZombie();
        vueEnnemi = new VueEnnemi(panneauDeJeu, z);

//        Tourelle t = créerTourelle(2);
//        vueTourelle = new VueTourelle(panneauDeJeu,t);

        Tourelle t = new TourelleMitrailleuse(20*16,15*16,environnement);
        environnement.ajouterTourelle(t);
        tours.add(t);

        vueTourelle = new VueTourelle(panneauDeJeu, t);


        bfs();
        listenerActeur = new ListObsActeur(panneauDeJeu);
        listenerTourelle = new ListObsTourelle(panneauDeJeu);
        environnement.getActeurs().addListener(listenerActeur);
        environnement.getTourelles().addListener(listenerTourelle);
        coordoneeGetCoordSouris();

        gameLaunche();

        initAnimation();
        // Démarrer l'animation
        gameLoop.play();
    }

    public void coordoneeGetCoordSouris() {
        tilePane.setOnMousePressed(mouseEvent -> {
            System.out.println("x " + mouseEvent.getX() + " Y " + mouseEvent.getY() + " id " + environnement.getTileMap()[(int) mouseEvent.getX() / 16][(int) mouseEvent.getY() / 16]);
        });
    }
    public void bfs(){
        BFS bfs;
        source = environnement.getSommet(0, 20);

        System.out.println("source sommet " + source);

        System.out.println("poid de sommet source " + source.getPoids());


        cible = environnement.getSommet(89, 34);
//        Circle circle = new Circle(89 * 16, 34 * 16, 10, Color.BLACK);
//        panneauDeJeu.getChildren().add(circle);

        System.out.println("sommet cible poid " + cible.getPoids());

        bfs = new BFS(environnement, source);

        this.chemin = bfs.cheminVersSource(cible);

        System.out.println("longeur chemin " + chemin);

//        for (Sommet s : chemin) {
//            PaneauDeJeu.getChildren().add(new Circle(s.getX() * 16, s.getY() * 16, 5, Color.RED));
//        }
    }



//        public Tourelle créerTourelle(int nbTourelle){
//        Tourelle tourelle = null;
//        for (int i = 0 ; i <= nbTourelle ; i++){
//            Random rand = new Random();
//            int nb = rand.nextInt(3 - 1 + 1) + 1;
//            int x = rand.nextInt(environnement.getX() + 1) + 1;
//            int y = rand.nextInt(environnement.getY() + 1) + 1;
//
//            if (nb == 1){
//                Tourelle t1 = new TourelleMitrailleuse(x*16,y*16,environnement);
//                environnement.ajouterTourelle(t1);
//                tours.add(t1);
//            }
//            else if (nb == 2){
//                Tourelle t1 = new TourelleRepousse(x*16,y*16,environnement);
//                environnement.ajouterTourelle(t1);
//                tours.add(t1);
//            }
//            else {
//                Tourelle t1 = new TourelleGèle(x*16,y*16,environnement);
//                environnement.ajouterTourelle(t1);
//                tours.add(t1);
//            }
//        }
//        return tourelle;
//    }


    public void reglerTaille(){
        this.tilePane.setMinSize(environnement.getX() * 16, environnement.getY() * 16);
        this.tilePane.setMaxSize(environnement.getX() * 16, environnement.getY() * 16);
        this.tilePane.setPrefSize(environnement.getX() * 16, environnement.getY() * 16);
        this.panneauDeJeu.setMinSize(environnement.getX() * 16, environnement.getY() * 16);
        this.panneauDeJeu.setMaxSize(environnement.getX() * 16, environnement.getY() * 16);
        this.panneauDeJeu.setPrefSize(environnement.getX() * 16, environnement.getY() * 16);
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
//        AtomicInteger k = new AtomicInteger();


        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.17),

                (ev -> {
                    if (temps % 10 == 0){
                        Sommet sommet = chemin.get(i.getAndIncrement());
                        System.out.println("changement dans controleur");
                        Acteur zombie = environnement.créerZombie();
                        zombies.add(zombie);
                        environnement.ajouterActeur(zombie);
//                        zombie.setX(sommet.getX() * 16);
//                        zombie.setY(sommet.getY() * 16);
                    }
                    if (temps == 10000) {
                        gameLoop.stop();
                    } else if (temps % 2 == 0) {
                        System.out.println("un tour");

                        for (Acteur zombie : zombies) {
                            Sommet sommet = chemin.get(i.getAndIncrement());
                            zombie.setX(sommet.getX() * 16);
                            zombie.setY(sommet.getY() * 16);
                            for (Tourelle tour : tours){
                                tour.setCible(zombie);
                                tour.attaquer();
                            }
                           if (!zombie.estVivant()){
                               vueEnnemi.supprimerZombieMort(zombie);
                           }

                            //System.out.println(sommet);
                            if (sommet.getY() == cible.getY() && sommet.getX() == cible.getX()) {
                                System.out.println("Vous avez perdu !");
                                gameLoop.stop();
                            }
                        }
                    }


                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }

}