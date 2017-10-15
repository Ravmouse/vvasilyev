package ru.job4j.h3list.simplestackandqueue;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * SimpleStackTest class.
 */
public class SimpleStackTest {
    /**
     * Checks the push() method work.
     */
    @Test
    public void pushingTest() {
        SimpleStack<String> ss = new SimpleStack<>();
        ss.push("1");
        ss.push("2");
        ss.push("3");
        assertThat(ss.get(1), is("3"));
    }

    /**
     * Checks the pull() method work.
     */
    @Test
    public void pullingTest() {
        SimpleStack<String> ss = new SimpleStack<>();
        ss.push("1");
        ss.push("2");
        ss.push("3");
        ss.poll();
        assertThat(ss.poll(), is("2"));
        assertThat(ss.poll(), is("1"));
    }
}