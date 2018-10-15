package org.geekgub.lesson1.task3;

import org.geekhub.lesson1.task3.MatrixUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MatrixUtilsTest {

    @Test
    public void shouldCreateMatrixWithSpecifiedDimension() {
        final int rowsCount = 6;
        final int columnsCount = 5;

        int[][] matrix = MatrixUtils.createMatrix(rowsCount, columnsCount);

        Assert.assertEquals(matrix.length, rowsCount);
        for (int[] row : matrix) {
            Assert.assertEquals(row.length, columnsCount);
        }
    }

    @Test
    public void shouldFillMatrixIncrementally() {
        int[][] matrix = MatrixUtils.createMatrix(6, 5);

        MatrixUtils.fillMatrix(matrix, 1);

        int[][] expectedMatrix = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25},
                {26, 27, 28, 29, 30},
        };
        Assert.assertEquals(matrix, expectedMatrix);
    }

    @Test
    public void shouldFindCellsValuesWithIntegerSquareRootValueInReverseOrder() {
        int[][] matrix = MatrixUtils.createMatrix(6, 5);
        MatrixUtils.fillMatrix(matrix, 1);

        int[] actualRoots = MatrixUtils.findCellsWithIntegerSquareRootValue(matrix);

        int[] expectedRoots = {25, 16, 9, 4, 1};
        Assert.assertEquals(actualRoots, expectedRoots);
    }

    @Test(dataProvider = "sumRootsOfCellsWithIntegerSquareRootValueDataProvider")
    public void shouldSumRootsOfCellsWithIntegerSquareRootValue(int rows, int cols, int initValue, int expectedResult) {
        int[][] matrix = MatrixUtils.createMatrix(rows, cols);
        MatrixUtils.fillMatrix(matrix, initValue);

        int sum = MatrixUtils.sumRootsOfCellsWithIntegerSquareRoot(matrix);

        Assert.assertEquals(sum, expectedResult);
    }

    @DataProvider
    private Object[][] sumRootsOfCellsWithIntegerSquareRootValueDataProvider() {
        return new Object[][]{
                {0, 0, 777, 0},
                {1, 1, 900, 30},
                {1, 10, 5, 3},
                {6, 5, 1, 5 + 4 + 3 + 2 + 1},
                {6, 5, 2, 5 + 4 + 3 + 2},
        };
    }
}