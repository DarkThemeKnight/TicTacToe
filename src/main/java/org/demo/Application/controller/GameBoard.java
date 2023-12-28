package org.demo.Application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.demo.Ticktacktoe;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
public class GameBoard {
    Ticktacktoe ticktacktoe = new Ticktacktoe();
    @FXML
    private Button cell00;
    @FXML
    private Button cell01;
    @FXML
    private Button cell02;
    @FXML
    private Button cell10;
    @FXML
    private Button cell11;
    @FXML
    private Button cell12;
    @FXML
    private Button cell20;
    @FXML
    private Button cell21;
    @FXML
    private Button cell22;
    @FXML
    private Button reset;
    @FXML
    private Button menu;

    @FXML
    private Label toPlay;
    private int currentPlayer = 0;
    private Map<Button, Integer> positionMap;
    public void initialize() {
        positionMap = new HashMap<>();
        positionMap.put(cell00, 0);
        positionMap.put(cell01, 1);
        positionMap.put(cell02, 2);
        positionMap.put(cell10, 3);
        positionMap.put(cell11, 4);
        positionMap.put(cell12, 5);
        positionMap.put(cell20, 6);
        positionMap.put(cell21, 7);
        positionMap.put(cell22, 8);
        positionMap.keySet().forEach(button -> {
            button.setText(null);
            button.setDisable(false);
        });
        positionMap.put(menu,-1000);
        positionMap.put(reset,1000);
        toPlay.setText("Player 1's Turn");
        ticktacktoe.initialize.accept(null);
    }
    @FXML
    private void menuOptions(ActionEvent event){
        Button clicked = (Button) event.getSource();
        int position = positionMap.get(clicked);
        if (position == 1000){
            initialize();
        }
        else if (position == -1000) {
            // Go back to the main menu (Welcome.fxml)
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Welcome.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) clicked.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                // Handle any potential exceptions
            }
        }
    }
    @FXML
    private void handleButtonPressed(ActionEvent event){
        boolean gameCompleted = gameCompleted(event);
        if (gameCompleted){
            Set<Button> buttons = positionMap.keySet();
            List<Button>buttonsFiltered = buttons.stream().filter(button -> positionMap.get(button) != 1000 && positionMap.get(button) != -1000).toList();
            buttonsFiltered.forEach(button -> button.setDisable(true));
        }
    }
    private void changePlayer(){
        currentPlayer++;
        if (currentPlayer % 2 == 0){
            toPlay.setText("Its Player 1's turn");
        }
        else {
            toPlay.setText("Its Player 2's turn");
        }
    }
    private boolean gameCompleted(ActionEvent event){
        Button clicked = (Button) event.getSource();
        int position = positionMap.get(clicked);
        if (currentPlayer % 2 == 0){
            //player 1
            boolean didPlay = ticktacktoe.play.apply(position,ticktacktoe.getPLAYER1());
            if (didPlay) {
                clicked.setText(ticktacktoe.getPLAYER1());
                boolean didWin = ticktacktoe.checkWin.test(null);
                if (didWin) {
                    toPlay.setText("Player 1 Wins");
                    return true;
                }
                boolean draw = ticktacktoe.checkDraw.test(null);
                if (draw) {
                    toPlay.setText("Game Drawn");
                    return true;
                }
                changePlayer();
            }
        }
        else {
            //player 2
            boolean didPlay = ticktacktoe.play.apply(position,ticktacktoe.getPLAYER2());
            if (didPlay){
                clicked.setText(ticktacktoe.getPLAYER2());
                boolean didWin = ticktacktoe.checkWin.test(null);
                if (didWin){
                    toPlay.setText("Player 2 Wins");
                    return true;
                }
                boolean draw = ticktacktoe.checkDraw.test(null);
                if (draw){
                    toPlay.setText("Game Drawn");
                    return true;
                }
                changePlayer();
            }
        }
        return false;
    }
}
