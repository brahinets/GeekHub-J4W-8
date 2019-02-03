package org.geekhub.lesson15.dependencyinjection.application.printer;

import org.geekhub.lesson15.dependencyinjection.application.logger.LoggerService;
import org.geekhub.lesson15.dependencyinjection.dependencyinjector.annotations.Bean;

import javax.inject.Inject;

@Bean
public class PrimeNumberPrinterImpl implements PrimeNumberPrinter {
    @Inject private LoggerService logger;

    @Override
    public void printPrimeNumbers(final int count) {
        for (int counter = 0, num = 3; counter < count; num+=2) {
            if (isPrimeNumber(num)) {
                counter++;
                logger.print(num);
            }
        }
    }

    private boolean isPrimeNumber(int number) {
        for (int i = 2; i < Math.sqrt(number) + 1; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
