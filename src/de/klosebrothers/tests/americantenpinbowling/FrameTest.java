package de.klosebrothers.tests.americantenpinbowling;

import de.klosebrothers.americantenpinbowling.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class FrameTest {

    @Test
    public void testIsSpareNotSpare() {
        Frame frame = new Frame();
        ThrowAttempt throwAttemptOne = new ThrowAttempt(1);
        ThrowAttempt throwAttemptTwo = new ThrowAttempt(4);
        frame.addThrowAttempt(throwAttemptOne);
        frame.addThrowAttempt(throwAttemptTwo);
        assertFalse(frame.isSpare());
    }

    @Test
    public void testIsSpareNotSpareButStrike() {
        Frame frame = new Frame();
        ThrowAttempt throwAttempt = new ThrowAttempt(10);
        frame.addThrowAttempt(throwAttempt);
        assertFalse(frame.isSpare());
    }

    @Test
    public void testIsSpareSpare(){
        Frame frame = new Frame();
        ThrowAttempt throwAttemptOne = new ThrowAttempt(3);
        ThrowAttempt throwAttemptTwo = new ThrowAttempt(7);
        frame.addThrowAttempt(throwAttemptOne);
        frame.addThrowAttempt(throwAttemptTwo);
        assertTrue(frame.isSpare());
    }

    @Test
    public void testContainsStrikeFalse(){
        Frame frame = new Frame();
        ThrowAttempt throwAttemptOne = new ThrowAttempt(3);
        ThrowAttempt throwAttemptTwo = new ThrowAttempt(7);
        frame.addThrowAttempt(throwAttemptOne);
        frame.addThrowAttempt(throwAttemptTwo);
        assertFalse(frame.containsStrike());
    }

    @Test
    public void testContainsStrikeTrue(){
        Frame frame = new Frame();
        ThrowAttempt throwAttemptOne = new ThrowAttempt(10);
        ThrowAttempt throwAttemptTwo = new ThrowAttempt(7);
        frame.addThrowAttempt(throwAttemptOne);
        frame.addThrowAttempt(throwAttemptTwo);
        assertTrue(frame.containsStrike());
    }

    @Test
    public void testAddThrowAttempt(){
        Frame frame = new Frame();
        ThrowAttempt throwAttempt = new ThrowAttempt(3);
        frame.addThrowAttempt(throwAttempt);
        int index = frame.getThrowAttempts().size() - 1;
        assertEquals(throwAttempt, frame.getThrowAttempts().get(index));
    }

    @Test
    public void testAddScoreNull(){
        //TODO
    }

    @Test
    public void testAddScoreNegative(){
        Frame frame = new Frame();
        Assertions.assertThrows(ValueOutOfRangeException.class, () -> {
            frame.addScore(-1);
        });
    }

    @Test
    public void testAddScorePositiveOutOfRange(){
        Frame frame = new Frame();
        Assertions.assertThrows(ValueOutOfRangeException.class, () -> {
            frame.addScore(21);
        });
    }

    @Test
    public void testAddScorePositiveInRange(){
        Frame frame = new Frame();
        Assertions.assertDoesNotThrow(() -> frame.addScore(4));
    }

    @Test
    public void testGetThrowAttemptsNull(){
        //TODO
    }

    @Test
    public void testGetThrowAttempts(){
        Frame frame = new Frame();
        ThrowAttempt throwAttemptOne = new ThrowAttempt(3);
        ThrowAttempt throwAttemptTwo = new ThrowAttempt(5);
        frame.addThrowAttempt(throwAttemptOne);
        frame.addThrowAttempt(throwAttemptTwo);
        ThrowAttempt[] expectedThrowAttempts = {throwAttemptOne, throwAttemptTwo};
        ThrowAttempt[] actualThrowAttempts = (ThrowAttempt[]) frame.getThrowAttempts().toArray();
        assertArrayEquals(expectedThrowAttempts,actualThrowAttempts);
    }
}