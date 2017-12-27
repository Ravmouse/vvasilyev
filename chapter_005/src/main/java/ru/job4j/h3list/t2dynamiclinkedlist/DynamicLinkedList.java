package ru.job4j.h3list.t2dynamiclinkedlist;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @param <E> is the name of type parameter.
 */
public class DynamicLinkedList<E> implements Iterable<E> {
    /**
     * Ссылка на первый элемент списка.
     */
    private Link<E> first;
    /**
     * Ссылка на последний элемент списка.
     */
    private Link<E> last;

    /**
     * Добавить элемент в начало списка.
     * @param value is the element to be added to the beginning of the list.
     */
    public void addFirst(E value) {
        Link<E> newLink = new Link<>(value);
        if (this.isEmpty()) {
            last = newLink;
        } else {
            first.previous = newLink;
        }
        newLink.next = first;
        first = newLink;
    }

    /**
     * Добавить элемент в конец списка.
     * @param value is the element to be added to the end of the list.
     */
    public void addLast(E value) {
        Link<E> newLink = new Link<>(value);
        if (this.isEmpty()) {
            first = newLink;
        } else {
            last.next = newLink;
            newLink.previous = last;
        }
        last = newLink;
    }

    /**
     * Получить элемент по номеру позиции.
     * @param position is the number which is used to get the element.
     * @return the element according to its position number.
     */
    public E get(int position) {
        Link<E> currElement = first;
        int count = 1;
        while ((currElement != null) && (count < position)) {
            currElement = currElement.next;
            count++;
        }
        if ((currElement == null) || (position < count)) {
            throw new IndexOutOfBoundsException();
        }
        return currElement.data;
    }

    /**
     * Проверить, пустой список или нет.
     * @return true or false.
     */
    private boolean isEmpty() {
        return this.first == null;
    }

//=======================================================
    /**
     * Класс элемента списка; список двусвязный.
     * @param <E> is the name of type parameter.
     */
    private static class Link<E> {
        /**
         * Данные.
         */
        private E data;
        /**
         * Ссылка на следующий элемент списка.
         */
        private Link<E> next;
        /**
         * Ссылка на предыдущий элемент списка.
         */
        private Link<E> previous;

        /**
         * @param data for the constructor.
         */
        private Link(E data) {
            this.data = data;
        }
    }
//=======================================================
    /**
     * Получить итератор для объекта списка.
     * @return Iterator object.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Link<E> current = first;
            private Link<E> prev;

            @Override
            public boolean hasNext() {
                boolean result = false;
                if (current != null) {
                    result = true;
                }
                return result;
            }

            @Override
            public E next() {
                E result;
                if (hasNext()) {
                    prev = current;
                    result = current.data;
                    current = current.next;
                } else {
                    throw new NoSuchElementException();
                }
                return result;
            }
        };
    }
}