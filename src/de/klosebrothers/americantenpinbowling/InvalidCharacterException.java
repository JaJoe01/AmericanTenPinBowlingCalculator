package de.klosebrothers.americantenpinbowling;

public class InvalidCharacterException extends Exception{
    public InvalidCharacterException() {
        super("Das verwendete Zeichen entspricht nicht den vorgegebenen Konventionen");
    }
    public InvalidCharacterException(String msg) {
        super(msg);
    }
}
