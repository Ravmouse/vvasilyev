package ru.job4j.h6tree.t1elementarytree;

/**
 * @param <E> - обобщенный тип.
 */
public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {
    /**
     * Добавить элемент child в parent.
     * Parent может иметь список child.
     * @param parent - родительский элемент для добавления.
     * @param child - дочерний элемент родителя для добавления.
     * @return true, если удалось добавить и false, если - нет.
     */
    boolean add(E parent, E child);

    /**
     * @param value - значение, которое надо найти.
     * @return true, если значение найдено и false, если - нет.
     */
    boolean findBy(E value);
}