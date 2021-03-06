package kata.spring.test.tennis.config;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class ClientScope implements Scope {

    private Map<String, Object> scopedObjects
            = Collections.synchronizedMap(new HashMap<>());
    private Map<String, Runnable> destructionCallbacks
            = Collections.synchronizedMap(new HashMap<>());

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        return getId()
                .flatMap(s ->
                        Optional.ofNullable(scopedObjects.computeIfAbsent(s, key -> objectFactory.getObject())))
                .orElseGet(objectFactory::getObject);
    }

    @Override
    public Object remove(String name) {
        destructionCallbacks.remove(name);
        return scopedObjects.remove(name);
    }

    public void remove() {
        getId().ifPresent(scopedObjects::remove);
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        destructionCallbacks.put(name, callback);
    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    private Optional<String> getId() {
        return Optional.ofNullable(
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest()
                .getHeader(getHeaderName()));
    }

    protected abstract String getHeaderName();
    @Override
    public String getConversationId() {
        return "client";
    }


}
