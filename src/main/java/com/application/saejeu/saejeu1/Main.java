package com.application.saejeu.saejeu1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import javax.sound.sampled.*;
import java.io.*;
import java.net.URL;


public class Main extends Application {
    private static Clip clipFond;      // Variable pour la musique de fond
    private static Clip clipVictoire;  // Variable pour la musique de victoire
    private static Clip clipDefaite;   // Variable pour la musique de défaite

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

    public static void main(String[] args){
        URL urlImageVaiL = Main.class.getResource("sondFond.wav");
        String s = urlImageVaiL.getPath();
        PlayMusicFond(s);
        launch();
    }
    public static void PlayMusicFond(String location){
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(location));
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DataLine.Info info = new DataLine.Info(Clip.class, audioInputStream.getFormat());
        try {
            clipFond = (Clip) AudioSystem.getLine(info);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        try {
            clipFond.open(audioInputStream);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        clipFond.start();
    }

    public static boolean verifSon() {
        // Vérification si le clip de musique de fond est en cours de lecture
        if (!clipFond.isRunning()) {
            return false;
        }
        return true;
    }

    public static void stopMusicFond() {
        // Arrêt et fermeture du clip de musique de fond s'il est en cours de lecture
        if (clipFond != null && clipFond.isRunning()) {
            clipFond.stop();
            clipFond.close();
        }
    }

    public static void PlayMusicVictoire(String location){
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(location));
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DataLine.Info info = new DataLine.Info(Clip.class, audioInputStream.getFormat());
        try {
            clipVictoire = (Clip) AudioSystem.getLine(info);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        try {
            clipVictoire.open(audioInputStream);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        clipVictoire.start();
    }

    public static void stopMusicVictoire() {
        // Arrêt et fermeture du clip de musique de victoire s'il est en cours de lecture
        if (clipVictoire != null && clipVictoire.isRunning()) {
            clipVictoire.stop();
            clipVictoire.close();
        }
    }

    public static void PlayMusicDefaite(String location){
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(location));
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DataLine.Info info = new DataLine.Info(Clip.class, audioInputStream.getFormat());
        try {
            clipDefaite = (Clip) AudioSystem.getLine(info);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        try {
            clipDefaite.open(audioInputStream);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        clipDefaite.start();
    }

    public static void stopMusicDefaite() {
        // Arrêt et fermeture du clip de musique de défaite s'il est en cours de lecture
        if (clipDefaite != null && clipDefaite.isRunning()) {
            clipDefaite.stop();
            clipDefaite.close();
        }
    }

}

