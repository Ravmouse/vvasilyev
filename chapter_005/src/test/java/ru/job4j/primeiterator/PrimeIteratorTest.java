package ru.job4j.primeiterator;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * PrimeIteratorTest class.
 */
public class PrimeIteratorTest {
    /**
     * Checks if the next() method is iterating through an array.
     */
    @Test
    public void whenTakeTheNextElementThenGetThisElement() {
        PrimeIterator pr = new PrimeIterator(new int[]{1, 3, 5});
        pr.next();
        final int tmp = (Integer) pr.next();
        assertThat(tmp, is(3));
    }

    /**
     * Checks if the hasNext() method returns true only if the next element is prime.
     */
    @Test
    public void whenCheckIfTheNextElementIsPrimeNumberThenGetTrueOrFalse() {
        PrimeIterator pr = new PrimeIterator(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14});
        pr.hasNext();
        pr.next();
        pr.hasNext();
        pr.next();
        pr.hasNext();
        pr.next();
        pr.hasNext();
        pr.next();
        pr.hasNext();
        final int tmp = (Integer) pr.next();
        assertThat(tmp, is(11));
        pr.hasNext();
        pr.next();
        final boolean temp = pr.hasNext();
        assertThat(temp, is(false));
    }
}