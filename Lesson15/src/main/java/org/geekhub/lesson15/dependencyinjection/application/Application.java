package org.geekhub.lesson15.dependencyinjection.application;

import org.geekhub.lesson15.dependencyinjection.dependencyinjector.DependencyInjector;
import org.geekhub.lesson15.dependencyinjection.dependencyinjector.DependencyInjectorImpl;

public class Application {
    public static void main(String[] args) throws Exception {
        try (DependencyInjector context = new DependencyInjectorImpl(Application.class)) {
            context.getBean(Worker.class).printPrimeNumbers(10);
        }
    }
}
