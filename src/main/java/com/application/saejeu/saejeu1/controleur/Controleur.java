package com.application.saejeu.saejeu1.controleur;
import com.application.saejeu.saejeu1.Main;
import javafx.animation.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

import javafx.stage.Stage;
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
    @FXML
    private Label labelVies;
    @FXML
    private Label labelManche;
    @FXML
    private Label labelZombie;
    // permet de definir l'animation
    private Timeline gameLoop;
    private int temps;
    private Environnement environnement;
    private Manche manche;
    private ListChangeListener<Acteur> listenerActeur;
    private ListChangeListener<Tourelle> listenerTourelle;
    private TileMap tileMap;
    @FXML
    private Label labelPieces, labelM, labelG, labelR;
    private final int nb_manche = 10; // permet de définir le nombre de manches
    private boolean estEnPause = false; // gerer les pauses


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            gameLaunch(); // Lance le jeu en initialisant la carte et l'environnement
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        réglerTaille(); // Ajuste la taille du panneau de jeu en fonction de la taille de l'environnement

        // Crée des écouteurs de changement pour les listes d'acteurs et de tourelles de l'environnement
        listenerActeur = new ListObsActeur(panneauDeJeu);
        listenerTourelle = new ListObsTourelle(panneauDeJeu);
        environnement.getActeurs().addListener(listenerActeur);
        environnement.getTourelles().addListener(listenerTourelle);

        initAnimation(); // Initialise l'animation du jeu
        // Démarre l'animation
        gameLoop.play();
    }
    @FXML
    public void abandonnerJeu(ActionEvent actionEvent) {
        afficherGameOverScene();
        gameLoop.stop();
    }
    @FXML
    public void pauseJeu(ActionEvent event) {
        estEnPause = true;
        System.out.println("Vous avez mit pause !");
        gameLoop.pause();
    }
    @FXML
    public void reprendreJeu(ActionEvent event) {
        estEnPause = false;
        System.out.println("Vous avez repris !");
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
    public void réglerTaille() {
        // Définit la taille minimale du panneau de jeu en fonction de la taille de l'environnement
        this.panneauDeJeu.setMinSize(environnement.getX() * 16, environnement.getY() * 16);

        // Définit la taille maximale du panneau de jeu en fonction de la taille de l'environnement
        this.panneauDeJeu.setMaxSize(environnement.getX() * 16, environnement.getY() * 16);

        // Définit la taille préférée du panneau de jeu en fonction de la taille de l'environnement
        this.panneauDeJeu.setPrefSize(environnement.getX() * 16, environnement.getY() * 16);
    }
    public void gameLaunch() throws IOException {

        manche = new Manche(); // Crée une nouvelle instance de la classe Manche
        try {
            // Crée une nouvelle instance de TileMap en utilisant la virgule (",") comme délimiteur et le nom "vraietilemap"
            this.tileMap = new TileMap(",", "vraietilemap");

            // Crée une nouvelle instance d'Environnement en utilisant le TileMap précédemment créé
            this.environnement = new Environnement(this.tileMap);

            // Crée une nouvelle instance de VueTerrain en utilisant l'Environnement, le TilePane et le nom du fichier "tileset1.jpg"
            VueTerrain vueTerrain = new VueTerrain(this.environnement, this.tilePane, "tileset1.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        mettreAJourAffichageVies(environnement.getVies()); // Met à jour l'affichage du nombre de vies
        mettreAJourAffichageManche(manche.getNumeroManche()); // Met à jour l'affichage du numéro de la manche
        mettreAJourAffichageZombies(environnement.getActeurs().size()); // Met à jour l'affichage du nombre de zombies
    }
    public void afficherGameOverScene() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL resource = getClass().getResource("/com/application/saejeu/saejeu1/finJeu.fxml");
        Parent root = null;
        try {
            root = fxmlLoader.load(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return; // Arrêter la méthode si une exception se produit lors du chargement du fichier FXML
        }
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) ((Node) panneauDeJeu).getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void afficherWinJeuScene() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/application/saejeu/saejeu1/winJeu.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return; // Arrêter la méthode si une exception se produit lors du chargement du fichier FXML
        }
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) ((Node) panneauDeJeu).getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void mettreAJourAffichageZombies(int zombies) {

        IntegerProperty zProperty = new SimpleIntegerProperty();
        zProperty.set(zombies);
        this.labelZombie.textProperty().bind(zProperty.asString());
    }
    public void mettreAJourAffichageVies(int vies) {

        IntegerProperty vProperty = new SimpleIntegerProperty();
        vProperty.set(vies);
        this.labelVies.textProperty().bind(vProperty.asString());
    }
    public void mettreAJourAffichageManche(int numeroManche) {

        IntegerProperty mProperty = new SimpleIntegerProperty();
        mProperty.set(numeroManche);
        this.labelManche.textProperty().bind(mProperty.asString());
    }
    private int changerStringLabelEnInt (String input) {
        String numberString = input.replaceAll("[^0-9]", ""); // Supprime tous les caractères non numériques
        return Integer.parseInt(numberString);
    }

    private Timeline initAnimation() {
        gameLoop = new Timeline();
        temps = 0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        TourManager tourManager = new TourManager(environnement, manche, nb_manche, this, gameLoop);

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.08),
                (ev -> {
                    if (temps % 10 == 0 && manche.getNombreZombies() != manche.getCompteurZombie()) {
                        tourManager.ajouterZombie(); // Ajoute un zombie toutes les 10 unités de temps si le nombre de zombies ajoutés est inférieur au nombre total de zombies de la manche
                        this.labelPieces.textProperty().bind(this.environnement.getPropertyPièces().asString());
                    } else if (temps % 2 == 0) {
                        tourManager.effectuerTour(); // Effectue un tour toutes les 2 unités de temps
                    }

                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);

        return gameLoop;
    }

}