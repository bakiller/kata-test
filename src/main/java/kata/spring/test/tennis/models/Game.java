package kata.spring.test.tennis.models;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * The Game.
 */
@Component
@Scope(scopeName = "game")
public class Game {
    private final Map<GameSide, Score> result;
    private GameSide winner;
    private UUID uuid = UUID.randomUUID();

    /**
     * Gets uuid.
     *
     * @return the uuid
     */
    public UUID getUuid() {
        return uuid;
    }

    /**
     * Instantiates a new Game.
     */
    public Game() {
        result = new HashMap<>();
        getFirstPlayerScore();
        getSecondPlayerScore();
    }

    /**
     * Gets first player score.
     *
     * @return the first player score
     */
    public Score getFirstPlayerScore() {
        return getPlayerScore(GameSide.FIRST_PLAYER);
    }

    /**
     * Gets second player score.
     *
     * @return the second player score
     */
    public Score getSecondPlayerScore() {
        return getPlayerScore(GameSide.SECOND_PLAYER);
    }

    /**
     * Increment player score.
     *
     * @param selectedSide the selected side
     */
    public void incrementPlayerScore(GameSide selectedSide) {
        assertNotFinished();
        Score score = result.compute(selectedSide,
                (gameSide, currentScore) -> Optional
                        .ofNullable(currentScore)
                        .map(Score::nextScore)
                        .orElseGet(Score::initialScore));
        if (score.isWinningScore()) {
            result.clear();
            winner = selectedSide;
        }
    }

    private void assertNotFinished() {
        if (winner != null) {
            throw new FinishedGameException();
        }
    }

    private Score getPlayerScore(GameSide selectedSide) {
        return result.computeIfAbsent(selectedSide, side -> Score.initialScore());
    }

    /**
     * Gets other score.
     *
     * @param score the score
     * @return the other score
     */
    public Score getOtherScore(Score score) {
        if (!Objects.equals(score, getFirstPlayerScore()) && !Objects.equals(score, getSecondPlayerScore())) {
            throw new IllegalArgumentException("incorrect score");
        }
        return getFirstPlayerScore() == score ? getSecondPlayerScore() : getFirstPlayerScore();
    }

    /**
     * Gets winner.
     *
     * @return the winner
     */
    public GameSide getWinner() {
        return winner;
    }

    /**
     * Sets winner.
     *
     * @param winner the winner
     */
    public void setWinner(GameSide winner) {
        this.winner = winner;
    }

    /**
     * The enum Game side.
     */
    public enum GameSide {
        /**
         * First player game side.
         */
        FIRST_PLAYER,
        /**
         * Second player game side.
         */
        SECOND_PLAYER
    }

    /**
     * The type Finished game exception.
     */
    static class FinishedGameException extends RuntimeException {
    }

}
