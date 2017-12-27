package ru.job4j.h3list.t2dynamiclinkedlist;
import org.junit.Test;
import java.util.Iterator;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * DynamicLinkedListTest.
 */
public class DynamicLinkedListTest {
    /**
     * Checks adding elements to the beginning of the list.
     */
    @Test
    public void addingFirstTest() {
        DynamicLinkedList<String> dl = new DynamicLinkedList<>();
        dl.addFirst("1");
        dl.addFirst("2");
        dl.addFirst("3");
        assertThat(dl.get(1), is("3"));
        assertThat(dl.get(2), is("2"));
        assertThat(dl.get(3), is("1"));
    }

    /**
     * Checks adding elements to the end of the list.
     */
    @Test
    public void addingLastTest() {
        DynamicLinkedList<String> dl = new DynamicLinkedList<>();
        dl.addLast("1");
        dl.addLast("2");
        dl.addLast("3");
        assertThat(dl.get(1), is("1"));
        assertThat(dl.get(2), is("2"));
        assertThat(dl.get(3), is("3"));
    }

    /**
     * Checks iterating through elements from the first element to the last one.
     */
    @Test
    public void iteratingNextElementTest() {
        DynamicLinkedList<String> dl = new DynamicLinkedList<>();
        dl.addFirst("1");
        dl.addFirst("2");
        dl.addFirst("3");
        Iterator<String> it = dl.iterator();
        it.next();
        it.next();
        assertThat(it.next(), is("1"));
    }

    /**
     * Checks if the hasNext() method returns true or false.
     */
    @Test
    public void checkingIfIteratorHasNextElement() {
        DynamicLinkedList<String> dl = new DynamicLinkedList<>();
        dl.addFirst("1");
        dl.addFirst("2");
        dl.addFirst("3");
        Iterator<String> it = dl.iterator();
        it.hasNext();
        it.next();
        it.hasNext();
        it.next();
        assertThat(it.hasNext(), is(true));
        it.next();
        assertThat(it.hasNext(), is(false));
    }
}