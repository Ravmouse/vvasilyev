package ru.job4j.h4set.t2simplelinkedlistset;
import org.junit.Test;
import java.util.NoSuchElementException;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * SimpleLinkedListSetTest class.
 */
public class SimpleLinkedListSetTest {
    /**
     * Checks adding only unique elements in the container.
     */
    @Test
    public void addingTest() {
        SimpleLinkedListSet<String> sll = new SimpleLinkedListSet<>();
        sll.add("java");
        assertThat(sll.get(1), is("java"));
        sll.add("bingo");
        assertThat(sll.get(2), is("bingo"));
        sll.add("java");
        assertThat(sll.get(1), is("java"));
        assertThat(sll.get(2), is("bingo"));
        sll.add("tea");
        assertThat(sll.get(1), is("java"));
        assertThat(sll.get(2), is("bingo"));
        assertThat(sll.get(3), is("tea"));
    }

    /**
     * Checks iterating through the all added elements within the container.
     */
    @Test(expected = NoSuchElementException.class)
    public void iteratingTest() {
        SimpleLinkedListSet<String> sll = new SimpleLinkedListSet<>();
        sll.add("java");
        sll.add("hello");
        sll.add("day");
        sll.add("mars");
        assertThat(sll.hasNext(), is(true));
        assertThat(sll.next(), is("java"));
        assertThat(sll.hasNext(), is(true));
        assertThat(sll.next(), is("hello"));
        assertThat(sll.hasNext(), is(true));
        assertThat(sll.next(), is("day"));
        assertThat(sll.hasNext(), is(true));
        assertThat(sll.next(), is("mars"));
        assertThat(sll.hasNext(), is(false));
        sll.next();
    }
}