package de.klosebrothers.americantenpinbowling;

import java.util.ArrayList;
import java.util.Arrays;

public class Game {
    private ArrayList<Frame> frames = new ArrayList<>();
    private int entirePoints;

    public Game() {

    }
    //Um Laufzeit zu sparen wird der Input nicht in einer speziellen Klasse validiert,
    //sondern "on the run" an verschiedenen Stellen geprüft
    public Game(ArrayList<String> throwInput) throws InvalidCharacterException, ValueOutOfRangeException, InvalidCharacterCombinationException, InvalidInputSizeException {
        for (int i = 0; i < throwInput.size(); i++) {
            addThrowAttempt(identifyThrowAttempt(throwInput.get(i).charAt(0)));
        }
        computeEntirePoints();
    }

    public ThrowAttempt identifyThrowAttempt(char throwAsChar)
            throws ValueOutOfRangeException, InvalidCharacterException, InvalidCharacterCombinationException {
        ThrowAttempt throwAttempt;
        ArrayList<Character> validNumbersAsChar =
                new ArrayList<>(Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9'));
        //Für Nummern 1-9
        if (validNumbersAsChar.contains(throwAsChar)) {
            throwAttempt = new ThrowAttempt(Integer.parseInt(String.valueOf(throwAsChar)));
        } else {
            switch (throwAsChar) {
                case 'X':
                case 'x':
                    throwAttempt = new ThrowAttempt(10);
                    break;
                case '/':
                    //Bei einem Spare wird geprüft ob diesem ein anderer Wurf vorausging
                    if (frames.size() > 0 && frames.get(getLastFrameIndice()).getThrowAttempts().size() == 1) {
                        ArrayList<ThrowAttempt> lastThrowAttempts =
                                frames.get(getLastFrameIndice()).getThrowAttempts();
                        int lastThrowValue =
                                lastThrowAttempts.get(lastThrowAttempts.size() - 1).getKnockedDowns();
                        //Der Wert von / ergibt sich durch 10 - zuletzt in diesem Frame geworfen
                        throwAttempt = new ThrowAttempt(10 - lastThrowValue);
                    } else {
                        throw new InvalidCharacterCombinationException();
                    }
                    break;
                case '-':
                    throwAttempt = new ThrowAttempt(0);
                    break;
                default:
                    throw new InvalidCharacterException();
            }
        }
        return throwAttempt;
    }

    public void addThrowAttempt(ThrowAttempt throwAttempt) throws InvalidCharacterCombinationException, InvalidInputSizeException {
        //Wenn noch keine Frames vorhanden sind wird eine erste Frame gestartet
        if (frames.size() == 0) {
            Frame frame = new Frame();
            frame.addThrowAttempt(throwAttempt);
            frames.add(frame);
        } else {
            Frame currentFrame = frames.get(getLastFrameIndice());
            boolean containsStrike = currentFrame.containsStrike();
            boolean containsSpare = currentFrame.isSpare();
            int sizeThrowAttempts = currentFrame.getThrowAttempts().size();
            //Auf Grund der Bonuswurfregeln darf die 10. Frame je nach Würfen
            //bis zu 3 Würfe enthalten
            if (frames.size() > 9) {
                if (containsSpare && sizeThrowAttempts == 2) {
                    frames.get(9).addThrowAttempt(throwAttempt);
                } else if (currentFrame.getThrowAttempts().get(0).isStrike()
                        && sizeThrowAttempts < 3) {
                    frames.get(9).addThrowAttempt(throwAttempt);
                } else if (sizeThrowAttempts < 2) {
                    currentFrame.addThrowAttempt(throwAttempt);
                } else {
                    throw new InvalidInputSizeException();
                }
            } else {
                //Wenn noch ein Wurf zur Frame hinzugefügt werden muss
                if (sizeThrowAttempts < 2 && !containsStrike) {
                    //Wenn die Eingabe nicht valide im Bezug auf die Gesamtpunkte ist
                    if ((currentFrame.getLastThrowAttempt().getKnockedDowns()
                            + throwAttempt.getKnockedDowns()) > 10) {
                        throw new InvalidCharacterCombinationException();
                    }
                    currentFrame.addThrowAttempt(throwAttempt);
                } else {
                    Frame newFrame = new Frame();
                    newFrame.addThrowAttempt(throwAttempt);
                    frames.add(newFrame);
                }
            }
        }
    }

    public int getPointsOfFrame(Frame frame) throws InvalidInputSizeException, ValueOutOfRangeException {
        int points = 0;
        //Für die letzte Frame zählt die Summe aller Teilpunkte
        if (frames.indexOf(frame) == 9) {
            for (int i = 0; i < frame.getThrowAttempts().size(); i++) {
                points += frame.getThrowAttempts().get(i).getKnockedDowns();
            }
        } else {
            boolean strike = frame.containsStrike();
            boolean spare = frame.isSpare();
            if (spare || strike) {
                if (spare) {
                    //Im falles eines Spares muss ein weiterer Wurf existieren der addiert wird
                    if (frames.size() >= frames.indexOf(frame) + 1) {
                        points = 10 + frames.get(frames.indexOf(frame) + 1)
                                .getThrowAttempts().get(0).getKnockedDowns();
                    } else {
                        throw new InvalidInputSizeException();
                    }
                }
                //Im Falle eines Strikes müssen zwei weitere Würfe exisitieren die auf bis zu
                //zwei Frames verteilt sein dürfen und addiert werden
                else if (frames.size() >= frames.indexOf(frame) + 1) {
                    Frame nextFrame = frames.get(frames.indexOf(frame) + 1);
                    points = 10 + nextFrame.getThrowAttempts()
                            .get(0).getKnockedDowns();
                    //Würfe sind Teil einer Frame
                    if (nextFrame.getThrowAttempts().size() > 1) {
                        points += nextFrame.getThrowAttempts()
                                .get(1).getKnockedDowns();
                        //Der zweite Wurf liegt in einer neuen Frame
                    } else if (frames.size() > frames.indexOf(nextFrame) + 1){
                        nextFrame = frames.get(frames.indexOf(frame) + 2);
                        points += nextFrame.getThrowAttempts()
                                .get(0).getKnockedDowns();
                    } else {
                        throw new InvalidInputSizeException();
                    }
                } else {
                    throw new InvalidInputSizeException();
                }
            } else {
                //Falls weder Strike, noch Spare zählt die Summe der Würfe
                points = frame.getThrowAttempts().get(0).getKnockedDowns()
                        +frame.getThrowAttempts().get(1).getKnockedDowns();
            }
        }
        frame.setScore(points);
        return points;
    }

    public void computeEntirePoints() throws InvalidInputSizeException, ValueOutOfRangeException {
        for (Frame frame : frames) {
            entirePoints += getPointsOfFrame(frame);
        }
    }

    public int getEntirePoints() {
        return entirePoints;
    }


    public ArrayList<Frame> getFrames() {
        return frames;
    }

    public int getLastFrameIndice() {
        return frames.size() - 1;
    }
}
