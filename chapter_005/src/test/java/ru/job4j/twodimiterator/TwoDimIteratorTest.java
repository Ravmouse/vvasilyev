package ru.job4j.twodimiterator;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * TwoDimIteratorTest class.
 */
public class TwoDimIteratorTest {
    /**
     * Checks if the next() method is iterating through an array.
     */
    @Test
    public void whenTakeTheThirdElementThenGetThisElement() {
        TwoDimIterator t = new TwoDimIterator(new int[][]{{2, 4, 6, 8, 10}, {1, 2, 3}});
        t.next();
        t.next();
        final int res = (Integer) t.next();
        assertThat(res, is(6));
    }

    /**
     * Checks if the hasNext() method returns true when there are elements in an array and false otherwise.
     */
    @Test
    public void whenCheckTheNextElementThenGetTrueOrFalse() {
        TwoDimIterator t = new TwoDimIterator(new int[][]{{2, 4}, {1, 2, 3}});
        t.next();
        t.next();
        t.hasNext();
        t.next();
        t.next();
        boolean res = t.hasNext();
        assertThat(res, is(true));

        t.next();
        res = t.hasNext();
        assertThat(res, is(false));
    }
}