package kata.spring.test.tennis.config;

public class SetScope extends ClientScope {
    @Override
    protected String getHeaderName() {
        return "set-id";
    }
}
