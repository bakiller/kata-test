package kata.spring.test.tennis.config;

public class GameScope extends ClientScope {


    @Override
    protected String getHeaderName() {
        return "game-id";
    }
}
