package kata.spring.test.tennis.models;

import kata.spring.test.tennis.service.GameService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * The Game.
 */
@Component
@Scope(scopeName = "set")
public class Set {
    private final GameService gameService;
    private Game.GameSide winner;
    private List<Game> finishedGames;
    private Game currentGame;
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
     *
     * @param gameService the game service
     */
    public Set(GameService gameService) {
        this.gameService = gameService;
        this.finishedGames = new ArrayList<>();
        resetGame();
    }

    private void resetGame() {
        gameService.resetGame();
        this.currentGame = gameService.getGame();
    }

    /**
     * Gets first player score.
     *
     * @return the first player score
     */
    public int getFirstPlayerScore() {
        return getPlayerScore(Game.GameSide.FIRST_PLAYER);
    }

    /**
     * Gets second player score.
     *
     * @return the second player score
     */
    public int getSecondPlayerScore() {
        return getPlayerScore(Game.GameSide.SECOND_PLAYER);
    }

    /**
     * Increment player score.
     *
     * @param selectedSide the selected side
     */
    public void incrementPlayerScore(Game.GameSide selectedSide) {
        assertNotFinished();
        currentGame.incrementPlayerScore(selectedSide);
        if (currentGame.getWinner() != null) {
            finishedGames.add(currentGame);
            resetGame();
        }
    }

    private void assertNotFinished() {
        if (winner != null) {
            throw new FinishedGameException();
        }
    }

    private int getPlayerScore(Game.GameSide selectedSide) {
        return (int) finishedGames.stream().filter(game -> Objects.equals(game.getWinner(), selectedSide)).count();
    }

    /**
     * Gets winner.
     *
     * @return the winner
     */
    public Game.GameSide getWinner() {
        return winner;
    }

    /**
     * Sets winner.
     *
     * @param winner the winner
     */
    public void setWinner(Game.GameSide winner) {
        this.winner = winner;
    }


    /**
     * The type Finished game exception.
     */
    static class FinishedGameException extends RuntimeException {
    }

}
