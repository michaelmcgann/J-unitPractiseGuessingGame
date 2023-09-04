package com.mike.game;

import java.util.Random;

public class GuessingGame {
    private int guesses = 0;

    public String guess(int guessedNumber, int correctNumber) {
        String returnMessage = null;
        guesses++;
        if (guesses <= 4 && guessedNumber == correctNumber) {
            String guessPlural = guesses == 1 ? " guess" : " guesses";
            returnMessage = "You guessed correctly in " + guesses + guessPlural;
        } else if (guesses < 4) {
            returnMessage = guessedNumber < correctNumber ?
                    "You guessed wrong - too low!" : "You guessed wrong - too high!";
        } else {
            returnMessage = "You ran out of guesses!";
        }
        return returnMessage;
    }

    public int getRandomNumber() {
        return new Random().nextInt(10) + 1;
    }

    public static void main(String[] args) {
        GuessingGame game = new GuessingGame();
        int randomNumber = game.getRandomNumber();
        int guesses = 0;
        int guessNumber = 0;

        while (guesses < 4) {

            boolean validInput = false;
            while (!validInput) {
                String guess = System.console().readLine("Guess a number between 1 and 10: ");
                try {
                    guessNumber = Integer.parseInt(guess);
                    validInput = true;
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number");
                }
            }

            guesses++;
            String feedback = game.guess(guessNumber, randomNumber);
            if (feedback.contains("correctly")) {
                System.out.println(feedback);
                break;
            } else {
                System.out.println(feedback);
            }
        }
    }
}
