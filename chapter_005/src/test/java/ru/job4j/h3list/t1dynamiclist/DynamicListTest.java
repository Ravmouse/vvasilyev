package ru.job4j.h3list.t1dynamiclist;
import org.junit.Test;
import java.util.Iterator;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * DynamicListTest.
 */
public class DynamicListTest {
    /**
     * Checks that the iterator can go through the elements of the array.
     */
    @Test
    public void iteratingTest() {
        DynamicList<String> dy = new DynamicList<>();
        dy.add("java");
        dy.add("jdk");
        dy.add("jre");

        Iterator<String> it = dy.iterator();
        it.next();
        it.next();

        assertThat(it.next(), is("jre"));
        assertThat(it.hasNext(), is(false));
    }

    /**
     * Checks that elements can be added to the array and the length of the array is increased when
     * extra element is added.
     */
    @Test
    public void addingTest() {
        DynamicList<String> dy = new DynamicList<>();
        dy.add("html");
        dy.add("css");
        dy.add("js");
        assertThat(dy.getContainer().length, is(3));
        dy.add("html");
        dy.add("css");
        dy.add("js");
        assertThat(dy.get(5), is("js"));
        assertThat(dy.getContainer().length, is(8));
    }

    /**
     * Checks getting the element by its index.
     */
    @Test
    public void gettingTest() {
        DynamicList<String> dy = new DynamicList<>();
        dy.add("java");
        dy.add("key");
        dy.add("headfirst");
        assertThat(dy.get(2), is("headfirst"));
    }
}