package org.demo.Application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Welcome.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setTitle("Welcome to Tic Tac Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
