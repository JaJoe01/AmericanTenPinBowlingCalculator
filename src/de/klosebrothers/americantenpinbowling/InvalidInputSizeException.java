package de.klosebrothers.americantenpinbowling;

public class InvalidInputSizeException extends Exception {

    public InvalidInputSizeException() {
        super("Die Eingegebene Liste hat eine unpassende Größe");
    }
    public InvalidInputSizeException(String msg) {
        super(msg);
    }
}
