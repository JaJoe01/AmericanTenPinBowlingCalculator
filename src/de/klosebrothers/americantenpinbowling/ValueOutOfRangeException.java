package de.klosebrothers.americantenpinbowling;

public class ValueOutOfRangeException extends Exception {
    public ValueOutOfRangeException() {
        super("Der Wert befindet sich ausserhalb des zulässigen Bereichs");
    }

    public ValueOutOfRangeException(String msg) {
        super(msg);
    }
}
