package org.geekhub.lesson15.annotationconfig.logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
class LoggerServiceImpl implements LoggerService {
    private final String loggerPrefix;

    LoggerServiceImpl(@Value("${logger.prefix}") String loggerPrefix) {
        this.loggerPrefix = loggerPrefix;
    }

    @Override
    public void print(Object object) {
        System.out.println("[" + loggerPrefix + "]: " + object);
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
