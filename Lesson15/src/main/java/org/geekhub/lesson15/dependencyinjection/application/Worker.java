package org.geekhub.lesson15.dependencyinjection.application;

import org.geekhub.lesson15.dependencyinjection.application.printer.PrimeNumberPrinter;
import org.geekhub.lesson15.dependencyinjection.dependencyinjector.annotations.Bean;

import javax.inject.Inject;

@Bean
public class Worker {
    @Inject private PrimeNumberPrinter primeNumberPrinter;

    public void printPrimeNumbers(int number) {
        primeNumberPrinter.printPrimeNumbers(number);
    }
}
