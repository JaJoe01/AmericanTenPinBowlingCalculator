package de.klosebrothers.tests.americantenpinbowling;

import de.klosebrothers.americantenpinbowling.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class TestScenarios {

    @Test
    public void testScenarioNormalCaseOne() throws InvalidCharacterException, InvalidCharacterCombinationException, ValueOutOfRangeException, InvalidInputSizeException {
        ArrayList<String> input =
                new ArrayList<>(Arrays.asList("4", "/", "X", "3", "/", "X", "6", "2", "X", "X", "X", "1", "7", "3", "4"));
        //10+10(4 /)  +  10+10(X)  +  10+10(3 /)  +  10+6+2(X)  +  6+2(6 2)  +  10+10(X)  +  10+10(X)  +  10+1+7(X)  +  1+7(1 7)  +  3+4(3 4)
        // = 170
        Game game = new Game(input);
        Assertions.assertEquals(170, game.getEntirePoints());
    }

    @Test
    public void testScenarioNormalCaseTwo() throws InvalidCharacterException, InvalidCharacterCombinationException, ValueOutOfRangeException, InvalidInputSizeException {
        ArrayList<String> input =
                new ArrayList<>(Arrays.asList("4", "2", "1", "3", "7", "1", "6", "2", "5", "2", "4", "1", "7", "1", "2", "4", "1", "8", "3", "4"));
        Game game = new Game(input);
        // 4+2+1+3+7+1+6+2+5+2+4+1+7+1+2+4+1+8+3+4
        // = 68
        Assertions.assertEquals(68, game.getEntirePoints());
    }

    @Test
    public void testScenarioNormalCaseThreeBonusThrows() throws InvalidCharacterException, InvalidCharacterCombinationException, ValueOutOfRangeException, InvalidInputSizeException {
        ArrayList<String> input =
                new ArrayList<>(Arrays.asList("4", "2", "1", "3", "7", "1", "6", "2", "5", "2", "4", "1", "7", "1", "2", "4", "1", "8", "X", "2", "4"));
        Game game = new Game(input);
        // 4+2+1+3+7+1+6+2+5+2+4+1+7+1+2+4+1+8+10+2+4
        // = 77
        Assertions.assertEquals(77, game.getEntirePoints());
    }

}
