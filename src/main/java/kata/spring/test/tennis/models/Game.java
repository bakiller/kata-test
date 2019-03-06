package kata.spring.test.tennis.models;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.web.context.WebApplicationContext.SCOPE_SESSION;

@Component
@Scope(SCOPE_SESSION)
public class Game {

    private final Map<GameSide, Score> result;
    private GameSide winner;
    private UUID uuid = UUID.randomUUID();

    public UUID getUuid() {
        return uuid;
    }

    public Game() {
        result = new HashMap<>();
    }

    public Score getFirstPlayerScore() {
        return getPlayerScore(GameSide.FIRST_PLAYER);
    }

    public Score getSecondPlayerScore() {
        return getPlayerScore(GameSide.SECOND_PLAYER);
    }

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

    public GameSide getWinner() {
        return winner;
    }

    public void setWinner(GameSide winner) {
        this.winner = winner;
    }

    public enum GameSide {
        FIRST_PLAYER, SECOND_PLAYER
    }

    static class FinishedGameException extends RuntimeException {
    }

}
