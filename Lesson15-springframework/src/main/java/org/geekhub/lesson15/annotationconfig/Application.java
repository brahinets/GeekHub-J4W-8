package org.geekhub.lesson15.annotationconfig;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("app.properties")
@ComponentScan("org.geekhub.lesson15.annotationconfig")
public class Application {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(Application.class)) {
            context.getBean(Worker.class).printPrimeNumbers(10);
        }
    }
}
