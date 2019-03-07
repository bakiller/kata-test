package kata.spring.test.tennis.models;

import org.springframework.web.context.ContextLoader;

import java.util.Objects;

public class IntegerScore implements Score {
    private Integer score = 0;



    private IntegerScore(Integer score) throws UnsupportedIntegerScoreException {
        this();
        this.score = score + 15;
        if (this.score > 40) {
            throw new UnsupportedIntegerScoreException();
        }
    }

    public IntegerScore() { }
    @Override
    public boolean isWinningScore() {
        return false;
    }

    @Override
    public Score nextScore() {
        try {
            return new IntegerScore(this.score);
        } catch (UnsupportedIntegerScoreException e) {
            return new DeuceScore();
        }
    }

    @Override
    public String getDesignation() {
        return String.valueOf(this.score);
    }

    private class UnsupportedIntegerScoreException extends Exception {
    }

}
