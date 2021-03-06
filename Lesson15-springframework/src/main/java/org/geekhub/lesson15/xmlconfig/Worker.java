package org.geekhub.lesson15.xmlconfig;

import org.geekhub.lesson15.xmlconfig.printer.PrimeNumberPrinter;

public class Worker {
    private PrimeNumberPrinter primeNumberPrinter;

    public Worker(PrimeNumberPrinter primeNumberPrinter) {
        this.primeNumberPrinter = primeNumberPrinter;
    }

    public void printPrimeNumbers(int number) {
        primeNumberPrinter.printPrimeNumbers(number);
    }
}
