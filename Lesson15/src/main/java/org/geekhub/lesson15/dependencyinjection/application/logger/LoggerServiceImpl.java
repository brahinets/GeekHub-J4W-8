package org.geekhub.lesson15.dependencyinjection.application.logger;

import org.geekhub.lesson15.dependencyinjection.dependencyinjector.annotations.Bean;
import org.geekhub.lesson15.dependencyinjection.dependencyinjector.annotations.Destroy;
import org.geekhub.lesson15.dependencyinjection.dependencyinjector.annotations.Init;

@Bean
public class LoggerServiceImpl implements LoggerService {
    @Override
    public void print(Object object) {
        System.out.println(object);
    }

    @Init
    public void init() {
        System.out.println("Initialized.");
    }

    @Destroy
    private void destroy() {
        System.out.println("Destroyed.");
    }
}
