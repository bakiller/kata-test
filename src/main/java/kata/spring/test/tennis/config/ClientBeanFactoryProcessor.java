package kata.spring.test.tennis.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class ClientBeanFactoryProcessor implements BeanFactoryPostProcessor {

    private GameScope gameScope;
    private SetScope setScope;

    public GameScope getGameScope() {
        return gameScope;
    }

    public SetScope getSetScope() {
        return setScope;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        this.gameScope = new GameScope();
        this.setScope = new SetScope();
        beanFactory.registerScope("game", this.gameScope);
        beanFactory.registerScope("set", this.setScope);
    }
}
