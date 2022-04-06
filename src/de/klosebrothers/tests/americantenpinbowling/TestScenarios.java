package de.klosebrothers.tests.americantenpinbowling;

import de.klosebrothers.americantenpinbowling.Game;
import de.klosebrothers.americantenpinbowling.InvalidCharacterCombinationException;
import de.klosebrothers.americantenpinbowling.InvalidCharacterException;
import de.klosebrothers.americantenpinbowling.InvalidInputSizeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class TestScenarios {

    @Test
    public void testScenarioInvalidInputSizeTooBig() {
        ArrayList<String> input =
                new ArrayList<>(Arrays.asList("X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X"));
        Assertions.assertThrows(InvalidInputSizeException.class, () -> {
            Game game = new Game(input);
        });
    }

    @Test
    public void testScenarioInvalidInputSizeTooSmall() {
        ArrayList<String> input =
                new ArrayList<>(Arrays.asList("X", "X", "X"));
        Assertions.assertThrows(InvalidInputSizeException.class, () -> {
            Game game = new Game(input);
        });
    }

    @Test
    public void testScenarioInvalidCharacterInput() {
        ArrayList<String> input =
                new ArrayList<>(Arrays.asList("c", "X", "X", "f", "X", "X", "X", "X", "X", "X", "X", "X"));
        Assertions.assertThrows(InvalidCharacterException.class, () -> {
            Game game = new Game(input);
        });
    }

    @Test
    public void testScenarioNullInput() {
        ArrayList<String> input = null;
        Assertions.assertThrows(Exception.class, () -> {
            Game game = new Game(input);
        });
    }

    @Test
    public void testScenarioSpareAndStrike() {
        ArrayList<String> input =
                new ArrayList<>(Arrays.asList("/", "X", "X", "/", "X", "X", "X", "X", "X", "X", "X", "X"));
        Assertions.assertThrows(InvalidCharacterCombinationException.class, () -> {
            Game game = new Game(input);
        });
    }

    @Test
    public void testScenarioFirstSpare() {
        ArrayList<String> input =
                new ArrayList<>(Arrays.asList("/", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X"));

    }

    @Test
    public void testScenarioNormalCaseOne() {
        ArrayList<String> input =
                new ArrayList<>(Arrays.asList("4", "/", "X", "3", "/", "X", "6", "2", "X", "X", "X", "X", "X", "X"));
                //TODO Prüfen
    }
}
