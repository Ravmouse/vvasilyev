package ru.job4j.h6tree.t1elementarytree;
import org.junit.Test;
import java.util.Iterator;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Тестовый класс.
 */
public class TreeTest {
    /**
     * Тест добавления элементов в класс Tree.
     */
    @Test
    public void addingTest() {
        Tree<Integer> t = new Tree<>();
        t.add(1, 2);
        t.add(1, 3);
        t.add(1, 4);
        t.add(2, 5);
        t.add(2, 6);
        assertThat(t.find(6).getValue(), is(6));
    }

    /**
     * Тест нахождения элементов в классе Tree.
     */
    @Test
    public void findingTest() {
        Tree<Integer> t = new Tree<>();
        t.add(1, 2);
        t.add(1, 3);
        t.add(1, 4);
        assertThat(t.find(4).getValue(), is(4));
    }

    /**
     * Тест итерации по всем элементам класса Tree.
     */
    @Test
    public void iteratingTest() {
        Tree<Integer> t = new Tree<>();
        t.add(1, 2);
        t.add(1, 3);
        t.add(1, 4);
        t.add(4, 8);
        t.add(4, 9);
        t.add(9, 18);
        final Iterator<Integer> it = t.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(8));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(18));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(9));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(false));
    }
}