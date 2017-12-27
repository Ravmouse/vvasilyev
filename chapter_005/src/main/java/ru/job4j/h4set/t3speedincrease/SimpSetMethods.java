package ru.job4j.h4set.t3speedincrease;

/**
 * @param <E> - обобщенный параметр.
 */
public interface SimpSetMethods<E> {
    /**
     * @param e - элемент для добавления в массив.
     * @return true или false.
     */
    boolean add(E e);

    /**
     * @param e - элемент, который нужно найти в массиве.
     * @return true или false.
     */
    boolean contains(E e);

    /**
     * @param e - элемент, который нужно удалить из массива.
     * @return true или false.
     */
    boolean remove(E e);
}