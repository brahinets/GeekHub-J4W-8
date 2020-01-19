package org.geekhub.lesson15.annotationconfig.printer;

import org.geekhub.lesson15.annotationconfig.logger.LoggerService;
import org.springframework.stereotype.Service;

@Service
class PrimeNumberPrinterImpl implements PrimeNumberPrinter {
    private final LoggerService logger;

    PrimeNumberPrinterImpl(LoggerService logger) {
        this.logger = logger;
    }

    @Override
    public void printPrimeNumbers(final int count) {
        for (int counter = 0, num = 3; counter < count; num += 2) {
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
