package kata.spring.test.tennis.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class ClientBeanFactoryProcessor implements BeanFactoryPostProcessor {

    private ClientScope scope;

    public ClientScope getScope() {
        return scope;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        this.scope = new ClientScope();
        beanFactory.registerScope("client", this.scope);
    }
}
