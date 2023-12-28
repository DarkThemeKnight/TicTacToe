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
    private void startGame() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GameBoard.fxml"));
            Parent root = loader.load();
            GameBoard gameBoardController = loader.getController();
            gameBoardController.initialize();
            Stage stage = (Stage) startGameButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void exitGame() {
        System.exit(0);
    }
}
