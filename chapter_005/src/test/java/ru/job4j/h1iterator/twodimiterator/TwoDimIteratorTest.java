package ru.job4j.h1iterator.twodimiterator;
import org.junit.Test;
import java.util.NoSuchElementException;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * TwoDimIteratorTest class.
 */
public class TwoDimIteratorTest {
    /**
     * Checks if the next() method iterates through the all elements within the array.
     */
    @Test
    public void whenTakeTheThirdElementThenGetThisElement() {
        TwoDimIterator t = new TwoDimIterator(new int[][]{{2, 4}, {1, 2}});
        t.next();
        t.next();
        final int res = (Integer) t.next();
        assertThat(res, is(1));
    }

    /**
     * Checks if the hasNext() method returns true when there are elements in the array and false otherwise.
     */
    @Test
    public void whenCheckTheNextElementThenGetTrueOrFalse() {
        TwoDimIterator t = new TwoDimIterator(new int[][]{{2, 4}, {1, 2}});
        t.next();
        t.next();
        boolean res = t.hasNext();
        assertThat(res, is(true));
        t.next();
        t.next();
        res = t.hasNext();
        assertThat(res, is(false));
    }

    /**
     * test.
     */
    @Test(expected = NoSuchElementException.class)
    public void hasNextNextSequentialInvocationFromMatrix() {
        TwoDimIterator mit = new TwoDimIterator(new int[][]{{1, 2, 3}, {4, 5, 6}});
        assertThat(mit.hasNext(), is(true));
        assertThat(mit.next(), is(1));
        assertThat(mit.hasNext(), is(true));
        assertThat(mit.next(), is(2));
        assertThat(mit.hasNext(), is(true));
        assertThat(mit.next(), is(3));
        assertThat(mit.hasNext(), is(true));
        assertThat(mit.next(), is(4));
        assertThat(mit.hasNext(), is(true));
        assertThat(mit.next(), is(5));
        assertThat(mit.hasNext(), is(true));
        assertThat(mit.next(), is(6));
        assertThat(mit.hasNext(), is(false));
        mit.next();
    }

    /**
     * test.
     */
    @Test(expected = NoSuchElementException.class)
    public void onlyNextSequentialInvocationFromMatrix() {
        TwoDimIterator td = new TwoDimIterator(new int[][]{{1, 2, 3}, {4, 5, 6}});
        assertThat(td.next(), is(1));
        assertThat(td.next(), is(2));
        assertThat(td.next(), is(3));
        assertThat(td.next(), is(4));
        assertThat(td.next(), is(5));
        assertThat(td.next(), is(6));
        td.next();
    }

    /**
     * test.
     */
    @Test(expected = NoSuchElementException.class)
    public void sequentialHasNextInvocationDoesntAffect() {
        TwoDimIterator t = new TwoDimIterator(new int[][]{{1, 2, 3}, {4, 5, 6}});
        assertThat(t.hasNext(), is(true));
        assertThat(t.hasNext(), is(true));
        assertThat(t.next(), is(1));
        assertThat(t.next(), is(2));
        assertThat(t.next(), is(3));
        assertThat(t.next(), is(4));
        assertThat(t.next(), is(5));
        assertThat(t.next(), is(6));
        t.next();
    }

    /**
     *
     */
    @Test(expected = NoSuchElementException.class)
    public void jaggedArrayIterator() {
        TwoDimIterator it = new TwoDimIterator(new int[][]{{1}, {3, 4}});
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(false));
        it.next();
    }
}