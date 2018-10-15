package org.geekhub.lesson2.task1;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class StringUtilsTest {

    @Test
    public void toggleCaseShouldReturnNullForNullInput() {
        String result = StringUtils.toggleCase(null);

        Assert.assertNull(result);
    }

    @Test
    public void toggleCaseShouldReturnEmptyStringForEmptyInput() {
        String result = StringUtils.toggleCase("");

        Assert.assertEquals(result, "");
    }

    @Test(dataProvider = "startWithNonEnglishLetter")
    public void toggleCaseShouldReturnStringAsIsWhenFirstCharIsNotEnglishLetter(String str) {
        String result = StringUtils.toggleCase(str);

        Assert.assertEquals(result, str);
    }

    @Test(dataProvider = "startWithLowerCaseData")
    public void toggleCaseShouldUpperCaseWhenFirstLetterIsLowerCase(String str, String expectedResult) {
        String result = StringUtils.toggleCase(str);

        Assert.assertEquals(result, expectedResult);
    }

    @Test(dataProvider = "startWithUpperCaseData")
    public void toggleCaseShouldUpperCaseWhenFirstLetterIsUpperCase(String str, String expectedResult) {
        String result = StringUtils.toggleCase(str);

        Assert.assertEquals(result, expectedResult);
    }

    @DataProvider
    private static Object[][] startWithNonEnglishLetter() {
        return new Object[][]{
                {"123abc"},
                {"123abc"},
                {" abc"},
                {"-ABC"},
                {"~!@#$%^&*()_+"}
        };
    }

    @DataProvider
    private static Object[][] startWithLowerCaseData() {
        return new Object[][]{
                {"abc", "ABC"},
                {"aBC", "ABC"},
                {"aBc", "ABC"}
        };
    }

    @DataProvider
    private static Object[][] startWithUpperCaseData() {
        return new Object[][]{
                {"Abc", "abc"},
                {"ABC", "abc"},
                {"AbC", "abc"}
        };
    }
}