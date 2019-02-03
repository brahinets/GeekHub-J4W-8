package org.geekhub.lesson15.annotationconfig;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.geekhub.lesson15.annotationconfig")
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        context.getBean(Worker.class).printPrimeNumbers(10);
        context.close();
    }
}
