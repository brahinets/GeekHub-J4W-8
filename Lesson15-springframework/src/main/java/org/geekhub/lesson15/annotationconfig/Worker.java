package org.geekhub.lesson15.annotationconfig;

import org.geekhub.lesson15.annotationconfig.printer.PrimeNumberPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Worker {
    private final PrimeNumberPrinter primeNumberPrinter;

    @Autowired
    public Worker(PrimeNumberPrinter primeNumberPrinter) {
        this.primeNumberPrinter = primeNumberPrinter;
    }

    public void printPrimeNumbers(int number) {
        primeNumberPrinter.printPrimeNumbers(number);
    }
}
