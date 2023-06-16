package com.application.saejeu.saejeu1.controleur;
import com.application.saejeu.saejeu1.Main;
import com.application.saejeu.saejeu1.modele.Tourelle.Tourelle;
import com.application.saejeu.saejeu1.modele.Tourelle.TourelleGèle;
import com.application.saejeu.saejeu1.modele.Tourelle.TourelleMitrailleuse;
import com.application.saejeu.saejeu1.modele.Tourelle.TourelleRepousse;
import com.application.saejeu.saejeu1.modele.Zombie.Acteur;
import javafx.animation.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.stage.Stage;
import javafx.util.Duration;
import com.application.saejeu.saejeu1.modele.*;
import com.application.saejeu.saejeu1.vue.VueTerrain;

public class Controleur implements Initializable {

    @FXML
    private TilePane tilePane; // Représente le terrain de jeu (la grille)
    @FXML
    private Pane panneauDeJeu; // Représente le panneau de jeu principal
    @FXML
    private Label labelManche, labelZombie, labelVies, nbTourelles, labelCoutAm; // Labels pour afficher les informations du jeu
    @FXML
    private Label labelPieces, labelM, labelG, labelR; // Labels pour afficher les informations de ressources
    private Timeline gameLoop; // Boucle de jeu pour les mises à jour périodiques
    private int temps; // Temps écoulé dans le jeu
    private Environnement environnement; // Référence à l'environnement du jeu
    private Manche manche; // Référence à la manche en cours
    private ListChangeListener<Acteur> listenerActeur; // Écouteur de changements pour les acteurs (zombies, tourelles, etc.)
    private ListChangeListener<Tourelle> listenerTourelle; // Écouteur de changements pour les tourelles
    private ListChangeListener<Pièce> listenerPièce;
    private ListChangeListener<Projectile> listenerProjectile;
    private TileMap tileMap; // Représente la carte de tuiles du terrain
    private final int nb_manche = 10; // Permet de définir le nombre de manches dans le jeu
    private boolean estEnPause = false; // Indique si le jeu est en pause ou non
    private static Music music; // attribut de music


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            gameLaunch(); // Lance le jeu en initialisant la carte et l'environnement
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        réglerTaille(); // Ajuste la taille du panneau de jeu en fonction de la taille de l'environnement

        // Crée des écouteurs de changement pour les listes d'acteurs et de tourelles de l'environnement
        this.listenerActeur = new ListObsActeur(this.panneauDeJeu);
        this.listenerTourelle = new ListObsTourelle(this.panneauDeJeu);
        this.listenerPièce = new ListObsPiece(this.panneauDeJeu);
        this.listenerProjectile = new ListObsProjectile(panneauDeJeu);
        this.environnement.getActeurs().addListener(this.listenerActeur);
        this.environnement.getTourelles().addListener(this.listenerTourelle);
        this.environnement.getListePièces().addListener(this.listenerPièce);
        this.environnement.getProjectiles().addListener(listenerProjectile);

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

            // Si conditions remplies
            if (mouseY >= 64 && !this.environnement.emplacementDéjàPrisParUneTourelle(mouseX, mouseY) && !this.tileMap.isNotObstacle(caseX, caseY) && this.environnement.getPièces() >= Integer.parseInt(this.labelG.getText())) {
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

            // Si conditions remplies
            if (mouseY >= 64 && !this.environnement.emplacementDéjàPrisParUneTourelle(mouseX, mouseY) && !this.tileMap.isNotObstacle(caseX, caseY) && this.environnement.getPièces() >= Integer.parseInt(this.labelR.getText())) {
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

            // Si conditions remplies
            if (mouseY >= 64 && !this.environnement.emplacementDéjàPrisParUneTourelle(mouseX, mouseY) && !this.tileMap.isNotObstacle(caseX, caseY) && this.environnement.getPièces() >= Integer.parseInt(this.labelM.getText())) {
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

        bindAffichageVies(); // Met à jour l'affichage du nombre de vies
        bindAffichageManche(); // Met à jour l'affichage du numéro de la manche
        mettreAJourAffichageZombies(environnement.getActeurs().size()); // Met à jour l'affichage du nombre de zombies
        bindAffichagePiece(); // Met à jour l'affichage des pièces
        mettreAJourAffichagePrixTourelles(); // Appel d'une méthode pour mettre à jour l'affichage des prix des tourelles
        mettreAJourAffichageTourelles(this.environnement.getTourelles().size()); // Appel d'une méthode pour mettre à jour l'affichage des tourelles en fonction de leur nombre
        mettreAJourCoûtAmélioration(); // Appel d'une méthode pour mettre à jour le coût d'amélioration
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
        // Arreter la musique en cours (si elle est en cours de lecture)
        music.stopMusicFond();
        // Lancer la musique de la défaite
        URL urlImageVaiL = Main.class.getResource("sonGameOver.wav");
        String s = urlImageVaiL.getPath();
        music.PlayMusicDefaite(s);
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
        // Arreter la musique en cours (si elle est en cours de lecture)
        music.stopMusicFond();
        // Lancer la musique de victoire
        URL urlImageVaiL = Main.class.getResource("sondVictoire.wav");
        String s = urlImageVaiL.getPath();
        music.PlayMusicVictoire(s);
    }
    public void bindAffichagePiece() {
        this.labelPieces.textProperty().bind(this.environnement.getPropertyPièces().asString());
    }
    public void bindAffichageVies() {
        this.labelVies.textProperty().bind(environnement.viesProperty().asString());
    }
    public void bindAffichageManche() {
        this.labelManche.textProperty().bind(manche.numeroMancheProperty().asString());
    }
    public int getTemps() {
        return this.temps;
    }

    public void mettreAJourCoûtAmélioration() {
        Tourelle m = new TourelleMitrailleuse(0, 0, this.environnement);
        this.labelCoutAm.textProperty().bind(m.getCoûtAmProperty().asString());
    }
    public void mettreAJourAffichageZombies(int zombies) {
        IntegerProperty zProperty = new SimpleIntegerProperty(zombies);
        this.labelZombie.textProperty().bind(zProperty.asString());
    }
    public void mettreAJourAffichageTourelles(int tourelles) {
        IntegerProperty tProperty = new SimpleIntegerProperty(tourelles);
        this.nbTourelles.textProperty().bind(tProperty.asString());
    }
    public void mettreAJourAffichagePrixTourelles() {
        Tourelle m = new TourelleMitrailleuse(0, 0, this.environnement);
        this.labelM.textProperty().bind(m.getCoûtProperty().asString());
        Tourelle g = new TourelleGèle(0, 0, this.environnement);
        this.labelG.textProperty().bind(g.getCoûtProperty().asString());
        Tourelle r = new TourelleRepousse(0, 0, this.environnement);
        this.labelR.textProperty().bind(r.getCoûtProperty().asString());
    }
    private void initAnimation() {
        gameLoop = new Timeline();
        temps = 0;
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        TourManager tourManager = new TourManager(environnement, manche, nb_manche, this, gameLoop);

        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.08),
                (ev -> {
                    if (temps % 10 == 0 && manche.getNombreZombies() != manche.getCompteurZombie()) {
                        tourManager.ajouterZombie(); // Ajoute un zombie toutes les 10 unités de temps si le nombre de zombies ajoutés est inférieur au nombre total de zombies de la manche
                    } else if (temps % 2 == 0) {
                        if(music.verifSon()==false){
                            // Lancer la musique du jeu si le son n'est plus lancé
                            URL urlImageVaiL = Main.class.getResource("sondFond.wav");
                            String s = urlImageVaiL.getPath();
                            music.PlayMusicFond(s);
                        }
                        tourManager.effectuerTour(); // Effectue un tour toutes les 2 unités de temps
                    }
                    temps++;
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }
}