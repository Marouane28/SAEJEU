package com.application.saejeu.saejeu1.controleur;
import javafx.animation.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
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
    private ListChangeListener<Acteur> listenerActeur;
    private ListChangeListener<Tourelle> listenerTourelle;
    private TileMap tileMap;
    @FXML
    private Label labelPieces, labelM, labelG, labelR;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gameLaunch();
        réglerTaille();

        listenerActeur = new ListObsActeur(panneauDeJeu);
        listenerTourelle = new ListObsTourelle(panneauDeJeu);
        environnement.getActeurs().addListener(listenerActeur);
        environnement.getTourelles().addListener(listenerTourelle);

        initAnimation();
        // Démarrer l'animation
        gameLoop.play();
    }

    @FXML
    private void ajouterTourelleG() {

        // Ici, vous pouvez implémenter la logique pour ajouter une tourelle
        // lorsque le bouton est cliqué
        // Par exemple, vous pouvez activer l'écouteur de clic sur la zone de jeu.
        panneauDeJeu.setOnMouseClicked(mouseEvent -> {
            // Obtenir les coordonnées de la souris
            int mouseX = (int) mouseEvent.getX() - ((int) mouseEvent.getX() % this.tileMap.getTileSize());
            int mouseY = (int) mouseEvent.getY() - ((int) mouseEvent.getY() % this.tileMap.getTileSize());
            // Condition pour ne pas la placer sur le chemin des zombies
            int caseX = mouseX / this.tileMap.getTileSize();
            int caseY = mouseY / this.tileMap.getTileSize();

            boolean tourelleIci = false;
            for (int i = 0; i < this.environnement.getTourelles().size(); i++) {

                if (this.environnement.getTourelles().get(i).getX() == (mouseX) && this.environnement.getTourelles().get(i).getY() == (mouseY)) {

                    tourelleIci = true;
                }
            }
            // Si condition remplie
            if (!tourelleIci && !this.tileMap.isNotObstacle(caseX, caseY) && this.environnement.getPièces() >= this.changerStringLabelEnInt(String.valueOf(this.labelG))) {
                // Créer une nouvelle tourelle à la position de la souris si condition remplie
                Tourelle nouvelleTourelle = new TourelleGèle(mouseX, mouseY, this.environnement);
                // Ajouter la tourelle à votre environnement ou à une liste de tourelles
                this.environnement.ajouterTourelle(nouvelleTourelle);
                this.environnement.gagnerUnCertainNombreDePièce(-nouvelleTourelle.getCoût());
            }
            this.panneauDeJeu.setOnMouseClicked(null);
        });
    }
    @FXML
    private void ajouterTourelleR() {
        // Ici, vous pouvez implémenter la logique pour ajouter une tourelle
        // lorsque le bouton est cliqué
        // Par exemple, vous pouvez activer l'écouteur de clic sur la zone de jeu
        panneauDeJeu.setOnMouseClicked(mouseEvent -> {
            // Obtenir les coordonnées de la souris
            int mouseX = (int) mouseEvent.getX() - ((int) mouseEvent.getX() % this.tileMap.getTileSize());
            int mouseY = (int) mouseEvent.getY() - ((int) mouseEvent.getY() % this.tileMap.getTileSize());
            // Condition pour ne pas la placer sur le chemin des zombies
            int caseX = mouseX / this.tileMap.getTileSize();
            int caseY = mouseY / this.tileMap.getTileSize();

            boolean tourelleIci = false;
            for (int i = 0; i < this.environnement.getTourelles().size(); i++) {

                if (this.environnement.getTourelles().get(i).getX() == (mouseX) && this.environnement.getTourelles().get(i).getY() == (mouseY)) {

                    tourelleIci = true;
                }
            }
            // Si condition remplie
            if (!tourelleIci && !this.tileMap.isNotObstacle(caseX, caseY) && this.environnement.getPièces() >= this.changerStringLabelEnInt(String.valueOf(this.labelR))) {
                // Créer une nouvelle tourelle à la position de la souris si condition remplie
                Tourelle nouvelleTourelle = new TourelleRepousse(mouseX, mouseY, this.environnement);
                // Ajouter la tourelle à votre environnement ou à une liste de tourelles
                this.environnement.ajouterTourelle(nouvelleTourelle);
                this.environnement.gagnerUnCertainNombreDePièce(-nouvelleTourelle.getCoût());
            }
            this.panneauDeJeu.setOnMouseClicked(null);
        });
    }
    @FXML
    private void ajouterTourelleM() {
        // Ici, vous pouvez implémenter la logique pour ajouter une tourelle
        // lorsque le bouton est cliqué
        // Par exemple, vous pouvez activer l'écouteur de clic sur la zone de jeu
        panneauDeJeu.setOnMouseClicked(mouseEvent -> {
            // Obtenir les coordonnées de la souris
            int mouseX = (int) mouseEvent.getX() - ((int) mouseEvent.getX() % this.tileMap.getTileSize());
            int mouseY = (int) mouseEvent.getY() - ((int) mouseEvent.getY() % this.tileMap.getTileSize());
            // Condition pour ne pas la placer sur le chemin des zombies
            int caseX = mouseX / this.tileMap.getTileSize();
            int caseY = mouseY / this.tileMap.getTileSize();

            boolean tourelleIci = false;
            for (int i = 0; i < this.environnement.getTourelles().size(); i++) {

                if (this.environnement.getTourelles().get(i).getX() == (mouseX) && this.environnement.getTourelles().get(i).getY() == (mouseY)) {

                    tourelleIci = true;
                }
            }
            // Si condition remplie
            if (!tourelleIci && !this.tileMap.isNotObstacle(caseX, caseY) && this.environnement.getPièces() >= this.changerStringLabelEnInt(String.valueOf(this.labelM))) {
                // Créer une nouvelle tourelle à la position de la souris si condition remplie
                Tourelle nouvelleTourelle = new TourelleMitrailleuse(mouseX, mouseY, this.environnement);
                // Ajouter la tourelle à votre environnement ou à une liste de tourelles
                this.environnement.ajouterTourelle(nouvelleTourelle);
                this.environnement.gagnerUnCertainNombreDePièce(-nouvelleTourelle.getCoût());
            }
            this.panneauDeJeu.setOnMouseClicked(null);
        });
    }

    public void réglerTaille(){
        this.panneauDeJeu.setMinSize(environnement.getX() * 16, environnement.getY() * 16);
        this.panneauDeJeu.setMaxSize(environnement.getX() * 16, environnement.getY() * 16);
        this.panneauDeJeu.setPrefSize(environnement.getX() * 16, environnement.getY() * 16);
    }

    public void gameLaunch() {
        try {
            this.tileMap = new TileMap(",","vraietilemap");
            this.environnement=new Environnement(this.tileMap);
            VueTerrain vueTerrain = new VueTerrain(this.environnement, this.tilePane,"tileset1.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initAnimation() {
        gameLoop = new Timeline();
        temps = 0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.30),
                (ev -> {
                    if (temps % 10 == 0){
                        System.out.println("Ajout zombie");
                        environnement.créerZombie();
                        this.environnement.gagnerUnCertainNombreDePièce(100);
                        this.labelPieces.textProperty().bind(this.environnement.getPropertyPièces().asString());


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
                            ArrayList<Tourelle> tourCopy = new ArrayList<>(environnement.getTourelles());
                            for (Tourelle tour : tourCopy) {
                                tour.setCible(zombie);
                                tour.attaquer();
                                System.out.println("pv :"+tour.getPv());
                                //vérifie si la tourelle ne marche plus
                                if (!tour.estEnMarche()) {
                                    environnement.getTourelles().remove(tour);
                                }
                            }
                            //verifie si le zombie est mort
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
    private int changerStringLabelEnInt (String input) {

        String numberString = input.replaceAll("[^0-9]", ""); // Supprime tous les caractères non numériques
        return Integer.parseInt(numberString);
    }

}