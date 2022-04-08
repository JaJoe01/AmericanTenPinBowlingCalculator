package de.klosebrothers.americantenpinbowling;

public class ThrowAttempt {
    private final boolean strike;
    private final int knockedDowns;

    //Der ThrowAttempt stellt genau einen Wurf da
    public ThrowAttempt(int knockedDowns) throws ValueOutOfRangeException {
        boolean inRange = knockedDowns <= 10 && knockedDowns >= 0;
        if (inRange) {
            this.knockedDowns = knockedDowns;
            strike = isStrike();
        } else {
            throw new ValueOutOfRangeException();
        }
    }

    public boolean isStrike() {
        return knockedDowns == 10;
    }

    public int getKnockedDowns() {
        return knockedDowns;
    }
}
