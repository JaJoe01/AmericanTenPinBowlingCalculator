package de.klosebrothers.americantenpinbowling;

import java.util.ArrayList;

public class Frame {
    private final ArrayList<ThrowAttempt> throwAttempts;
    private boolean spare;
    private int score;

    //Stellt genau eine Frame / einen turn da
    public Frame() {
        spare = false;
        score = 0;
        throwAttempts  = new ArrayList<>();
    }

    public boolean isSpare() {
        return spare;
    }

    //Prüft nur die ersten beiden Würfe
    //da ja nur diese interessant für die Berechnung sind
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

    public ArrayList<ThrowAttempt> getThrowAttempts() {
        return throwAttempts;
    }

    public ThrowAttempt getLastThrowAttempt() {
        return throwAttempts.get(throwAttempts.size() - 1);
    }

    public void setScore(int points) throws ValueOutOfRangeException {
        if (points >= 0 && points <= 30) {
            this.score = points;
        } else {
            throw new ValueOutOfRangeException();
        }
    }
}
