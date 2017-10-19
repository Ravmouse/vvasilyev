package ru.job4j.h4set.simpleset;
import java.util.Iterator;

/**
 * @param <E> is the name of type parameter.
 */
public interface MySimpleSet<E> extends Iterator<E> {
    /**
     * @param e is the type parameter that should be added.
     */
    void add(E e);
}