package org.geekhub.lesson15.xmlconfig.logger;

public class LoggerServiceImpl implements LoggerService {
    @Override
    public void print(Object object) {
        System.out.println(object);
    }

    public void init() {
        System.out.println("Initialized.");
    }

    private void destroy() {
        System.out.println("Destroyed.");
    }
}
