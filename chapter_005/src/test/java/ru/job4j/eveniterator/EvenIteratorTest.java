package ru.job4j.eveniterator;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * EvenIteratorTest class.
 */
public class EvenIteratorTest {
    /**
     * Checks if the next() method is iterating through an array.
     */
    @Test
    public void whenTakeTheNextElementThenGetThisElement() {
        EvenIterator ev = new EvenIterator(new int[]{1, 2, 3});
        ev.next();
        final int tmp = (Integer) ev.next();
        assertThat(tmp, is(2));
    }

    /**
     * Checks if the hasNext() method returns true only if the even number is the next in an array.
     */
    @Test
    public void whenCheckIfTheNextElementIsEvenThenGetTrueOrFalse() {
        EvenIterator ev = new EvenIterator(new int[]{1, 2, 3, 4, 1});
        ev.hasNext();
        ev.next();
        ev.hasNext();
        final int tmp = (Integer) ev.next();
        assertThat(tmp, is(4));
        final boolean temp = ev.hasNext();
        assertThat(temp, is(false));
    }
}