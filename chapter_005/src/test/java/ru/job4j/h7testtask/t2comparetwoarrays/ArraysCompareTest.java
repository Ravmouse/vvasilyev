package ru.job4j.h7testtask.t2comparetwoarrays;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test class.
 */
public class ArraysCompareTest {
    /**
     * Using the first method.
     */
    @Test
    public void whenTwoArraysAreEqualThenReturnTrueFirstMeth() {
        Integer[] one = {2, 2, 1};
        Integer[] two = {2, 1, 2};
        ArraysCompare ar = new ArraysCompare();
        assertThat(ar.compareTwoArraysFirst(one, two), is(true));
    }

    /**
     * Using the first method.
     */
    @Test
    public void whenTwoArraysAreNotEqualThenReturnFalseFirstMeth() {
        Integer[] one = {2, 2, 1};
        Integer[] two = {2, 1, 3};
        ArraysCompare ar = new ArraysCompare();
        assertThat(ar.compareTwoArraysFirst(one, two), is(false));
    }

    /**
     * Using the second method.
     */
    @Test
    public void whenTwoArraysAreEqualThenReturnTrueSecondMeth() {
        Integer[] one = {3, 2, 1};
        Integer[] two = {2, 1, 3};
        ArraysCompare ar = new ArraysCompare();
        assertThat(ar.compareTwoArraysSecond(one, two), is(true));
    }

    /**
     * Using the second method.
     */
    @Test
    public void whenTwoArraysAreNotEqualThenReturnFalseSecondMeth() {
        Integer[] one = {3, 2, 1};
        Integer[] two = {2, 1, 2};
        ArraysCompare ar = new ArraysCompare();
        assertThat(ar.compareTwoArraysSecond(one, two), is(false));
    }

    /**
     * Using the third method.
     */
    @Test
    public void testingTheThirdMethod() {
        int[] a = {2, -4, 56, 1, -9, 7, 45, -123};
        int[] b = {45, -9, 7, -123, -4, 2, 56, 1};
        ArraysCompare ar = new ArraysCompare();
        assertThat(ar.compareTwoArraysThird(a, b), is(true));

        a[0] = 20;
        assertThat(ar.compareTwoArraysThird(a, b), is(false));
    }
}