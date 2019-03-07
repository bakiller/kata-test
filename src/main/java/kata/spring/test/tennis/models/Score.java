package kata.spring.test.tennis.models;

import kata.spring.test.tennis.config.ApplicationContextHolder;
import kata.spring.test.tennis.config.ClientBeanFactoryProcessor;

import java.util.Optional;

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

    default Game currentGame() {
        return ApplicationContextHolder.getApplicationContext().getBean(Game.class);
    }
}
