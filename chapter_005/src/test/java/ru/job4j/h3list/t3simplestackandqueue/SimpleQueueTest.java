package ru.job4j.h3list.t3simplestackandqueue;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * SimpleQueueTest class.
 */
public class SimpleQueueTest {
    /**
     * Checks the push() method work.
     */
    @Test
    public void pushingTest() {
        SimpleQueue<String> s = new SimpleQueue<>();
        s.push("1");
        s.push("2");
        s.push("3");
        assertThat(s.get(3), is("3"));
    }

    /**
     * Checks the pull() method work.
     */
    @Test
    public void pullingTest() {
        SimpleQueue<String> s = new SimpleQueue<>();
        s.push("1");
        s.push("2");
        s.push("3");
        s.poll();
        assertThat(s.poll(), is("2"));
        assertThat(s.poll(), is("3"));
    }
}