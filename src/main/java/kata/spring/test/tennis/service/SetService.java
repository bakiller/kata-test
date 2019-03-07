package kata.spring.test.tennis.service;

import kata.spring.test.tennis.config.ApplicationContextHolder;
import kata.spring.test.tennis.config.ClientBeanFactoryProcessor;
import kata.spring.test.tennis.models.Game;
import kata.spring.test.tennis.models.Set;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;

/**
 * The Set service.
 */
@Service
public abstract class SetService {


    /**
     * this abstract method will return the bean Set, depending on the session.
     *
     * @return the set active in this session
     */
    @Lookup
    public abstract Set getSet();


    /**
     * adds a point to the player of the selected side.
     *
     * @param side the selected side
     * @return the set active in this session
     */
    public Set addPoint(Game.GameSide side) {
        Set set = getSet();
        set.incrementPlayerScore(side);
        return set;
    }

    public void resetSet() {
        ApplicationContextHolder
                .getApplicationContext()
                .getBeansOfType(ClientBeanFactoryProcessor.class)
                .values().forEach(clientBeanFactoryProcessor -> clientBeanFactoryProcessor.getSetScope().remove());
    }
}
