package org.geekhub.lesson2.task4;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class PolynomialSolverTest {

    private PolynomialSolver polynomialSolver;

    @BeforeMethod
    public void setUp() {
        polynomialSolver = new PolynomialSolver();
    }

    @Test
    public void shouldReturnNoRoots() {
        Optional<Set<Integer>> roots = polynomialSolver.findRoots(3, 2, 2);

        Assert.assertFalse(roots.isPresent());
    }

    @Test
    public void shouldReturnOneRoots() {
        Optional<Set<Integer>> roots = polynomialSolver.findRoots(1, -4, 4);

        Assert.assertTrue(roots.isPresent());
        Assert.assertEquals(roots.get(), new HashSet<>(Collections.singletonList(2)));
    }

    @Test
    public void shouldReturnTwoRoots() {
        Optional<Set<Integer>> roots = polynomialSolver.findRoots(1, -7, 6);

        Assert.assertTrue(roots.isPresent());
        Assert.assertEquals(roots.get(), new HashSet<>(Arrays.asList(6, 1)));
    }
}