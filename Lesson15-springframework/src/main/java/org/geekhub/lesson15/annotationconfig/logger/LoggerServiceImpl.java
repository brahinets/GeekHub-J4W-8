package org.geekhub.lesson15.annotationconfig.logger;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class LoggerServiceImpl implements LoggerService {
    @Override
    public void print(Object object) {
        System.out.println(object);
    }

    @PostConstruct
    public void init() {
        System.out.println("Initialized.");
    }

    @PreDestroy
    private void destroy() {
        System.out.println("Destroyed.");
    }
}
