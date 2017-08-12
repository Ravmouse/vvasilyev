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
public class TurnTest {
    /**
     * Test of the array with even amount of numbers.
     */
    @Test
    public void whenTurnArrayWithEvenAmountOfElementsThenTurnedArray() {
        Turn turn = new Turn();
        int[] result = turn.back(new int[] {1, 2, 3, 4, 5, 6});
        int[] expected = {6, 5, 4, 3, 2, 1};
        assertThat(result, is(expected));
    }
    /**
     * Test of the array with odd amount of numbers.
     */
    @Test
    public void whenTurnArrayWithOddAmountOfElementsThenTurnedArray() {
        Turn turn = new Turn();
        int[] result = turn.back(new int[] {4, 1, 6, 2, 7});
        int[] expected = {7, 2, 6, 1, 4};
        assertThat(result, is(expected));
    }
}