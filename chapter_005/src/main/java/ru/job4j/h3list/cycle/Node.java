package ru.job4j.h3list.cycle;
/**
 * @param <T> is the name of type parameter.
 */
public class Node<T> {
    /**
     * The parameter type.
     */
    private T value;
    /**
     * The next element.
     */
    Node<T> next;
    /**
     * @param value to be assigned in the constructor.
     */
    Node(T value) {
        this.value = value;
    }
}