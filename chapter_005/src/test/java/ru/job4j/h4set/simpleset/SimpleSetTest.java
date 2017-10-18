package ru.job4j.h4set.simpleset;
import org.junit.Test;
import java.util.NoSuchElementException;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * SimpleSetTest class.
 */
public class SimpleSetTest {
    /**
     * Checks adding only unique elements in the container.
     */
    @Test
    public void addingTest() {
        SimpleSet<String> ss = new SimpleSet<>();
        ss.add("qwerty");
        ss.add("java");
        ss.add("jvm");
        assertThat(ss.getAllElements(), is(new String[] {"qwerty", "java", "jvm"}));
        ss.add("qwerty");
        assertThat(ss.getAllElements(), is(new String[] {"qwerty", "java", "jvm"}));
        ss.add("oracle");
        assertThat(ss.getAllElements(), is(new String[] {"qwerty", "java", "jvm", "oracle"}));
        ss.add("oracle");
        assertThat(ss.getAllElements(), is(new String[] {"qwerty", "java", "jvm", "oracle"}));
    }

    /**
     * Checks iterating through the all added elements within the container.
     */
    @Test(expected = NoSuchElementException.class)
    public void iteratingTest() {
        SimpleSet<Integer> ss = new SimpleSet<>();
        ss.add(11);
        ss.add(22);
        ss.add(33);
        assertThat(ss.hasNext(), is(true));
        assertThat(ss.next(), is(11));
        assertThat(ss.hasNext(), is(true));
        assertThat(ss.next(), is(22));
        assertThat(ss.hasNext(), is(true));
        assertThat(ss.next(), is(33));
        assertThat(ss.hasNext(), is(false));
        ss.next();
    }
}