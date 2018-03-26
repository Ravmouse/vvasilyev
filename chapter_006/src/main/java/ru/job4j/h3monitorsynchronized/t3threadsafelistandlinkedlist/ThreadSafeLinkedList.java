package ru.job4j.h3monitorsynchronized.t3threadsafelistandlinkedlist;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * @param <E> - обобщенный тип.
 */
@ThreadSafe
public class ThreadSafeLinkedList<E> implements Iterable<E> {
    /**
     * Ссылка на первый элемент списка.
     */
    @GuardedBy("this")
    private Link<E> first;
    /**
     * Ссылка на последний элемент списка.
     */
    @GuardedBy("this")
    private Link<E> last;
    /**
     * Хранит кол-во добавленных элементов.
     */
    private int count;

    /**
     * @param value - элемент, который добавляется в начало списка.
     */
    public synchronized void addFirst(E value) {
        if (value != null) {
            Link<E> newLink = new Link<>(value);
            if (this.isEmpty()) {
                last = newLink;
            } else {
                newLink.next = first;
                first.previous = newLink;
            }
            first = newLink;
            count++;
        }
    }

    /**
     * @param value - элемент, который добавляется в конец списка.
     */
    public synchronized void addLast(E value) {
        if (value != null) {
            Link<E> newLink = new Link<>(value);
            if (this.isEmpty()) {
                first = newLink;
            } else {
                last.next = newLink;
                newLink.previous = last;
            }
            last = newLink;
            count++;
        }
    }

    /**
     * @param position - номер элемента в списке.
     * @return элемент списка в соответствии с номером position.
     */
    public synchronized E get(int position) {
        Link<E> currElement = first;
        int count = 0;
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
     * @return true, если список пуст и false, если - нет.
     */
    private synchronized boolean isEmpty() {
        return this.first == null;
    }

    /**
     * @return количество добавленных в список элементов.
     */
    public int getCount() {
        return count;
    }

    /**
     * Удаляет первый элемент в списке.
     */
    public synchronized void removeFirst() {
        if (!isEmpty()) {
            if (first == last) {
                first = null;
                last = null;
            } else {
                first.next.previous = null;
                first = first.next;
            }
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * Удаляет последний элемент в списке.
     */
    public synchronized void removeLast() {
        if (!isEmpty()) {
            if (first == last) {
                first = null;
                last = null;
            } else {
                last.previous.next = null;
                last = last.previous;
            }
        } else {
            throw new NoSuchElementException();
        }
    }
//=======================================================
    /**
     * @param <E> - обобщенный тип.
     * Внутренний класс.
     */
    private static class Link<E> {
        /**
         * Данные внутри класса Link.
         */
        private E data;
        /**
         * Ссылка на следующий элемент.
         */
        private Link<E> next;
        /**
         * Ссылка на предыдущий элемент.
         */
        private Link<E> previous;

        /**
         * @param data - данные для конструктора.
         */
        private Link(E data) {
            this.data = data;
        }
    }
//=======================================================
    /**
     * @return итератор для обхода данного списка.
     */
    @Override
    public LinkedListIterator iterator() {
        return new LinkedListIterator();
    }

    /**
     * Внутренний класс итератора, который реализует интерфейс Iterator.
     * Анонимный класс не создавал в методе iterator(), потому что создал доп. методы previous() и hasPrevious().
     * По ссылке на Iterator эти методы нельзя было бы вызвать.
     */
    public class LinkedListIterator implements Iterator<E> {
        /**
         * Ссылка на текущий элемент.
         * Позиция итератора при его создании устанавливается на первый элемент списка.
         */
        private Link<E> current = first;
        /**
         * Ссылка на предыдущий элемент.
         */
        private Link<E> prev;
        /**
         * Переменная для отслеживания изменения списка после создания итератора.
         * Если эти значения не будут равны, то - исключение ConcurrentModificationException.
         */
        private int expectedCount = count;

        /**
         * @return true, если есть следующий элемент и false, если - нет.
         */
        @Override
        public boolean hasNext() {
            boolean rsl = false;
            if (current != null) {
                rsl = true;
            }
            return rsl;
        }

        /**
         * @return следующий элемент.
         */
        @Override
        public E next() {
            if (expectedCount == count) {
                E rsl;
                if (hasNext()) {
                    prev = current;
                    rsl = current.data;
                    current = current.next;
                } else {
                    throw new NoSuchElementException();
                }
                return rsl;
            } else {
                throw new ConcurrentModificationException();
            }
        }

        /**
         * @return true, если есть предыдущий элемент и false, если - нет.
         */
        public boolean hasPrevious() {
            boolean rsl = false;
            if (prev != null) {
                rsl = true;
            }
            return rsl;
        }

        /**
         * @return предыдущий элемент.
         */
        public E previous() {
            if (expectedCount == count) {
                E rsl;
                if (hasPrevious()) {
                    current = prev;
                    rsl = prev.data;
                    prev = prev.previous;
                } else {
                    throw new NoSuchElementException();
                }
                return rsl;
            } else {
                throw new ConcurrentModificationException();
            }
        }
    }
}