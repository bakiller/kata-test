package kata.spring.test.tennis.models;

/**
 * The Winnig score.
 */
public class WinnigScore implements Score {

    private static final WinnigScore INSTANCE = new WinnigScore();

    private WinnigScore() {

    }

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

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static WinnigScore getInstance() {
        return INSTANCE;
    }
}
