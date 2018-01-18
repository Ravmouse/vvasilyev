package ru.job4j.h6tree.t3bst;
import org.junit.Test;
import java.util.Iterator;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тестовый класс.
 */
public class BinarySearchTreeTest {
    /**
     * Тест добавления и поиска элементов.
     */
    @Test
    public void whenAddElementsToTreeThenFindSomeElement() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.add(50);
        bst.add(40);
        bst.add(60);
        bst.add(35);
        bst.add(45);
        bst.add(55);
        assertThat(bst.find(45).getValue(), is(45));
    }

    /**
     * Тест итерации по всем элементам дерева.
     */
    @Test
    public void whenAddElementsToTreeThenIterateThemAll() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.add(7);
        bst.add(4);
        bst.add(1);
        Iterator<Integer> it = bst.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(7));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(false));
    }

    /**
     * Тест итерации по нескольким элементам и присваивания итератора заново для повторной итерации по всем
     * элементам.
     */
    @Test
    public void whenAddElementsAndIterateSomeThenIterateAllOfThemAfterAssignmentOneMoreTime() {
        BinarySearchTree<Integer> b = new BinarySearchTree<>();
        b.add(3);
        b.add(2);
        b.add(6);
        b.add(4);
        b.add(12);
        b.add(9);
        Iterator<Integer> i = b.iterator();
        assertThat(i.next(), is(3));
        assertThat(i.next(), is(2));
        assertThat(i.next(), is(6));
        assertThat(i.hasNext(), is(true));
        i = b.iterator();
        assertThat(i.next(), is(3));
        assertThat(i.next(), is(2));
        assertThat(i.next(), is(6));
        assertThat(i.next(), is(4));
        assertThat(i.next(), is(12));
        assertThat(i.next(), is(9));
        assertThat(i.hasNext(), is(false));
    }
}