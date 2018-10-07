package org.geekgub.lesson1.task1;

import org.geekhub.lesson1.task1.PrimeNumbersCalculator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PrimeNumbersCalculatorTest {

    @Test(dataProvider = "noPrimeNumbers")
    public void shouldNotFindPrimeNumbersLess(int upperBoundExclusive, int[] expectedPrimeNumbers) {
        int[] actualPrimeNumbers = PrimeNumbersCalculator.findPrimeNumbersLessThan(upperBoundExclusive);

        Assert.assertEquals(actualPrimeNumbers, expectedPrimeNumbers);
    }

    @Test(dataProvider = "hasPrimeNumbers")
    public void shouldFindPrimeNumbersLess(int upperBoundExclusive, int[] expectedPrimeNumbers) {
        int[] actualPrimeNumbers = PrimeNumbersCalculator.findPrimeNumbersLessThan(upperBoundExclusive);

        Assert.assertEquals(actualPrimeNumbers, expectedPrimeNumbers);
    }

    @DataProvider
    private Object[][] noPrimeNumbers() {
        return new Object[][]{
                {-5, new int[0]},
                {0, new int[0]},
                {1, new int[0]},
                {2, new int[0]},
        };
    }

    @DataProvider
    private Object[][] hasPrimeNumbers() {
        return new Object[][]{
                {3, new int[]{2}},
                {5, new int[]{2, 3}},
                {20, new int[]{2, 3, 5, 7, 11, 13, 17, 19}},
        };
    }

}