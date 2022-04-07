package de.klosebrothers.americantenpinbowling;

import java.util.ArrayList;

public class Frame {
    private final ArrayList<ThrowAttempt> throwAttempts = new ArrayList<>();
    private boolean spare;
    private int score;

    public Frame() {
        spare = false;
        score = 0;
    }

    public boolean isSpare() {
        return spare;
    }

    //Prüft nur die ersten beiden Würfe
    public boolean containsStrike() {
        for (int i = 0; i < 2 && i < throwAttempts.size(); i++) {
            if (throwAttempts.get(i).isStrike()) {
                return true;
            }
        }
        return false;
    }

    public void addThrowAttempt(ThrowAttempt throwAttempt) {
        throwAttempts.add(throwAttempt);
        if(throwAttempts.size() == 2) {
            spare = throwAttempts.get(0).getKnockedDowns()
                    + throwAttempts.get(1).getKnockedDowns() == 10;
        }
    }

    public void addScore(int score) throws ValueOutOfRangeException {
        if (score >= 0 && score <= 30) {
            this.score += score;
        } else {
            throw new ValueOutOfRangeException();
        }
    }

    public ArrayList<ThrowAttempt> getThrowAttempts() {
        return throwAttempts;
    }

    public ThrowAttempt getLastThrowAttempt() {
        return throwAttempts.get(throwAttempts.size() - 1);
    }
}
