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
    public void testIndentifyThrowAttemptKnownValue() {
        char throwAttemptInput = "7".charAt(0);
        Game game = new Game();
        ThrowAttempt expectedThrowAttempt = new ThrowAttempt(7);
        ThrowAttempt actualThrowAttempt = game.identifyThrowAttempt(throwAttemptInput);
        assertEquals(expectedThrowAttempt, actualThrowAttempt);
    }

    @Test
    public void testIndentifyThrowAttemptStrike() {
        char throwAttemptInput = "x".charAt(0);
        Game game = new Game();
        ThrowAttempt expectedThrowAttempt = new ThrowAttempt(10);
        ThrowAttempt actualThrowAttempt = game.identifyThrowAttempt(throwAttemptInput);
        assertEquals(expectedThrowAttempt, actualThrowAttempt);
    }

    @Test
    public void testIdentifyThrwoAttemptsMiss() {
        char throwAttemptInput = "-".charAt(0);
        Game game = new Game();
        ThrowAttempt expectedThrowAttempt = new ThrowAttempt(0);
        ThrowAttempt actualThrowAttempt = game.identifyThrowAttempt(throwAttemptInput);
        assertEquals(expectedThrowAttempt, actualThrowAttempt);
    }

    @Test
    public void testIndentifyThrowAttemptSpare() {
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
        assertEquals(expectedFrame, actualFrame);
    }

    @Test
    public void testIdentifyThrowAttemptNull() {
        //TODO
    }

    @Test
    public void testIdentifyThrowAttemptOversizedInput() {
        char throwAttemptInput = "11".charAt(0);
        Game game = new Game();
        Assertions.assertThrows(ValueOutOfRangeException.class, () -> {
            game.identifyThrowAttempt(throwAttemptInput);
        });
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
        Assertions.assertThrows(Exception.class, () -> {
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
        //Würfe wie 9 6 und 8 9 sind unmöglich da > 10
        Assertions.assertThrows(InvalidCharacterCombinationException.class, () -> {
            Game game = new Game(input);
        });
    }
}