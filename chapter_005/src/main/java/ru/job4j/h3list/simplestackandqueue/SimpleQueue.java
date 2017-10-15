package ru.job4j.h3list.simplestackandqueue;

/**
 * @param <E> is the name of type parameter.
 */
public class SimpleQueue<E> extends SimpleAbstract<E> {
    /**
     * @param value to be pushed in the queue.
     */
    @Override
    public void push(E value) {
        getLinkList().addLast(value);
    }
}