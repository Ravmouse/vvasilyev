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
        assertThat(ar.areTwoArraysEqualFirst(one, two), is(true));
    }

    /**
     * Using the first method.
     */
    @Test
    public void whenTwoArraysAreNotEqualThenReturnFalseFirstMeth() {
        Integer[] one = {2, 2, 1};
        Integer[] two = {2, 1, 3};
        ArraysCompare ar = new ArraysCompare();
        assertThat(ar.areTwoArraysEqualFirst(one, two), is(false));
    }

    /**
     * Using the second method.
     */
    @Test
    public void whenTwoArraysAreEqualThenReturnTrueSecondMeth() {
        Integer[] one = {3, 2, 1};
        Integer[] two = {2, 1, 3};
        ArraysCompare ar = new ArraysCompare();
        assertThat(ar.areTwoArraysEqualSecond(one, two), is(true));
    }

    /**
     * Using the second method.
     */
    @Test
    public void whenTwoArraysAreNotEqualThenReturnFalseSecondMeth() {
        Integer[] one = {3, 2, 1};
        Integer[] two = {2, 1, 2};
        ArraysCompare ar = new ArraysCompare();
        assertThat(ar.areTwoArraysEqualSecond(one, two), is(false));
    }

    /**
     * Using the third method.
     */
    @Test
    public void testingTheThirdMethod() {
        int[] a = {-10, 35, 3, 8, 4, 6, 9, 1, 6, 8, 7, 12};
        int[] b = {6, 4, 1, 8, 3, 9, 6, 12, 8, -10, 35, 7};
        ArraysCompare ar = new ArraysCompare();
        assertThat(ar.areTwoArraysEqualThird(a, b), is(true));
        a[0] = 2;
        assertThat(ar.areTwoArraysEqualThird(a, b), is(false));
    }

    /**
     * Comparing two arrays.
     */
    @Test
    public void testingCompareMethod() {
        int[] a = {1, 2, 3};
        int[] b = {1, 2, 3};
        ArraysCompare ar = new ArraysCompare();
        assertThat(ar.compareTwoArrays(a, b), is(0));
        a[0] = 2;
        assertThat(ar.compareTwoArrays(a, b), is(1));
        b[0] = 3;
        assertThat(ar.compareTwoArrays(a, b), is(-1));
        a[0] = 1;
        int[] c = {1, 2, 3, 4};
        assertThat(ar.compareTwoArrays(a, c), is(-1));
        assertThat(ar.compareTwoArrays(c, a), is(1));
    }
}