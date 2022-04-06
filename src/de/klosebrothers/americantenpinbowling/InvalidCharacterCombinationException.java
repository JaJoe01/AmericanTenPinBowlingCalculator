package de.klosebrothers.americantenpinbowling;

public class InvalidCharacterCombinationException extends Exception {
    public InvalidCharacterCombinationException() {
        super("Die gewählte Zeichenkombination ist ungültig");
    }
    public InvalidCharacterCombinationException(String msg) {
        super(msg);
    }
}
