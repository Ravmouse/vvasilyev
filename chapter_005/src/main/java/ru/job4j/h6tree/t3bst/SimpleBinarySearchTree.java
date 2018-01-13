package ru.job4j.h6tree.t3bst;

/**
 * @param <E> - обобщенный тип класса.
 */
public interface SimpleBinarySearchTree<E extends Comparable<E>> extends Iterable<E> {
    /**
     * @param e - значение дочернего элемента для добавления в дерево.
     * @return true, если удалось добавить элемент и false, если - нет.
     */
    boolean add(E e);

    /**
     * @param e - значение, которое нужно найти среди нодов дерева.
     * @return нод с искомым значением.
     */
    Node<E> find(E e);
}