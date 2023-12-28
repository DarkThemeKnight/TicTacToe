package org.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Ticktacktoe {
    private String[] board = new String[9];
    private final String PLAYER1 = "X";
    private final String PLAYER2 = "O";
    private Scanner sc;
    private final Map<String,Integer> playerMap = new HashMap<>(Map.of(PLAYER2,2,PLAYER1,1));

    public String getPLAYER1() {
        return PLAYER1;
    }

    public String getPLAYER2() {
        return PLAYER2;
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
    public BiFunction<Integer,Integer,Integer> parsePosition = (r,c)->
    {
        if (r > 2 || r < 0 || c < 0 || c > 2){
            return  -1;
        }
        return r * 3 + c;
    };

    public void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 9; i += 3) {
            System.out.printf("| %s | %s | %s |%n",
                    (board[i] != null ? board[i] : " "),
                    (board[i + 1] != null ? board[i + 1] : " "),
                    (board[i + 2] != null ? board[i + 2] : " "));
            System.out.println("-------------");
        }
    }
    public boolean playGameCommandLine(){
        sc = new Scanner(System.in);
        initialize.accept(null);
        boolean stoppingConditionIsMet = false;
        int player = 0;
         while (!stoppingConditionIsMet){
            printBoard();
            String currentPlayer;
            if (player % 2 == 0){ // player 1
                System.out.println("Player One's Turn\n");
                currentPlayer = PLAYER1;
            }else {
                System.out.println("Player Two's Turn\n");
                currentPlayer = PLAYER2;
            }
            int row;
            int col;
             try {
                 System.out.print("Enter row =>  ");
                 while (!sc.hasNextInt()) {
                     System.out.println("Invalid Input. Enter a valid number for row.");
                     sc.next(); // Clear the invalid input
                 }
                 row = sc.nextInt();

                 System.out.print("Enter Column => ");
                 while (!sc.hasNextInt()) {
                     System.out.println("Invalid Input. Enter a valid number for column.");
                     sc.next(); // Clear the invalid input
                 }
                 col = sc.nextInt();
                 System.out.println();
             } catch (NumberFormatException e) {
                 System.out.println("Invalid Input");
                 continue;
             }

             int position = parsePosition.apply(row,col);
            if (position == -1){
                System.out.println("Illegal move");
                continue;
            }
            boolean isValid = play.apply(position,currentPlayer);
            if (!isValid){
                System.out.println("Position already played");
                continue;
            }
            boolean win = checkWin.test(null);
            if (win){
                printBoard();
                System.out.println("Player "+playerMap.get(currentPlayer)+" has won the game");
                stoppingConditionIsMet = true;
            }
            boolean draw = checkDraw.test(null);
            if (draw){
                printBoard();
                System.out.println("Game drawn");
                stoppingConditionIsMet = true;
            }
            player++;
        }
         sc.close();
        return requestRematch();
    }
    private boolean requestRematch(){
        sc = new Scanner(System.in);
        System.out.println("Rematch ?\nA.Yes\tB.No");
        String val = sc.nextLine().toUpperCase();
        while (!val.equals("YES") && !val.equals("NO"))
        {
            System.out.println("Invalid input");
            val = sc.nextLine().toUpperCase();
        }
        switch (val){
            case "YES" -> {
                return true;
            }
            case "NO" -> {
                return false;
            }
        }
        return false;
    }

}
