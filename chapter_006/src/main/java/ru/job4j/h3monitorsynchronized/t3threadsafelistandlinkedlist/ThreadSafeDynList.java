package ru.job4j.h3monitorsynchronized.t3threadsafelistandlinkedlist;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * @param <E> - обобщенный тип.
 * Динамический список.
 */
@ThreadSafe
public class ThreadSafeDynList<E> implements Iterable<E> {
    /**
     * Массив Object'ов.
     */
    @GuardedBy("this")
    private Object[] container;
    /**
     * Счетчик, который подсчитывает кол-во добавленных элементов.
     */
    private int count;
    /**
     * Позиция элемента для возвращения итератором.
     */
    private int index;

    /**
     *
     */
    public ThreadSafeDynList() {
        container = new Object[3];
    }

    /**
     * @param value - элемент для добавления в список.
     */
    public synchronized void add(E value) {
        if (count == container.length) {
            container = Arrays.copyOf(container, (container.length * 3) / 2 + 1);
        }
        this.container[count++] = value;
    }

    /**
     * @param position - номер элемента в списке.
     * @return элемент списка в соответствии с номером position.
     */
    public synchronized E get(int position) {
        E result = null;
        if ((position > -1) && (position < count)) {
            result = (E) container[position];
        }
        return result;
    }

    /**
     * @return итератор для обхода данного списка.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() { //Анонимный класс.
            /**
             * Переменная для отслеживания изменения списка после создания итератора.
             * Если эти значения не будут равны, то - исключение ConcurrentModificationException.
             */
            private int expectedCount = count;

            @Override
            public boolean hasNext() {
                boolean result = false;
                if (index < count) {
                    result = true;
                }
                return result;
            }

            @Override
            public synchronized E next() {
                if (expectedCount == count) {
                    if (this.hasNext()) {
                        return (E) container[index++];
                    } else {
                        throw new NoSuchElementException();
                    }
                } else {
                    throw new ConcurrentModificationException(); //Если список был модифицирован после создания
                }                                                //иетратора, то кинуть исключение.
            }
        };
    }

    /**
     * @return количество добавленных в список элементов.
     */
    public int getCount() {
        return this.count;
    }
}