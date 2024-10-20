package week3;
import java.util.Scanner;
import java.util.Random;

public class TicTacToe {

    static char[][] board;
    static char currentPlayer;
    static char playerSymbol;
    static char computerSymbol;
    static int playerScore = 0;
    static int computerScore = 0;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println("You will play against the computer.");
        
        // Allow player to choose X or O
        chooseSymbol();
        
        while (true) {
            initializeBoard();
            currentPlayer = 'O';  

            while (true) {
                printBoard();
                if (currentPlayer == playerSymbol) {
                    playerMove();  
                } else {
                    computerMove(); 
                }
                if (isWinner()) {
                    printBoard();
                    if (currentPlayer == playerSymbol) {
                        System.out.println("Awesome! You beat the computer!");
                        playerScore++;
                    } else {
                        System.out.println("Oops! The computer won this time. Better luck next time!");
                        computerScore++;
                    }
                    break;
                }
                if (isBoardFull()) {
                    printBoard();
                    System.out.println("It's a draw!");
                    break;
                }
                switchPlayer();
            }
            
            // Display score and ask if the player wants to play again
            System.out.println("\nScores: You - " + playerScore + ", Computer - " + computerScore);
            System.out.print("\nDo you want to play again? (yes/no): ");
            if (!scanner.next().equalsIgnoreCase("yes")) {
                System.out.println("Thanks for playing! Final Scores: You - " + playerScore + ", Computer - " + computerScore);
                break;
            }
        }
    }

    public static void chooseSymbol() {
        System.out.print("Choose your symbol (X or O): ");
        playerSymbol = scanner.next().toUpperCase().charAt(0);
        if (playerSymbol == 'X') {
            computerSymbol = 'O';
        } else {
            computerSymbol = 'X';
        }
        System.out.println("You are '" + playerSymbol + "' and the computer is '" + computerSymbol + "'.");
    }

    public static void initializeBoard() {
        board = new char[][]{{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
    }

    public static void printBoard() {
        System.out.println("\n+---+---+---+");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("+---+---+---+");
        }
    }

    public static void playerMove() {
        int move;
        while (true) {
            System.out.print("\nPlayer " + currentPlayer + ", enter your move (1-9): ");
            move = scanner.nextInt();
            if (move >= 1 && move <= 9 && isValidMove(move)) {
                placeMove(move);
                break;
            } else {
                System.out.println("This move is not valid. Try again.");
            }
        }
    }

    public static void computerMove() {
        Random rand = new Random();
        int move;
        while (true) {
            move = rand.nextInt(9) + 1;  
            if (isValidMove(move)) {
                placeMove(move);
                System.out.println("\nComputer placed '" + computerSymbol + "' at position: " + move);
                break;
            }
        }
    }

    public static void placeMove(int move) {
        int row = (move - 1) / 3;
        int col = (move - 1) % 3;
        board[row][col] = currentPlayer;
    }

    public static boolean isValidMove(int move) {
        int row = (move - 1) / 3;
        int col = (move - 1) % 3;
        return board[row][col] == ' ';
    }

    public static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public static boolean isWinner() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true;
            }
        }
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true;
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            return true;
        }
        return false;
    }

    public static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}
