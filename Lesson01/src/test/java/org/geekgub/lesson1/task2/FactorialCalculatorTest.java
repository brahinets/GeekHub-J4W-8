package org.geekgub.lesson1.task2;

import org.geekhub.lesson1.task2.FactorialCalculator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FactorialCalculatorTest {

    @Test(dataProvider = "factorialCases")
    public void shouldCalculateNonNegativeFactorial(int factorialCount, int expectedFactorialValue) {
        int actualFactorialValue = FactorialCalculator.calculateFactorialOf(factorialCount);

        Assert.assertEquals(actualFactorialValue, expectedFactorialValue);
    }

    @DataProvider
    private Object[][] factorialCases() {
        return new Object[][]{
                {0, 1},
                {1, 1},
                {2, 2},
                {3, 6},
                {4, 24},
                {5, 120},
        };
    }
}