package org.geekhub.lesson15.xmlconfig;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("org/geekhub/lesson15/xmlconfig/context.xml");
        context.getBean(Worker.class).printPrimeNumbers(10);
        context.close();
    }
}
