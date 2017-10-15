package ru.job4j.h3list.simplestackandqueue;

/**
 * @param <E> is the name of type parameter.
 */
public class SimpleStack<E> extends SimpleAbstract<E> {
    /**
     * @param value to be pushed in the stack.
     */
    @Override
    public void push(E value) {
        getLinkList().addFirst(value);
    }
}