package org.demo.Application.controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeController {

    @FXML
    private Button startGameButton;

    @FXML
    private Button exitButton;

    @FXML
    private void startGame() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GameBoard.fxml"));
            Parent root = loader.load();

            // Get the controller instance
            GameBoard gameBoardController = loader.getController();

            // You can perform any initialization or setup on the controller if needed
            // gameBoardController.initialize();

            // Set up the stage
            Stage stage = (Stage) startGameButton.getScene().getWindow(); // Assuming startGameButton is a button in your welcome.fxml
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle any potential IOExceptions
        }
    }

    @FXML
    private void exitGame() {
        System.exit(0);
    }
}
