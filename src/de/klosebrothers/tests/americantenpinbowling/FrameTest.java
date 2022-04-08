package de.klosebrothers.tests.americantenpinbowling;

import de.klosebrothers.americantenpinbowling.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FrameTest {

    @Test
    public void testIsSpareNotSpare() throws ValueOutOfRangeException {
        Frame frame = new Frame();
        ThrowAttempt throwAttemptOne = new ThrowAttempt(1);
        ThrowAttempt throwAttemptTwo = new ThrowAttempt(4);
        frame.addThrowAttempt(throwAttemptOne);
        frame.addThrowAttempt(throwAttemptTwo);
        assertFalse(frame.isSpare());
    }

    @Test
    public void testIsSpareNotSpareButStrike() throws ValueOutOfRangeException {
        Frame frame = new Frame();
        ThrowAttempt throwAttempt = new ThrowAttempt(10);
        frame.addThrowAttempt(throwAttempt);
        assertFalse(frame.isSpare());
    }

    @Test
    public void testIsSpareSpare() throws ValueOutOfRangeException {
        Frame frame = new Frame();
        ThrowAttempt throwAttemptOne = new ThrowAttempt(3);
        ThrowAttempt throwAttemptTwo = new ThrowAttempt(7);
        frame.addThrowAttempt(throwAttemptOne);
        frame.addThrowAttempt(throwAttemptTwo);
        assertTrue(frame.isSpare());
    }

    @Test
    public void testContainsStrikeFalse() throws ValueOutOfRangeException {
        Frame frame = new Frame();
        ThrowAttempt throwAttemptOne = new ThrowAttempt(3);
        ThrowAttempt throwAttemptTwo = new ThrowAttempt(7);
        frame.addThrowAttempt(throwAttemptOne);
        frame.addThrowAttempt(throwAttemptTwo);
        assertFalse(frame.containsStrike());
    }

    @Test
    public void testContainsStrikeTrue() throws ValueOutOfRangeException {
        Frame frame = new Frame();
        ThrowAttempt throwAttemptOne = new ThrowAttempt(10);
        ThrowAttempt throwAttemptTwo = new ThrowAttempt(7);
        frame.addThrowAttempt(throwAttemptOne);
        frame.addThrowAttempt(throwAttemptTwo);
        assertTrue(frame.containsStrike());
    }

    @Test
    public void testAddThrowAttempt() throws ValueOutOfRangeException {
        Frame frame = new Frame();
        ThrowAttempt throwAttempt = new ThrowAttempt(3);
        frame.addThrowAttempt(throwAttempt);
        int index = frame.getThrowAttempts().size() - 1;
        assertEquals(throwAttempt, frame.getThrowAttempts().get(index));
    }

    @Test
    public void testAddScoreNegative(){
        Frame frame = new Frame();
        Assertions.assertThrows(ValueOutOfRangeException.class, () -> {
            frame.setScore(-1);
        });
    }

    @Test
    public void testAddScorePositiveOutOfRange(){
        Frame frame = new Frame();
        Assertions.assertThrows(ValueOutOfRangeException.class, () -> {
            frame.setScore(31);
        });
    }

    @Test
    public void testAddScorePositiveInRange(){
        Frame frame = new Frame();
        Assertions.assertDoesNotThrow(() -> frame.setScore(4));
    }


    @Test
    public void testGetThrowAttempts() throws ValueOutOfRangeException {
        Frame frame = new Frame();
        ThrowAttempt throwAttemptOne = new ThrowAttempt(3);
        ThrowAttempt throwAttemptTwo = new ThrowAttempt(5);
        frame.addThrowAttempt(throwAttemptOne);
        frame.addThrowAttempt(throwAttemptTwo);
        ThrowAttempt[] expectedThrowAttempts = {throwAttemptOne, throwAttemptTwo};
        ThrowAttempt[] actualThrowAttempts = new ThrowAttempt[2];
        actualThrowAttempts[0] =frame.getThrowAttempts().get(0);
        actualThrowAttempts[1] =frame.getThrowAttempts().get(1);
        assertArrayEquals(expectedThrowAttempts,actualThrowAttempts);
    }
}