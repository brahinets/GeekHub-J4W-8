package org.geekgub.lesson1.task1;

import org.geekhub.lesson1.task1.PrimeNumbersCalculator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PrimeNumbersCalculatorTest {

    @Test(dataProvider = "primeNumbers")
    public void shouldFindAllPrimeNumbersLess(int upperBoundExclusive, int[] expectedPrimeNumbers) {
        int[] actualPrimeNumbers = PrimeNumbersCalculator.findPrimeNumbersLessThan(upperBoundExclusive);

        Assert.assertEquals(actualPrimeNumbers, expectedPrimeNumbers);
    }

    @DataProvider
    private Object[][] primeNumbers() {
        return new Object[][]{
                {-5, new int[0]},
                {0, new int[0]},
                {1, new int[0]},
                {2, new int[0]},
                {3, new int[]{2}},
                {5, new int[]{2, 3}},
                {19, new int[]{2, 3, 5, 7, 11, 13, 17}},
                {20, new int[]{2, 3, 5, 7, 11, 13, 17, 19}}
        };
    }

}