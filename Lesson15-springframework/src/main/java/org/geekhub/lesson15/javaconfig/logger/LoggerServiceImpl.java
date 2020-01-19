package org.geekhub.lesson15.javaconfig.logger;

public class LoggerServiceImpl implements LoggerService {
    private final String loggerPrefix;

    public LoggerServiceImpl(String loggerPrefix) {
        this.loggerPrefix = loggerPrefix;
    }

    @Override
    public void print(Object object) {
        System.out.println("[" + loggerPrefix + "]: " + object);
    }

    public void init() {
        System.out.println("Initialized.");
    }

    private void destroy() {
        System.out.println("Destroyed.");
    }
}
