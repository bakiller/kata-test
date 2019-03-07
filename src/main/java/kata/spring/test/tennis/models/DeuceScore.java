package kata.spring.test.tennis.models;

/**
 * The Winnig score.
 */
public class DeuceScore implements Score {

    private boolean advatage;

    @Override
    public boolean isWinningScore() {
        return false;
    }

    @Override
    public Score nextScore() {
        if (isDeuce()) {
            this.advatage = true;
            return this;
        }
        Score other = currentGame().getOtherScore(this);
        if (advatage || other instanceof IntegerScore) {
            return WinnigScore.getInstance();
        }
        if (other instanceof DeuceScore && ((DeuceScore) other).advatage) {
            ((DeuceScore) other).advatage = false;
            return this;
        }
        throw new UnsupportedOperationException("This case is not treated");
    }

    private boolean isDeuce() {
        try {
            DeuceScore other = (DeuceScore) currentGame().getOtherScore(this);
            return !this.advatage && !other.advatage;
        } catch (ClassCastException ignored) {
            return false;
        }
    }

    @Override
    public String getDesignation() {
        return advatage ? "ADV" : isDeuce() ? "DEUCE" : "40";
    }
}
