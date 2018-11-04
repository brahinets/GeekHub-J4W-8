package org.geekhub.lesson4.task1;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class NumberParserTest {

    @DataProvider
    public static Object[][] notNumberString() {
        return new Object[][]{
            {null},
            {"+"},
            {"-"},
            {""},
            {" "},
            {"    "},
            {"\r"},
            {"\n"},
            {"\t"},
            {"\r\n\t"},
            {"!@#$%"},
            {"-+=12"},
            {"-!123"},
            {"=123"},
            {"12+3"},
            {"abc"},
            {"!qwe"},
            {"1qwe"}
        };
    }

    @DataProvider
    public static Object[][] numberString() {
        return new Object[][]{
            {"-123", -123},
            {"-012", -12},
            {"-0", 0},
            {"0", 0},
            {"+0", 0},
            {"+1", 1},
            {"01", 1},
            {"10", 10},
            {"123", 123},
            {"1234", 1234},
        };
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void shouldThrowException_whenInputStringIsNull() {
        NumberParser.parse(null);
    }

    @Test(expectedExceptions = NumberFormatException.class)
    public void shouldThrowException_whenInputStringIsEmpty() {
        NumberParser.parse("");
    }

    @Test(dataProvider = "notNumberString", expectedExceptions = NumberFormatException.class)
    public void shouldThrowException_whenInputStringIsNotANumber(String notNumberString) {
        NumberParser.parse(notNumberString);
    }

    @Test(dataProvider = "numberString")
    public void shouldParseNumber_whenInputStringIsANumber(String numberToParse, int expectedValue) {
        int actualValue = NumberParser.parse(numberToParse);

        Assert.assertEquals(actualValue, expectedValue);
    }
}
