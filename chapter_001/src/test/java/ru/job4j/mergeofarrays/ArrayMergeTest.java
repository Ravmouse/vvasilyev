package ru.job4j.mergeofarrays;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test class.
 * @author Vitaly Vasilyev (rav.energ@rambler.ru)
 * @version $Id$
 * @since 0.1
 */
public class ArrayMergeTest {
    /**
     * Test of merging two sorted arrays into one.
     */
    @Test
    public void whenTwoSortedArraysThenReturnOneArrayWithSortedElements() {
        ArrayMerge arr = new ArrayMerge();
        int[] first = {-3, 0, 2, 4, 9};
        int[] second = {-2, 1, 3, 5, 10, 12};
        int[] expected = {-3, -2, 0, 1, 2, 3, 4, 5, 9, 10, 12};
        int[] result = arr.mergeOfSortedArrays(first, second);
        assertThat(expected, is(result));
    }
}