package kata.spring.test.tennis.service;

import kata.spring.test.tennis.config.ApplicationContextHolder;
import kata.spring.test.tennis.config.ClientBeanFactoryProcessor;
import kata.spring.test.tennis.models.Game;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * The Game service.
 */
@Service
public abstract class GameService {


    /**
     * this abstract method will return the bean Game, depending on the session.
     *
     * @return the game active in this session
     */
    @Lookup
    public abstract Game getGame();


    /**
     * adds a point to the player of the selected side.
     *
     * @param side the selected side
     * @return the game active in this session
     */
    public Game addPoint(Game.GameSide side) {
        Game game = getGame();
        game.incrementPlayerScore(side);
        return game;
    }

    public void resetGame() {
        ApplicationContextHolder
                .getApplicationContext()
                .getBeansOfType(ClientBeanFactoryProcessor.class)
        .values().forEach(clientBeanFactoryProcessor -> clientBeanFactoryProcessor.getScope().remove());
    }
}
