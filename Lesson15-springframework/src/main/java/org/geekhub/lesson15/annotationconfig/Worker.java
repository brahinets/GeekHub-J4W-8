package org.geekhub.lesson15.annotationconfig;

import org.geekhub.lesson15.annotationconfig.printer.PrimeNumberPrinter;
import org.springframework.stereotype.Component;

@Component
class Worker {
    private final PrimeNumberPrinter primeNumberPrinter;

    Worker(PrimeNumberPrinter primeNumberPrinter) {
        this.primeNumberPrinter = primeNumberPrinter;
    }

    public void printPrimeNumbers(int number) {
        primeNumberPrinter.printPrimeNumbers(number);
    }
}
