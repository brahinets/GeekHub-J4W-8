package org.geekhub.lesson2.task2;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class StringRepeaterTest {

    @Test(dataProvider = "stringRepeaterTestData")
    public void testStringAppend(String string, int times, String expectedResult) {
        StringRepeater stringRepeater = new StringRepeaterStringAppendImpl();

        String result = stringRepeater.repeat(string, times);

        Assert.assertEquals(result, expectedResult);
    }

    @Test(dataProvider = "stringRepeaterTestData")
    public void testStringConcat(String string, int times, String expectedResult) {
        StringRepeater stringRepeater = new StringRepeaterStringConcatImpl();

        String result = stringRepeater.repeat(string, times);

        Assert.assertEquals(result, expectedResult);
    }

    @Test(dataProvider = "stringRepeaterTestData")
    public void testStringBuilderAppend(String string, int times, String expectedResult) {
        StringRepeater stringRepeater = new StringRepeaterStringBuilderImpl();

        String result = stringRepeater.repeat(string, times);

        Assert.assertEquals(result, expectedResult);
    }

    @Test(dataProvider = "stringRepeaterTestData")
    public void testStringBufferAppend(String str, int times, String expectedResult) {
        StringRepeater stringRepeater = new StringRepeaterStringBufferImpl();

        String result = stringRepeater.repeat(str, times);

        Assert.assertEquals(result, expectedResult);
    }

    @DataProvider
    private static Object[][] stringRepeaterTestData() {
        return new Object[][]{
                {"", 0, ""},
                {"1", 0, ""},
                {"1", 1, "1"},
                {"1", 2, "11"},
                {"11", 2, "1111"},
                {"111", 3, "111111111"},
                {"111", -1, ""}
        };
    }
}