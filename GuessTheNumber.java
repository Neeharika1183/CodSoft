import java.util.*;
public class GuessTheNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int totalRounds = 0;
        int totalScore = 0;
        boolean playAgain = true;
        while (playAgain) {
            totalRounds++;
            int myNum = rand.nextInt(101);
            int maxAttempts = 8;
            int attempts = 0;
            boolean guessedCorrectly = false;
            System.out.println("Round " + totalRounds + ": My number is between 0 to 100");
            System.out.println("You have " + maxAttempts + " attempts to guess it.");
            while (attempts < maxAttempts && !guessedCorrectly) {
                System.out.println("Enter your guess: ");
                int guessNum = sc.nextInt();
                attempts++;
                if (guessNum == myNum) {
                    System.out.println("You guessed it right!! My number is " + myNum);
                    guessedCorrectly = true;
                    totalScore += (maxAttempts - attempts + 1);
                } else if (myNum < guessNum) {
                    System.out.println("Try again! My number is less than " + guessNum);
                } else {
                    System.out.println("Try again! My number is greater than " + guessNum);
                }
            }
            if (!guessedCorrectly) {
                System.out.println("Sorry! You've used all your attempts. My number was " + myNum);
            }
            System.out.println("Your score for this round is: " + (maxAttempts - attempts + 1));
            System.out.println("Do you want to play again? (yes/no): ");
            String response = sc.next();
            playAgain = response.equalsIgnoreCase("yes");
        }
        System.out.println("Total rounds played: " + totalRounds);
        System.out.println("Your total score: " + totalScore);
        sc.close();
    }
}