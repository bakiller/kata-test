package kata.spring.test.tennis.models;

public class IntegerScore implements Score {
    private Integer score;

    private IntegerScore(Integer score) throws UnsupportedIntegerScoreException {
        this.score = score + 15;
        if (this.score == 45) {
            this.score = 40;
        }
        if (this.score > 40) {
            throw new UnsupportedIntegerScoreException();
        }
    }

    public IntegerScore() {
        this.score = 0;
    }

    @Override
    public boolean isWinningScore() {
        return false;
    }

    @Override
    public Score nextScore() {
        try {
            return new IntegerScore(this.score);
        } catch (UnsupportedIntegerScoreException e) {
            return new WinnigScore();
        }
    }

    @Override
    public String getDesignation() {
        return String.valueOf(this.score);
    }

    private class UnsupportedIntegerScoreException extends Exception {
    }

}
