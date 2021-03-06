package de.klosebrothers.tests.americantenpinbowling;

import de.klosebrothers.americantenpinbowling.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    public void testIdentifyThrowAttemptUnknownValue() {
        char throwAttempt = "c".charAt(0);
        Game game = new Game();
        Assertions.assertThrows(InvalidCharacterException.class, () -> {
            game.identifyThrowAttempt(throwAttempt);
        });
    }

    @Test
    public void testIndentifyThrowAttemptKnownValue() throws ValueOutOfRangeException, InvalidCharacterException, InvalidCharacterCombinationException {
        char throwAttemptInput = "7".charAt(0);
        Game game = new Game();
        ThrowAttempt expectedThrowAttempt = new ThrowAttempt(7);
        ThrowAttempt actualThrowAttempt = game.identifyThrowAttempt(throwAttemptInput);
        assertEquals(expectedThrowAttempt.getKnockedDowns(), actualThrowAttempt.getKnockedDowns());
    }

    @Test
    public void testIndentifyThrowAttemptStrike() throws ValueOutOfRangeException, InvalidCharacterException, InvalidCharacterCombinationException {
        char throwAttemptInput = "x".charAt(0);
        Game game = new Game();
        ThrowAttempt expectedThrowAttempt = new ThrowAttempt(10);
        ThrowAttempt actualThrowAttempt = game.identifyThrowAttempt(throwAttemptInput);
        assertEquals(expectedThrowAttempt.isStrike(), actualThrowAttempt.isStrike());
    }

    @Test
    public void testIdentifyThrwoAttemptsMiss() throws ValueOutOfRangeException, InvalidCharacterException, InvalidCharacterCombinationException {
        char throwAttemptInput = "-".charAt(0);
        Game game = new Game();
        ThrowAttempt expectedThrowAttempt = new ThrowAttempt(0);
        ThrowAttempt actualThrowAttempt = game.identifyThrowAttempt(throwAttemptInput);
        assertEquals(expectedThrowAttempt.getKnockedDowns(), actualThrowAttempt.getKnockedDowns());
    }

    @Test
    public void testIndentifyThrowAttemptSpare() throws ValueOutOfRangeException, InvalidCharacterCombinationException, InvalidCharacterException, InvalidInputSizeException {
        Game gameOne = new Game();
        ThrowAttempt throwAttemptOne = new ThrowAttempt(4);
        ThrowAttempt throwAttemptTwo = new ThrowAttempt(6);
        gameOne.addThrowAttempt(throwAttemptOne);
        gameOne.addThrowAttempt(throwAttemptTwo);
        Frame expectedFrame = gameOne.getFrames().get(0);

        char throwAttemptInput = "/".charAt(0);
        Game gameTwo = new Game();
        gameTwo.addThrowAttempt(throwAttemptOne);
        gameTwo.addThrowAttempt(gameTwo.identifyThrowAttempt(throwAttemptInput));
        Frame actualFrame = gameTwo.getFrames().get(0);
        assertEquals(expectedFrame.isSpare(), actualFrame.isSpare());
    }

    @Test
    public void testGameInvalidInputSizeTooBig() {
        ArrayList<String> input =
                new ArrayList<>(Arrays.asList("X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X"));
        Assertions.assertThrows(InvalidInputSizeException.class, () -> {
            Game game = new Game(input);
        });
    }

    @Test
    public void testGameInvalidInputSizeIncompleteFrame() {
        ArrayList<String> input =
                new ArrayList<>(Arrays.asList("X", "X", "X", "X", "X", "X", "X", "X", "X", "8"));
        Assertions.assertThrows(InvalidInputSizeException.class, () -> {
            Game game = new Game(input);
        });
    }

    @Test
    public void testGameInvalidInputSizeTooSmall() {
        ArrayList<String> input =
                new ArrayList<>(Arrays.asList("X", "X", "X"));
        Assertions.assertThrows(InvalidInputSizeException.class, () -> {
            Game game = new Game(input);
        });
    }

    @Test
    public void testGameNullInput() {
        ArrayList<String> input = null;
        Assertions.assertThrows(NullPointerException.class, () -> {
            Game game = new Game(input);
        });
    }

    @Test
    public void testGameSpareAndStrike() {
        ArrayList<String> input =
                new ArrayList<>(Arrays.asList("/", "X", "X", "/", "X", "X", "X", "X", "X", "X", "X", "X"));
        Assertions.assertThrows(InvalidCharacterCombinationException.class, () -> {
            Game game = new Game(input);
        });
    }

    @Test
    public void testGameFirstSpare() {
        ArrayList<String> input =
                new ArrayList<>(Arrays.asList("/", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X"));
        Assertions.assertThrows(InvalidCharacterCombinationException.class, () -> {
            Game game = new Game(input);
        });
    }

    @Test
    public void testGameFrameOverload() {
        ArrayList<String> input =
                new ArrayList<>(Arrays.asList("9", "6", "1", "3", "7", "1", "8", "9", "5", "2", "4", "1", "7", "1", "2", "4", "1", "8", "3", "4"));
        //W??rfe wie 9 6 und 8 9 sind unm??glich da > 10
        Assertions.assertThrows(InvalidCharacterCombinationException.class, () -> {
            Game game = new Game(input);
        });
    }
}