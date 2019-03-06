package kata.spring.test.tennis.models;

import java.util.Optional;
import java.util.function.Function;

public interface Score {

    boolean isWinningScore();
    Score nextScore();
    String getDesignation();

    static Score nextScoreOf(Score score) {
        return Optional.ofNullable(score).map(Score::nextScore).orElseGet(Score::initialScore);
    }

    static Score initialScore() {
        return new IntegerScore();
    }
}
