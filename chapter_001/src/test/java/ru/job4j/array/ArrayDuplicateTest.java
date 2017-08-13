package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test class.
 * @author Vitaly Vasilyev (rav.energ@rambler.ru)
 * @version $Id$
 * @since 0.1
 */
public class ArrayDuplicateTest {
    /**
     * Test of the array with duplicate elements.
     */
    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
        ArrayDuplicate duplicate = new ArrayDuplicate();
        String[] test = {"Привет", "Мир", "Привет", "Привет", "Супер", "Мир", "Мир"};
        String[] expected = {"Привет", "Мир", "Супер"};
        String[] result = duplicate.remove(test);
        assertThat(result, is(expected));
    }
}