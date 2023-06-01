package com.application.saejeu.saejeu1.controleur;
import javafx.animation.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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

    // permet de definir l'animation
    private Timeline gameLoop;
    private int temps;
    private Environnement environnement;
    ListChangeListener<Acteur> listenerActeur;
    ListChangeListener<Tourelle> listenerTourelle;
    @FXML
    private TextField textFieldX,textFieldY;
    private TileMap tileMap;
    private VueTerrain vueTerrain;

    private VueTourelle vueTourelle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gameLaunche();
        reglerTaille();

        listenerActeur = new ListObsActeur(panneauDeJeu);
        listenerTourelle = new ListObsTourelle(panneauDeJeu);
        environnement.getActeurs().addListener(listenerActeur);
        environnement.getTourelles().addListener(listenerTourelle);

        coordonnéeGetCoordSouris();
        initAnimation();
        // Démarrer l'animation
        gameLoop.play();
    }

    public void coordonnéeGetCoordSouris() {
        tilePane.setOnMousePressed(mouseEvent -> {
            //System.out.println("x " + mouseEvent.getX() + " Y " + mouseEvent.getY() + " id " + environnement.getTileMap()[(int) mouseEvent.getX() / 16][(int) mouseEvent.getY() / 16]);
            this.textFieldX.setText(String.valueOf(mouseEvent.getX()));
            this.textFieldY.setText(String.valueOf(mouseEvent.getY()));
        });
    }
    public void addGel(ActionEvent action) {

        double x = Double.parseDouble(this.textFieldX.getText());
        double y = Double.parseDouble(this.textFieldY.getText());

        double posXModulo16 = x - (x % this.tileMap.getTileSize());
        double posYModulo16 = y - (y % this.tileMap.getTileSize());

        int caseX = (int) (posXModulo16) / this.tileMap.getTileSize();
        int caseY = (int) (posYModulo16) / this.tileMap.getTileSize();

        boolean tourelleIci = false;
        for (int i = 0; i < this.environnement.getTourelles().size(); i++) {

            if (this.environnement.getTourelles().get(i).getX() == (posXModulo16) && this.environnement.getTourelles().get(i).getY() == (posYModulo16)) {

                tourelleIci = true;
            }
        }
        Tourelle t;
        if (!tourelleIci) {

            if (!this.tileMap.isNotObstacle(caseX, caseY)) {

                t = new TourelleGèle((int) posXModulo16, (int) posYModulo16, this.environnement);
                this.environnement.ajouterTourelle(t);
                this.vueTourelle = new VueTourelle(this.panneauDeJeu, t);
                System.out.println(this.environnement.getTourelles().toString());
            }
        }
    }
    public void addRepousse(ActionEvent action) {

        double x = Double.parseDouble(this.textFieldX.getText());
        double y = Double.parseDouble(this.textFieldY.getText());

        double posXModulo16 = x - (x % this.tileMap.getTileSize());
        double posYModulo16 = y - (y % this.tileMap.getTileSize());

        int caseX = (int) (posXModulo16) / this.tileMap.getTileSize();
        int caseY = (int) (posYModulo16) / this.tileMap.getTileSize();

        boolean tourelleIci = false;
        for (int i = 0; i < this.environnement.getTourelles().size(); i++) {

            if (this.environnement.getTourelles().get(i).getX() == (posXModulo16) && this.environnement.getTourelles().get(i).getY() == (posYModulo16)) {

                tourelleIci = true;
            }
        }
        Tourelle t;
        if (!tourelleIci) {

            if (!this.tileMap.isNotObstacle(caseX, caseY)) {

                t = new TourelleRepousse((int) posXModulo16, (int) posYModulo16, this.environnement);
                this.environnement.ajouterTourelle(t);
                this.vueTourelle = new VueTourelle(this.panneauDeJeu, t);
                System.out.println(this.environnement.getTourelles().toString());
            }
        }
    }
    public void addMitrailleuse(ActionEvent action) {

        double x = Double.parseDouble(this.textFieldX.getText());
        double y = Double.parseDouble(this.textFieldY.getText());

        double posXModulo16 = x - (x % this.tileMap.getTileSize());
        double posYModulo16 = y - (y % this.tileMap.getTileSize());

        int caseX = (int) (posXModulo16) / this.tileMap.getTileSize();
        int caseY = (int) (posYModulo16) / this.tileMap.getTileSize();

        boolean tourelleIci = false;
        for (int i = 0; i < this.environnement.getTourelles().size(); i++) {

            if (this.environnement.getTourelles().get(i).getX() == (posXModulo16) && this.environnement.getTourelles().get(i).getY() == (posYModulo16)) {

                tourelleIci = true;
            }
        }
        Tourelle t;
        if (!tourelleIci) {

            if (!this.tileMap.isNotObstacle(caseX, caseY)) {

                t = new TourelleMitrailleuse((int) posXModulo16, (int) posYModulo16, this.environnement);
                this.environnement.ajouterTourelle(t);
                this.vueTourelle = new VueTourelle(this.panneauDeJeu, t);
                System.out.println(this.environnement.getTourelles().toString());
            }
        }
    }

    public void reglerTaille(){
        this.panneauDeJeu.setMinSize(environnement.getX() * 16, environnement.getY() * 16);
        this.panneauDeJeu.setMaxSize(environnement.getX() * 16, environnement.getY() * 16);
        this.panneauDeJeu.setPrefSize(environnement.getX() * 16, environnement.getY() * 16);
    }

    public void gameLaunche() {
        try {
            this.tileMap = new TileMap(",","vraietilemap");
            this.environnement=new Environnement(this.tileMap);
            this.vueTerrain = new VueTerrain(this.environnement, this.tilePane,"tileset1.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initAnimation() {
        gameLoop = new Timeline();
        temps = 0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.17),
                (ev -> {
                    if (temps % 10 == 0){
                        System.out.println("Ajout zombie");
                        environnement.créerZombie();

                    }
                    else if (temps % 2 == 0) {
                        System.out.println("un tour");
                        ArrayList<Acteur> acteursCopy = new ArrayList<>(environnement.getActeurs());
                        for (Acteur zombie : acteursCopy) {
                            // Vérifier si l'ennemi est gelé
                            if (zombie.getCyclesRestants() == 0) {
                                zombie.deplacement();
                            } else {
                                zombie.decrementerCyclesRestants();
                            }
                            for (Tourelle tour : this.environnement.getTourelles()){
                                tour.setCible(zombie);
                                tour.attaquer();
                                System.out.println(zombie.estVivant());
                            }

                            if (!zombie.estVivant()) {
                               environnement.getActeurs().remove(zombie);
                            }

                            //System.out.println(sommet);
                            if (zombie.getY() == 34*16 && zombie.getX() == 89*16) {
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