package de.klosebrothers.americantenpinbowling;

import java.util.ArrayList;
import java.util.Arrays;

public class Game {
    private ArrayList<Frame> frames = new ArrayList<>();
    private int entirePoints;

    public Game() {

    }

    public Game(ArrayList<String> throwInput) throws InvalidCharacterException, ValueOutOfRangeException, InvalidCharacterCombinationException, InvalidInputSizeException {
        for (int i = 0; i < throwInput.size(); i++) {
            addThrowAttempt(identifyThrowAttempt(throwInput.get(i).charAt(0)));
        }
        computeEntirePoints();
    }
    /*
    public boolean isValid(ArrayList<String> inputAsStringArray) throws InvalidCharacterException, InvalidCharacterCombinationException {
        boolean inRange = inputAsStringArray.size() <= 21 && inputAsStringArray.size() >= 10;
        boolean allCharactersValid;
        ArrayList<Character> validCharacters = (ArrayList<Character>) Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'X', 'x', '-', '/');
        // 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 88, 120, 45, 47
        // 0-9, X, x, -, /
        for (String tempString : inputAsStringArray) {
            if (tempString.length() > 1) {
                char firstChar = tempString.charAt(0);
                char secondChar = tempString.charAt(1);
                if (!(firstChar == 49 && secondChar == 48)) {
                    //Fallunterscheidung welche Exception geworfen werden muss
                    if (validCharacters.contains(firstChar) && validCharacters.contains(secondChar)) {
                        throw new InvalidCharacterException();
                    } else {
                        throw new InvalidCharacterCombinationException();
                    }
                }
            } else {
                if (!validCharacters.contains(tempString.charAt(0))) {
                    throw new InvalidCharacterException();
                }
            }
            //switch (tempString)
        }
        return true;
    }
     */

    public ThrowAttempt identifyThrowAttempt(char throwAsChar)
            throws ValueOutOfRangeException, InvalidCharacterException, InvalidCharacterCombinationException {
        ThrowAttempt throwAttempt;
        ArrayList<Character> validNumbersAsChar =
                new ArrayList<>(Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9'));

        if (validNumbersAsChar.contains(throwAsChar)) {
            throwAttempt = new ThrowAttempt(Integer.parseInt(String.valueOf(throwAsChar)));
        } else {
            switch (throwAsChar) {
                case 'X':
                case 'x':
                    throwAttempt = new ThrowAttempt(10);
                    break;
                case '/':
                    if (frames.size() > 0 && frames.get(getLastFrameIndice()).getThrowAttempts().size() == 1) {
                        ArrayList<ThrowAttempt> lastThrowAttempts =
                                frames.get(getLastFrameIndice()).getThrowAttempts();
                        int lastThrowValue =
                                lastThrowAttempts.get(lastThrowAttempts.size() - 1).getKnockedDowns();
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

    public void addThrowAttempt(ThrowAttempt throwAttempt) throws InvalidCharacterCombinationException {
        if (frames.size() == 0) {
            Frame frame = new Frame();
            frame.addThrowAttempt(throwAttempt);
            frames.add(frame);
        } else {
            Frame currentFrame = frames.get(getLastFrameIndice());
            boolean containsStrike = currentFrame.containsStrike();
            boolean containsSpare = currentFrame.isSpare();
            int sizeThrowAttempts = currentFrame.getThrowAttempts().size();
            if (frames.size() > 9) {
                if (containsSpare && sizeThrowAttempts == 2) {
                    frames.get(9).addThrowAttempt(throwAttempt);
                } else if (currentFrame.getThrowAttempts().get(0).isStrike()
                        && sizeThrowAttempts < 3) {
                    frames.get(9).addThrowAttempt(throwAttempt);
                } else if (sizeThrowAttempts < 2) {
                    currentFrame.addThrowAttempt(throwAttempt);
                }
            } else {
                if (sizeThrowAttempts < 2 && !containsStrike) {
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

    public int getPointsOfFrame(Frame frame) throws InvalidInputSizeException {
        int points = 0;
        if (frames.indexOf(frame) == 9) {
            for (int i = 0; i < frame.getThrowAttempts().size(); i++) {
                points += frame.getThrowAttempts().get(i).getKnockedDowns();
            }
        } else {
            boolean strike = frame.containsStrike();
            boolean spare = frame.isSpare();
            if (spare || strike) {
                if (spare) {
                    if (frames.size() >= frames.indexOf(frame) + 1) {
                        points = 10 + frames.get(frames.indexOf(frame) + 1)
                                .getThrowAttempts().get(0).getKnockedDowns();
                    } else {
                        throw new InvalidInputSizeException();
                    }
                } else if (frames.size() > frames.indexOf(frame) + 2) {
                    Frame nextFrame = frames.get(frames.indexOf(frame) + 1);
                    points = 10 + nextFrame.getThrowAttempts()
                            .get(0).getKnockedDowns();
                    if (nextFrame.getThrowAttempts().size() > 1) {
                        points += nextFrame.getThrowAttempts()
                                .get(1).getKnockedDowns();
                    } else {
                        nextFrame = frames.get(frames.indexOf(frame) + 2);
                        points += nextFrame.getThrowAttempts()
                                .get(0).getKnockedDowns();
                    }
                } else {
                    throw new InvalidInputSizeException();
                }
            } else {
                points = frame.getThrowAttempts().get(0).getKnockedDowns()
                        +frame.getThrowAttempts().get(1).getKnockedDowns();
            }
        }
        return points;
    }

    public void computeEntirePoints() throws InvalidInputSizeException {
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
