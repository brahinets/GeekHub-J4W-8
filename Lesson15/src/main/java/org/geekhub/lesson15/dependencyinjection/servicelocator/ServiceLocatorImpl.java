package org.geekhub.lesson15.dependencyinjection.servicelocator;

import org.geekhub.lesson15.dependencyinjection.application.logger.LoggerService;
import org.geekhub.lesson15.dependencyinjection.dependencyinjector.annotations.Bean;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ServiceLocatorImpl implements ServiceLocator {
    private final Map<Class<?>, Object> beanStorage;
    private final LoggerService logger;

    public ServiceLocatorImpl(String basePackage, LoggerService logger) {
        this.logger = logger;
        beanStorage = new HashMap<>();
        lookupForBeans(basePackage);
    }

    @Override
    public void lookupForBeans(String basePackage) {
        final Reflections reflections = new Reflections(basePackage, getClass().getClassLoader());
        final Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Bean.class);
        try {
            for (Class<?> bean : annotated) {
                beanStorage.put(bean, bean.getDeclaredConstructor().newInstance());
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            logger.print("Unexpected error: " + e.getMessage());
        }
    }

    @Override
    public Collection<Object> getAllBeans() {
        return beanStorage.values();
    }

    @Override
    public <T> T getBean(Class<T> beanType) {
        for (Map.Entry<Class<?>, Object> entry : beanStorage.entrySet()) {
            if (beanType.isAssignableFrom(entry.getKey())) {
                return (T) entry.getValue();
            }
        }
        throw new IllegalStateException("Bean with type=" + beanType + " has not been found");
    }
}
