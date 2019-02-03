package org.geekhub.lesson15.javaconfig;

import org.geekhub.lesson15.javaconfig.logger.LoggerService;
import org.geekhub.lesson15.javaconfig.logger.LoggerServiceImpl;
import org.geekhub.lesson15.javaconfig.printer.PrimeNumberPrinter;
import org.geekhub.lesson15.javaconfig.printer.PrimeNumberPrinterImpl;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Application {
    @Bean
    public Worker worker(PrimeNumberPrinter primeNumberPrinter) {
        return new Worker(primeNumberPrinter);
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public LoggerServiceImpl loggerService() {
        return new LoggerServiceImpl();
    }

    @Bean
    public PrimeNumberPrinter primeNumberPrinter(LoggerService loggerService) {
        return new PrimeNumberPrinterImpl(loggerService);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        context.getBean(Worker.class).printPrimeNumbers(10);
        context.close();
    }
}
