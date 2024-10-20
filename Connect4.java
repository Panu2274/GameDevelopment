package week4;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Connect4 {

    String board[][];
    int winningPlayer;
    Boolean draw;
    int playerTurn;
    Boolean winner;
    int difficulty;

    public Connect4() {
        winningPlayer = 0;
        draw = false;
        playerTurn = 1;
        difficulty = 1; 
        board = new String[6][7];
        newBoard();
    }

    public void newBoard() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = " . ";
            }
        }
    }

    public void displayBoard() {
        System.out.println(" ");
        System.out.println("\n        	**** Connect 4 ****        \n");
        System.out.println("\t 1    2    3    4    5    6    7");

        System.out.println("       +----+----+----+----+----+----+----+");
        for (int i = 0; i < 6; i++) {
        	System.out.print("       |");
            for (int j = 0; j < 7; j++) {
                System.out.print(board[i][j]+" |");
            }
            System.out.println("\n       +----+----+----+----+----+----+----+");
        }
    }

    public boolean validInput(String input) {
        return (Objects.equals(input, "1") ||
                Objects.equals(input, "2") ||
                Objects.equals(input, "3") ||
                Objects.equals(input, "4") ||
                Objects.equals(input, "5") ||
                Objects.equals(input, "6") ||
                Objects.equals(input, "7"));
    }

    public boolean isColumnFull(int column) {
        return (board[0][column - 1].equals(" X ") || board[0][column - 1].equals(" O "));
    }

    public int getNextAvailableSlot(int column) {
        int pos = 5;
        while (pos >= 0 && !board[pos][column].equals(" . ")) {
            pos--;
        }
        return pos;
    }

    public void swapPlayer() {
        playerTurn = (playerTurn == 1) ? 2 : 1;
    }

    public void playerMove() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nPlayer " + playerTurn + " - Please select which column to place your piece (1-7):");
        String input = sc.next();
        while (!validInput(input) || isColumnFull(Integer.parseInt(input))) {
            if (!validInput(input)) {
                System.out.print("\nInvalid input - Please select a column from 1-7:");
                input = sc.next();
            } else if (isColumnFull(Integer.parseInt(input))) {
                System.out.println("\nColumn full, select another column:");
                input = sc.next();
            }
        }

        int colChoice = Integer.parseInt(input) - 1;
        int row = getNextAvailableSlot(colChoice);

        String pieceToPlace = (playerTurn == 1) ? " X " : " O ";
        board[row][colChoice] = pieceToPlace;
        displayBoard();
        swapPlayer();
    }

    public void computerMoveEasy() {
        System.out.println("\nComputer (Player 2) is making a move (Easy)...");
        Random rand = new Random();
        int colChoice = rand.nextInt(7);

        while (isColumnFull(colChoice + 1)) {
            colChoice = rand.nextInt(7);
        }

        int row = getNextAvailableSlot(colChoice);
        board[row][colChoice] = " O ";
        displayBoard();
        swapPlayer();
    }

    public void computerMoveHard() {
        System.out.println("\nComputer (Player 2) is making a move (Hard)...");
        
        for (int j = 0; j < 7; j++) {
            if (!isColumnFull(j + 1)) {
                int row = getNextAvailableSlot(j);
                board[row][j] = " O ";
                if (checkWinner() == 2) {
                    displayBoard();
                    swapPlayer();
                    return;
                }
                board[row][j] = " . "; 
            }
        }

      
        computerMoveEasy();
    }

    public boolean isBoardFull() {
        for (int j = 0; j < 7; j++) {
            if (board[0][j].equals(" . ")) {
                return false;
            }
        }
        return true;
    }

    public String checkVerticalWinner() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 7; j++) {
                if (board[i][j].equals(board[i + 1][j]) &&
                    board[i][j].equals(board[i + 2][j]) &&
                    board[i][j].equals(board[i + 3][j]) &&
                    !board[i][j].equals(" . ")) {
                    return board[i][j];
                }
            }
        }
        return null;
    }

    public String checkHorizontalWinner() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j].equals(board[i][j + 1]) &&
                    board[i][j].equals(board[i][j + 2]) &&
                    board[i][j].equals(board[i][j + 3]) &&
                    !board[i][j].equals(" . ")) {
                    return board[i][j];
                }
            }
        }
        return null;
    }

    public String checkLeftDiagonalWinner() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j].equals(board[i + 1][j + 1]) &&
                    board[i][j].equals(board[i + 2][j + 2]) &&
                    board[i][j].equals(board[i + 3][j + 3]) &&
                    !board[i][j].equals(" . ")) {
                    return board[i][j];
                }
            }
        }
        return null;
    }

    public String checkRightDiagonalWinner() {
        for (int i = 0; i < 3; i++) {
            for (int j = 3; j < 7; j++) {
                if (board[i][j].equals(board[i + 1][j - 1]) &&
                    board[i][j].equals(board[i + 2][j - 2]) &&
                    board[i][j].equals(board[i + 3][j - 3]) &&
                    !board[i][j].equals(" . ")) {
                    return board[i][j];
                }
            }
        }
        return null;
    }

    public int checkWinner() {
        String symbol = checkVerticalWinner();
        if (symbol == null) symbol = checkHorizontalWinner();
        if (symbol == null) symbol = checkLeftDiagonalWinner();
        if (symbol == null) symbol = checkRightDiagonalWinner();

        if (symbol != null) {
            return symbol.equals(" X ") ? 1 : 2;
        }
        return 0;
    }

    public boolean checkForDraw() {
        return isBoardFull() && checkWinner() == 0;
    }

    public void showWinner() {
        System.out.println(" ");
        System.out.println("*****************************");
        System.out.println("                             ");
        System.out.println("      PLAYER " + winningPlayer + " WINS !!!!");
        System.out.println("  *                       *  ");
        System.out.println("     *       \\O/      *      ");
        System.out.println("  *     *     |    *      *  ");
        System.out.println("     *       / \\      *     ");
        System.out.println("  *                       *  ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("*****************************");
    }

    public void setDifficulty() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Select difficulty level (1 for Easy, 2 for Hard): ");
        String input = sc.next();
        while (!input.equals("1") && !input.equals("2")) {
            System.out.print("Invalid input. Please select either 1 (Easy) or 2 (Hard): ");
            input = sc.next();
        }
        difficulty = Integer.parseInt(input);
    }

    public void playGame() {
        newBoard();
        setDifficulty();
        while (winningPlayer == 0 && !draw) {
            if (playerTurn == 1) {
                playerMove();
            } else {
                if (difficulty == 1) {
                    computerMoveEasy();
                } else {
                    computerMoveHard();
                }
            }
            winningPlayer = checkWinner();
            draw = checkForDraw();
            if (winningPlayer != 0) {
                showWinner();
            } else if (draw) {
                System.out.println("It's a draw...");
            }
        }
        playAgainOption();
    }

    public void playAgainOption() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Do you want to play again? (y/n): ");
        String input = sc.next();
        if (input.equalsIgnoreCase("y")) {
            winningPlayer = 0;
            draw = false;
            playerTurn = 1;
            playGame();
        } else {
            System.out.println("Thanks for playing!\nSee you soon....");
        }
    }

    public static void main(String[] args) {
        Connect4 cn = new Connect4();
        System.out.println("	!!!! Welcome to Connect4 game !!!!\n");
        cn.playGame();
    }
}
