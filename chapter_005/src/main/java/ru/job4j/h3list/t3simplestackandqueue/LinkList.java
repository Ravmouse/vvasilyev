package ru.job4j.h3list.t3simplestackandqueue;
import java.util.NoSuchElementException;

/**
 * @param <E> is the name of type parameter.
 */
public class LinkList<E> {
    /**
     * The ref to the first element in the list.
     */
    private Link<E> first;
    /**
     * The ref to the last element in the list.
     */
    private Link<E> last;

    /**
     * @param value to be added to the list on the first position.
     */
    public void addFirst(E value) {
        Link<E> newLink = new Link<>(value);
        if (isEmpty()) {
            last = newLink;
        }
        newLink.next = first;
        first = newLink;
    }

    /**
     * @param value to be added to the list on the last position.
     */
    public void addLast(E value) {
        Link<E> newLink = new Link<>(value);
        if (isEmpty()) {
            first = newLink;
        } else {
            last.next = newLink;
        }
        last = newLink;
    }

    /**
     * @return the value that is deleted from the first position in the list.
     */
    public E removeFirst() {
        if (!isEmpty()) {
            Link<E> newLink = first;
            first = first.next;
            return newLink.data;
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * @param position which value should be found.
     * @return the value according to the found position.
     */
    public E get(int position) {
        int val = 1;
        Link<E> curr = first;
        while ((curr != null) && (val < position)) {
            curr = curr.next;
            val++;
        }
        if ((curr == null) || (position < val)) {
            throw new IndexOutOfBoundsException();
        }
        return curr.data;
    }

    /**
     * @return true or false.
     */
    private boolean isEmpty() {
        return first == null;
    }
//-----------------------------------------
    /**
     * @param <E> is the name of type parameter.
     */
    private class Link<E> {
        /**
         * Data for storing.
         */
        private E data;
        /**
         * The ref to the next element.
         */
        private Link<E> next;
        /**
         * @param data is the value for the constructor.
         */
        private Link(E data) {
            this.data = data;
        }
    }
}