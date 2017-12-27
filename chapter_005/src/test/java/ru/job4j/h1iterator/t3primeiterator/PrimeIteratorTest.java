package ru.job4j.h1iterator.t3primeiterator;
import org.junit.Test;
import java.util.NoSuchElementException;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * PrimeIteratorTest class.
 */
public class PrimeIteratorTest {
    /**
     * Checks if the next() method is iterating through the container without the hasNext() method.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenTakeTheNextElementThenGetThisElement() {
        PrimeIterator pr = new PrimeIterator(new int[]{3001, 3002, 3003, 3004, 3005, 3006, 3007, 3008, 3009, 3010, 3011,
                3012, 3013, 3014, 3015, 3016, 3017, 3018, 3019, 3020, 3021, 3022, 3023});
        assertThat(pr.next(), is(3001));
        assertThat(pr.next(), is(3011));
        assertThat(pr.next(), is(3019));
        assertThat(pr.next(), is(3023));
        pr.next();
    }

    /**
     * Checks if the hasNext() method returns true only if the next element is prime.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenCheckIfTheNextElementIsPrimeNumberThenGetTrueValue() {
        PrimeIterator pr = new PrimeIterator(new int[]{1690, 1691, 1692, 1693, 1694, 1695, 1696, 1697, 1698, 1699, 1700,
                1701, 1702, 1703, 1704, 1705, 1706, 1707, 1708, 1709, 1710});
        assertThat(pr.hasNext(), is(true));
        assertThat(pr.next(), is(1693));
        assertThat(pr.hasNext(), is(true));
        assertThat(pr.next(), is(1697));
        assertThat(pr.hasNext(), is(true));
        assertThat(pr.next(), is(1699));
        assertThat(pr.hasNext(), is(true));
        assertThat(pr.next(), is(1709));
        assertThat(pr.hasNext(), is(false));
        pr.next();
    }
}