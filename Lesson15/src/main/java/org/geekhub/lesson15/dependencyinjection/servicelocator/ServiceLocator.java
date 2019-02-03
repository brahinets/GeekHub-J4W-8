package org.geekhub.lesson15.dependencyinjection.servicelocator;

import java.util.Collection;

public interface ServiceLocator {
    void lookupForBeans(String basePackage);

    <T> T getBean(Class<T> beanType);

    Collection<Object> getAllBeans();
}
