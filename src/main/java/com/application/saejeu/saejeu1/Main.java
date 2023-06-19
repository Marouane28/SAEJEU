package com.application.saejeu.saejeu1;

import com.application.saejeu.saejeu1.controleur.Music;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;


public class Main extends Application {
    private static Music music; // attribut de music

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Chargement du fichier FXML pour la scène du menu
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menuJeu.fxml"));
        // Création de la scène avec la taille spécifiée
        Scene scene = new Scene(fxmlLoader.load(), 1440, 800);
        // Configuration de la fenêtre principale
        primaryStage.setResizable(false);
        primaryStage.setTitle("Zombie Survival : La Dernière Lueur d'Espoir");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        // Récupération de l'URL de l'image du fond du son
        URL urlImageVaiL = Main.class.getResource("sondFond.wav");
        // Obtention du chemin de l'URL en tant que chaîne de caractères
        String s = urlImageVaiL.getPath();
        // Lecture du fichier audio de fond
        music.PlayMusicFond(s);
        // Lancement de l'application
        launch();
    }


}

