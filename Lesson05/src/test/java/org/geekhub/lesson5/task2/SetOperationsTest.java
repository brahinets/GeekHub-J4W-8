package org.geekhub.lesson5.task2;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SetOperationsTest {
    private SetOperations<Object> sets;

    @BeforeMethod
    public void setUp() {
        sets = new SetOperationsImpl<>();
    }

    @Test(dataProvider = "equalSets")
    public void testEquals(Set<Object> set1, Set<Object> set2) {
        boolean equals = sets.equals(set1, set2);

        Assert.assertEquals(set1, set2);
        Assert.assertTrue(equals);
    }

    @Test(dataProvider = "notEqualSets")
    public void testNotEquals(Set<Object> set1, Set<Object> set2) {
        boolean equals = sets.equals(set1, set2);

        Assert.assertNotEquals(set1, set2);
        Assert.assertFalse(equals);
    }

    @Test(dataProvider = "unionSets")
    public void testUnion(Set<Object> set1, Set<Object> set2, Set<Object> expectedResult) {
        Set<Object> result = sets.union(set1, set2);

        Assert.assertEquals(result, expectedResult);
    }

    @Test(dataProvider = "subtractSets")
    public void testSubtract(Set<Object> set1, Set<Object> set2, Set<Object> expectedResult) {
        Set<Object> result = sets.subtract(set1, set2);

        Assert.assertEquals(result, expectedResult);
    }

    @Test(dataProvider = "intersectSets")
    public void testIntersect(Set<Object> set1, Set<Object> set2, Set<Object> expectedResult) {
        Set<Object> result = sets.intersect(set1, set2);

        Assert.assertEquals(result, expectedResult);
    }

    @Test(dataProvider = "symmetricSubtractSets")
    public void testSymmetricSubtract(Set<Object> set1, Set<Object> set2, Set<Object> expectedResult) {
        Set<Object> result = sets.symmetricSubtract(set1, set2);

        Assert.assertEquals(result, expectedResult);
    }

    @DataProvider
    public static Object[][] equalSets() {
        return new Object[][]{
            {set("3", "1", "2"), set("1", "3", "2")},
            {set("3", "1", "2"), set("1", "3", "2")},
            {set(), set()},
            {set(null, null), set(null, null)},
            {null, null}
        };
    }

    @DataProvider
    public static Object[][] notEqualSets() {
        return new Object[][]{
            {set("3", "1"), set("1", "3", "2")},
            {set(), set("1")},
            {set("1"), set()},
            {set(null, null), set()},
            {set(), set(null, null)},
            {null, set()},
            {set(), null}
        };
    }

    @DataProvider
    public static Object[][] unionSets() {
        return new Object[][]{
            {set("3", "1", "2"), set("1", "3", "2"), set("1", "2", "3")},
            {set("1", "2"), set("1", "3", "2"), set("1", "2", "3")},
            {set("1"), set("1", "3", "2"), set("1", "2", "3")},
            {set(), set("1", "3", "2"), set("1", "2", "3")},
            {set("1"), set("3", "2"), set("1", "2", "3")},
            {set("1"), set("2"), set("1", "2")},
            {set("1"), set(), set("1")},
            {set(), set(), set()},
        };
    }

    @DataProvider
    public static Object[][] subtractSets() {
        return new Object[][]{
            {set("3", "1", "2"), set("3", "2"), set("1")},
            {set("3", "1", "2"), set("3"), set("2", "1")},
            {set("1", "2"), set(), set("1", "2")},
            {set("1"), set("3", "1", "2"), set()},
            {set("1"), set("3", "2"), set("1")},
            {set(), set("3", "2"), set()},
            {set(), set("2"), set()},
            {set(), set(), set()},
        };
    }

    @DataProvider
    public static Object[][] intersectSets() {
        return new Object[][]{
            {set("3", "1", "2"), set("3", "2"), set("3", "2")},
            {set("3", "1", "2"), set("3"), set("3")},
            {set("3", "1", "2"), set(), set()},
            {set("3", "2"), set("1"), set()},
            {set("1", "2"), set("1"), set("1")},
            {set("1"), set("3", "1", "2"), set("1")},
            {set("1"), set("3", "2"), set()},
            {set(), set("3", "2"), set()},
            {set(), set(), set()},
        };
    }

    @DataProvider
    public static Object[][] symmetricSubtractSets() {
        return new Object[][]{
            {set("3", "1", "2"), set("3", "2"), set("1")},
            {set("3", "1", "2"), set("3"), set("1", "2")},
            {set("3", "1", "2"), set(), set("3", "1", "2")},
            {set("3", "2"), set("1"), set("3", "1", "2")},
            {set("1", "2"), set("1"), set("2")},
            {set("1"), set("3", "1", "2"), set("3", "2")},
            {set("1"), set("3", "2"), set("3", "1", "2")},
            {set(), set("3", "2"), set("3", "2")},
            {set(), set(), set()},
        };
    }

    private static Set<Object> set(Object... elements) {
        Set<Object> objects = new HashSet<>(Arrays.asList(elements));
        return Collections.unmodifiableSet(objects);
    }
}
