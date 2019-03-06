package kata.spring.test.tennis.models;

/**
 * The Winnig score.
 */
public class WinnigScore implements Score {

    @Override
    public boolean isWinningScore() {
        return true;
    }

    @Override
    public Score nextScore() {
        throw new Game.FinishedGameException();
    }

    @Override
    public String getDesignation() {
        return "0";
    }
}
