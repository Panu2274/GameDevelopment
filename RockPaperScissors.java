package week2;

import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String[] rps = {"Rock", "Paper", "Scissor"};
        String userMove;
        String computerMove;

        int userScore = 0;
        int computerScore = 0;
        int tieCount = 0;

        System.out.println("Choose game mode: Best of 3 or Best of 5 (Type 3 or 5):");
        int roundsToWin = scanner.nextInt();
        scanner.nextLine(); 

        int maxWins = roundsToWin / 2 + 1;

        while (userScore < maxWins && computerScore < maxWins) {
            System.out.println("Enter your move (Rock, Paper, Scissor). To exit the game, type Exit.");
            userMove = scanner.nextLine();

            if (userMove.equalsIgnoreCase("Exit")) {
                System.out.println("Thanks for playing!");
                break;
            }

            if (!userMove.equalsIgnoreCase("Rock") && !userMove.equalsIgnoreCase("Paper") && !userMove.equalsIgnoreCase("Scissor")) {
                System.out.println("Invalid move, please try again.");
                continue;
            }

            int computerChoice = random.nextInt(3);
            computerMove = rps[computerChoice];

         
            System.out.println("Rock...");
            pause();
            System.out.println("Paper...");
            pause();
            System.out.println("Scissor!");
            pause();

           
            System.out.println("Computer's move: " + computerMove);

            if (userMove.equalsIgnoreCase(computerMove)) {
                System.out.println("It's a tie! Both chose " + computerMove);
                tieCount++;
            } else if (userMove.equalsIgnoreCase("Rock") && computerMove.equals("Scissor") ||
                       userMove.equalsIgnoreCase("Scissor") && computerMove.equals("Paper") ||
                       userMove.equalsIgnoreCase("Paper") && computerMove.equals("Rock")) {
                System.out.println(getRandomWinComment());
                userScore++;
            } else {
                System.out.println(getRandomLoseComment());
                computerScore++;
            }

            System.out.println("Score:");
            System.out.println("You: " + userScore + " | Computer: " + computerScore + " | Ties: " + tieCount);
            System.out.println();
        }

        if (userScore > computerScore) {
            System.out.println("Congrats! You won the game!");
        } else {
            System.out.println("The computer won the game! Better luck next time.");
        }

        scanner.close();
    }

   
    private static void pause() {
        try {
            Thread.sleep(500); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

   
    private static String getRandomWinComment() {
        String[] winComments = {
            "Well done, you're on fire!",
            "You crushed it!",
            "Amazing! You're the champion!",
            "Nice move! You're unstoppable!"
        };
        Random random = new Random();
        return winComments[random.nextInt(winComments.length)];
    }

    
    private static String getRandomLoseComment() {
        String[] loseComments = {
            "Oops, that didn't work out!",
            "The computer got you this time!",
            "Better luck next round!",
            "Oh no! The computer is catching up!"
        };
        Random random = new Random();
        return loseComments[random.nextInt(loseComments.length)];
    }
}
