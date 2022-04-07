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
        Assertions.assertThrows(InvalidCharacterCombinationException.class, () -> {
            Game game = new Game(input);
        });
    }

    @Test
    public void testScenarioFrameOverload() {
        ArrayList<String> input =
                new ArrayList<>(Arrays.asList("9", "6", "1", "3", "7", "1", "8", "9", "5", "2", "4", "1", "7", "1", "2", "4", "1", "8", "3", "4"));
                //Würfe wie 9 6 und 8 9 sind unmöglich da > 10
        Assertions.assertThrows(InvalidCharacterCombinationException.class, () -> {
            Game game = new Game(input);
        });
    }

    @Test
    public void testScenarioNormalCaseOne() {
        ArrayList<String> input =
                new ArrayList<>(Arrays.asList("4", "/", "X", "3", "/", "X", "6", "2", "X", "X", "X", "1", "7"));
        //10+10(4 /)  +  10+10(X)  +  10+10(3 /)  +  10+6+2(X)  +  6+2(6 2)  +  10+10(X)  +  10+10(X)  +  10+1+7(X)  +  1+7(1 7)
        // = 152
        Game game = new Game(input);
        Assertions.assertEquals(152, game.getEntirePoints());
    }

    @Test
    public void testScenarioNormalCaseTwo() {
        ArrayList<String> input =
                new ArrayList<>(Arrays.asList("4", "2", "1", "3", "7", "1", "6", "2", "5", "2", "4", "1", "7", "1", "2", "4", "1", "8", "3", "4"));
        Game game = new Game(input);
        // 4+2+1+3+7+1+6+2+5+2+4+1+7+1+2+4+1+8+3+4
        // = 68
        Assertions.assertEquals(68, game.getEntirePoints());
    }

    @Test
    public void testScenarioNormalCaseThreeBonusThrows() {
        ArrayList<String> input =
                new ArrayList<>(Arrays.asList("4", "2", "1", "3", "7", "1", "6", "2", "5", "2", "4", "1", "7", "1", "2", "4", "1", "8", "X", "2", "4"));
        Game game = new Game(input);
        // 4+2+1+3+7+1+6+2+5+2+4+1+7+1+2+4+1+8+10+2+4
        // = 77
        Assertions.assertEquals(77, game.getEntirePoints());
    }
}
