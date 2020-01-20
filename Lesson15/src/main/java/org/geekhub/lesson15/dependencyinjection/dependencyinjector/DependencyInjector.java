package org.geekhub.lesson15.dependencyinjection.dependencyinjector;

public interface DependencyInjector extends AutoCloseable {
    <T> T getBean(Class<T> beanType);
}
