package com.application.saejeu.saejeu1.controleur;

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
    }
    @FXML
    public void quitterApplication(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }
}