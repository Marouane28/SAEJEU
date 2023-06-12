package com.application.saejeu.saejeu1.controleur;

import com.application.saejeu.saejeu1.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;

public class GameoverControleur {

    // quand le bouton est cliqué retour au menu
    @FXML
    private void retourMenu(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL resource = getClass().getResource("/com/application/saejeu/saejeu1/vueJeu.fxml");
        Parent root = fxmlLoader.load(resource);
        Scene scene = new Scene(root, 1440, 800);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Zombie Survival : La Dernière Lueur d'Espoir");
        primaryStage.setScene(scene);
        primaryStage.show();
        // Arreter la musique en cours (si elle est en cours de lecture)
        Main.stopMusicDefaite();
        // Lancer la musique du jeu
        try {
            Main.PlayMusicFond("/com/application/saejeu/saejeu1/musique/jeu.wav");
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void quitterApplication(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }
}