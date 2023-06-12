package com.application.saejeu.saejeu1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Stage;


import javax.sound.sampled.*;
import java.io.*;


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

    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        // Lancement de la musique de fond du jeu
        PlayMusicFond("/com/application/saejeu/saejeu1/musique/jeu.wav");
        // Lancement de l'application JavaFX
        launch();
    }

    public static void PlayMusicFond(String location) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        try (InputStream inputStream = Main.class.getResourceAsStream(location)) {
            if (inputStream == null) {
                throw new FileNotFoundException("Le fichier audio n'a pas pu être trouvé : " + location);
            }
            // Lecture des données audio du fichier en tant que tableau de bytes
            byte[] audioData = inputStream.readAllBytes();
            // Création d'un flux d'entrée à partir du tableau de bytes
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(audioData);
            // Création du flux audio à partir du flux d'entrée
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(byteArrayInputStream);
            // Obtention des informations sur le format audio
            DataLine.Info info = new DataLine.Info(Clip.class, audioInputStream.getFormat());
            // Création du clip audio
            clipFond = (Clip) AudioSystem.getLine(info);
            // Ouverture du clip avec le flux audio
            clipFond.open(audioInputStream);
            // Démarrage de la lecture du clip
            clipFond.start();
        }
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

    public static void PlayMusicVictoire(String location) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        try (InputStream inputStream = Main.class.getResourceAsStream(location)) {
            if (inputStream == null) {
                throw new FileNotFoundException("Le fichier audio n'a pas pu être trouvé : " + location);
            }
            // Lecture des données audio du fichier en tant que tableau de bytes
            byte[] audioData = inputStream.readAllBytes();
            // Création d'un flux d'entrée à partir du tableau de bytes
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(audioData);
            // Création du flux audio à partir du flux d'entrée
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(byteArrayInputStream);
            // Obtention des informations sur le format audio
            DataLine.Info info = new DataLine.Info(Clip.class, audioInputStream.getFormat());
            // Création du clip audio
            clipVictoire = (Clip) AudioSystem.getLine(info);
            // Ouverture du clip avec le flux audio
            clipVictoire.open(audioInputStream);
            // Démarrage de la lecture du clip
            clipVictoire.start();
        }
    }

    public static void stopMusicVictoire() {
        // Arrêt et fermeture du clip de musique de victoire s'il est en cours de lecture
        if (clipVictoire != null && clipVictoire.isRunning()) {
            clipVictoire.stop();
            clipVictoire.close();
        }
    }

    public static void PlayMusicDefaite(String location) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        try (InputStream inputStream = Main.class.getResourceAsStream(location)) {
            if (inputStream == null) {
                throw new FileNotFoundException("Le fichier audio n'a pas pu être trouvé : " + location);
            }
            // Lecture des données audio du fichier en tant que tableau de bytes
            byte[] audioData = inputStream.readAllBytes();
            // Création d'un flux d'entrée à partir du tableau de bytes
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(audioData);
            // Création du flux audio à partir du flux d'entrée
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(byteArrayInputStream);
            // Obtention des informations sur le format audio
            DataLine.Info info = new DataLine.Info(Clip.class, audioInputStream.getFormat());
            // Création du clip audio
            clipDefaite = (Clip) AudioSystem.getLine(info);
            // Ouverture du clip avec le flux audio
            clipDefaite.open(audioInputStream);
            // Démarrage de la lecture du clip
            clipDefaite.start();
        }
    }

    public static void stopMusicDefaite() {
        // Arrêt et fermeture du clip de musique de défaite s'il est en cours de lecture
        if (clipDefaite != null && clipDefaite.isRunning()) {
            clipDefaite.stop();
            clipDefaite.close();
        }
    }

}

