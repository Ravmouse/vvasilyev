package ru.job4j.h1iterator.eveniterator;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * EvenIteratorTest class.
 */
public class EvenIteratorTest {
    /**
     * Checks if the next() method iterates through the array.
     */
    @Test
    public void whenTakeTheNextElementThenGetThisElement() {
        EvenIterator ev = new EvenIterator(new int[]{1, 2, 3, 4, 5, 6});
        ev.next();
        ev.next();
        final int tmp = (Integer) ev.next();
        assertThat(tmp, is(6));
    }

    /**
     * Checks if the hasNext() method returns true only if the even number is the next in the array.
     */
    @Test
    public void whenCheckIfTheNextElementIsEvenThenGetTrueOrFalse() {
        EvenIterator ev = new EvenIterator(new int[]{1, 2, 3, 4, 5, 6});
        ev.hasNext();
        ev.next();
        ev.hasNext();
        final int tmp = (Integer) ev.next();
        assertThat(tmp, is(4));
        final boolean temp = ev.hasNext();
        assertThat(temp, is(true));
    }
}