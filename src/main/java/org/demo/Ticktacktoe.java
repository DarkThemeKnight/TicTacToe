package org.demo;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Ticktacktoe {
    private String[] board = new String[9];

    public String getPLAYER1() {
        return "X";
    }

    public String getPLAYER2() {
        return "O";
    }

    public Consumer<Void> initialize = unused -> board = new String[9];
    public BiFunction<Integer,String, Boolean> play  = (position,value) -> {
        if (board[position] != null){
            return false;
        }
        board[position] = value;
        return true;
    };
    public Predicate<Void> checkWin = unused -> {
        // Check rows for a win
        for (int i = 0; i < 9; i += 3) {
            if (board[i] != null && board[i].equals(board[i + 1]) && board[i].equals(board[i + 2])) {
                return true;
            }
        }

        // Check columns for a win
        for (int i = 0; i < 3; i++) {
            if (board[i] != null && board[i].equals(board[i + 3]) && board[i].equals(board[i + 6])) {
                return true;
            }
        }

        // Check diagonals for a win
        if (board[0] != null && board[0].equals(board[4]) && board[0].equals(board[8])) {
            return true;
        }
        return board[2] != null && board[2].equals(board[4]) && board[2].equals(board[6]);// No win condition found
    };
    public Predicate<Void> checkDraw = unused -> {
        for (String cell : board) {
            if (cell == null) {
                return false;
            }
        }
        return true;
    };


}
