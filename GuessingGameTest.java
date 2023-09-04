package com.mike.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GuessingGameTest {

    public static final int GAME_RANDOMNESS_RETRIES = 100;
    private GuessingGame game;

    @BeforeEach
    void setUp() {
        game = new GuessingGame();
    }

    @Test
    public void testSimpleWinSituation() {
        int randomNum = game.getRandomNumber();
        String message = game.guess(randomNum, randomNum);
        assertEquals("You guessed correctly in 1 guess", message);
    }

    @Test
    public void testOneWrongNegGuessSituation() {
        int randomNum = game.getRandomNumber();
        String message = game.guess(-5, randomNum);
        assertEquals("You guessed wrong - too low!", message);
    }

    @Test
    public void testOneWrongPositiveGuessSituation() {
        int randomNum = game.getRandomNumber();
        String message = game.guess(randomNum + 1, randomNum);
        assertEquals("You guessed wrong - too high!", message);
    }

    @RepeatedTest(2)
    public void testRandomness() {
        int[] randomNumCount = new int[11];
        for (int counter = 0; counter < GAME_RANDOMNESS_RETRIES; counter++) {
            int randomNum = game.getRandomNumber();
            randomNumCount[randomNum] = 1;
        }
        int sum = 0;
        for (int num : randomNumCount) {
            sum += num;
        }
        assertEquals(10, sum);
    }

    @Test
    public void testFourWrongGuesses() {
        int randomNum = game.getRandomNumber();
        game.guess(-3, randomNum);
        game.guess(-3, randomNum);
        game.guess(-3, randomNum);
        String message = game.guess(-3, randomNum);
        assertEquals("You ran out of guesses!", message);
    }

    @Test
    public void testThreeWrongGuessesAndOneCorrect() {
        int randomNum = game.getRandomNumber();
        game.guess(-3, randomNum);
        game.guess(-3, randomNum);
        game.guess(-3, randomNum);
        String message = game.guess(randomNum, randomNum);
        assertEquals("You guessed correctly in 4 guesses", message);
    }

    @Test
    public void testTwoWrongGuessesAndOneCorrect() {
        int randomNum = game.getRandomNumber();
        game.guess(-3, randomNum);
        game.guess(-3, randomNum);
        String message = game.guess(randomNum, randomNum);
        assertEquals("You guessed correctly in 3 guesses", message);
    }
}
