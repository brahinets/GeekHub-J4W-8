package org.geekhub.lesson15.javaconfig.logger;

public class LoggerServiceImpl implements LoggerService {
    @Override
    public void print(Object object) {
        System.out.println(String.valueOf(object));
    }

    public void init() {
        System.out.println("Initialized.");
    }

    private void destroy() {
        System.out.println("Destroyed.");
    }
}
