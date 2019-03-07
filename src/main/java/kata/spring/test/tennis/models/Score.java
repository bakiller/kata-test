package kata.spring.test.tennis.models;

import kata.spring.test.tennis.config.ApplicationContextHolder;

import java.util.Optional;

/**
 * The interface Score.
 */
public interface Score {

    /**
     * Is winning score boolean.
     *
     * @return the boolean
     */
    boolean isWinningScore();

    /**
     * Next score score.
     *
     * @return the score
     */
    Score nextScore();

    /**
     * Gets designation.
     *
     * @return the designation
     */
    String getDesignation();

    /**
     * Next score of score.
     *
     * @param score the score
     * @return the score
     */
    static Score nextScoreOf(Score score) {
        return Optional.ofNullable(score).map(Score::nextScore).orElseGet(Score::initialScore);
    }

    /**
     * Initial score score.
     *
     * @return the score
     */
    static Score initialScore() {
        return new IntegerScore();
    }

    /**
     * Current game game.
     *
     * @return the game
     */
    default Game currentGame() {
        return ApplicationContextHolder.getApplicationContext().getBean(Game.class);
    }
}
