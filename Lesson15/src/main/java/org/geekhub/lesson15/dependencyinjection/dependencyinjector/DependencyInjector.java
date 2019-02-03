package org.geekhub.lesson15.dependencyinjection.dependencyinjector;

public interface DependencyInjector {
    <T> T getBean(Class<T> beanType);

    void close();
}
