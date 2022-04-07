package de.klosebrothers.tests.americantenpinbowling;

import de.klosebrothers.americantenpinbowling.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ThrowAttemptTest {

    @Test
    public void testPositiveOutOfRange() {
        Assertions.assertThrows(ValueOutOfRangeException.class, () -> {
            ThrowAttempt throwAttempt = new ThrowAttempt(11);
        });
    }

    @Test
    public void testNegativeValue() {
        Assertions.assertThrows(ValueOutOfRangeException.class, () -> {
            ThrowAttempt throwAttempt = new ThrowAttempt(-1);
        });
    }

    @Test
    public void testPositiveInRange() {
        Assertions.assertDoesNotThrow(() -> new ThrowAttempt(5));
    }

    @Test
    public void testNull() {

        //TODO
        int knockedDown = 0;
        Assertions.assertThrows(NullPointerException.class, () -> {
            ThrowAttempt throwAttempt = new ThrowAttempt(knockedDown);
        });
    }

    @Test
    public void testZero() {
        Assertions.assertDoesNotThrow(() -> new ThrowAttempt(0));
    }

    @Test
    public void testMaxValue() {
        Assertions.assertDoesNotThrow(() -> new ThrowAttempt(10));
    }




    @Test
    public void testIsStrikeMaxValue() {
        ThrowAttempt throwAttempt = new ThrowAttempt(10);
        assertTrue(throwAttempt.isStrike());
    }

    @Test
    public void testIsStrikeOutOfRange() {
        ThrowAttempt throwAttempt = new ThrowAttempt(4);
        assertFalse(throwAttempt.isStrike());
    }


    @Test
    public void testGetNockedDowns() {
        ThrowAttempt throwAttempt = new ThrowAttempt(5);
        Assertions.assertEquals(5, throwAttempt.getKnockedDowns());
    }
}