package week1;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GameQuiz {

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 100;
    private static final int MAX_TRIES = 7;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String play = "yes";

        while (play.equalsIgnoreCase("yes")) {
            playGame(sc);
            System.out.println("Would you like to play again (yes or no):");
            play = sc.next().toLowerCase();
        }

        System.out.println("Thank you for playing!\nSee you soon...");
        sc.close();
    }

    private static void playGame(Scanner sc) {
        Random rd = new Random();
        int randNum = rd.nextInt(MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER; // Generate number between 1 and 100
        int guess = -1;
        int tries = 0;

        while (guess != randNum && tries < MAX_TRIES) {
            System.out.println("Guess a number between " + MIN_NUMBER + " to " + MAX_NUMBER + ":");
            try {
                guess = sc.nextInt();
                if (guess < MIN_NUMBER || guess > MAX_NUMBER) {
                    System.out.println("Please enter a number within the specified range!");
                    continue; // Skip the rest of the loop iteration
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                sc.next(); // Clear invalid input
                continue; // Skip to the next iteration
            }

            tries++;
            if (guess == randNum) {
                System.out.println("Awesome! You guessed the number!");
                System.out.println("And you took " + tries + " guesses!");
            } else if (guess > randNum) {
                System.out.println("Your guess is too high, try again.");
            } else {
                System.out.println("Your guess is too low, try again.");
            }

            if (tries >= MAX_TRIES) {
                System.out.println("Sorry, you've used all your tries! The number was: " + randNum);
            }
        }
    }
}
