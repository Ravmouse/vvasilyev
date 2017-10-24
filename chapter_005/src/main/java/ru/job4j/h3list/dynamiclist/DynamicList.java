package ru.job4j.h3list.dynamiclist;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @param <E> is the name of type parameter.
 */
public class DynamicList<E> implements Iterable<E> {
    /**
     * The array of Object elements.
     * Массив Object'ов.
     */
    private Object[] container;
    /**
     * The class field for adding elements in the array.
     * Поле класса для добавления элементов в массив.
     */
    private int count;
    /**
     * The class field for the iterator; the iterator returns elements using this var.
     * Поле класса для итератора; итератор возвращает эл-ты по этой переменной.
     */
    private int index;

    /**
     * The constructor of DynamicList class instance.
     * Конструктор экз.класса DynamicList.
     */
    public DynamicList() {
        container = new Object[3];
    }

    /**
     * Adds an element to the end of container.
     * Добавляет элемент в конец массива container.
     * @param value is the element to be added to container.
     */
    public void add(E value) {
        if (count == container.length) {
            container = Arrays.copyOf(container, (container.length * 3) / 2 + 1);
        }
        this.container[count++] = value;
    }

    /**
     * Returns the array's element by the index called position.
     * Возвращает элемент массива container по индексу position.
     * @param position is the index which element should be returned.
     * @return array's element by its index.
     */
    public E get(int position) {
        E result = null;
        if ((position > -1) && (position < count)) { // position д.б. больше -1 и меньше кол-ва добавлен.элементов
            result = (E) container[position];
        }
        return result;
    }

    /**
     * @return the iterator for the array of Object elements int the class.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            @Override
            public boolean hasNext() {
                boolean result = false;
                if (index < count) { // Как только индекс забираемого итератором элемента будет равнен индексу
                    result = true;   // следующему за последним положенным элементом, - вернуть false
                }                    // = NoSuchElementException
                return result;
            }

            @Override
            public E next() {
                if (this.hasNext()) {
                    return (E) container[index++];
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }

    /**
     * @return the reference to the array of Object elements.
     */
    public Object[] getContainer() {
        return container;
    }
}