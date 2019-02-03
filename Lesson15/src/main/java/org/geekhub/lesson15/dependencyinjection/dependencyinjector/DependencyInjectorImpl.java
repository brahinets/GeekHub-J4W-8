package org.geekhub.lesson15.dependencyinjection.dependencyinjector;

import org.geekhub.lesson15.dependencyinjection.application.logger.LoggerService;
import org.geekhub.lesson15.dependencyinjection.application.logger.LoggerServiceImpl;
import org.geekhub.lesson15.dependencyinjection.dependencyinjector.annotations.Destroy;
import org.geekhub.lesson15.dependencyinjection.dependencyinjector.annotations.Init;
import org.geekhub.lesson15.dependencyinjection.servicelocator.ServiceLocator;
import org.geekhub.lesson15.dependencyinjection.servicelocator.ServiceLocatorImpl;

import javax.inject.Inject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

public class DependencyInjectorImpl implements DependencyInjector {
    private final ServiceLocator locator;
    private final LoggerService logger;

    public DependencyInjectorImpl(String basePackage) {
        logger = new LoggerServiceImpl();
        locator = new ServiceLocatorImpl(basePackage, logger);
        injectAll();
    }

    public DependencyInjectorImpl(Class<?> baseClass) {
        this(baseClass.getPackage().getName());
    }

    @Override
    public <T> T getBean(Class<T> beanType) {
        return locator.getBean(beanType);
    }

    @Override
    public void close() {
        locator.getAllBeans().forEach(this::destroy);
    }

    private void injectAll() {
        final Collection<Object> beans = locator.getAllBeans();
        for (Object bean : beans) {
            inject(bean);
            init(bean);
        }
    }

    private void inject(Object bean) {
        for (Field field : bean.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Inject.class)) {
                try {
                    final Object injectable = locator.getBean(field.getType());
                    if (null != injectable) {
                        field.setAccessible(true);
                        field.set(bean, injectable);
                    }
                } catch (IllegalAccessException e) {
                    logger.print("Unexpected error on bean inject action: " + e.getMessage());
                }
            }
        }
    }

    private void init(Object bean) {
        for (Method method : bean.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Init.class)) {
                try {
                    method.setAccessible(true);
                    method.invoke(bean);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    logger.print("Unexpected error on bean init action: " + e.getMessage());
                }
            }
        }
    }

    private void destroy(Object bean) {
        for (Method method : bean.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Destroy.class)) {
                try {
                    method.setAccessible(true);
                    method.invoke(bean);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    logger.print("Unexpected error on bean destroy action: " + e.getMessage());
                }
            }
        }
    }
}
