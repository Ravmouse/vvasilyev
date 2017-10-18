package ru.job4j.h4set.simpleset;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * @param <E> is the name of type parameter.
 */
public class SimpleSet<E> implements MySimpleSetInterface<E> {
    /**
     * The array of Object elements.
     */
    private Object[] container;
    /**
     * The variable that counts the amount of added elements in the container.
     */
    private int count;
    /**
     * The variable that counts the amount of iterated elements.
     */
    private int index;

    /**
     * The constructor.
     */
    public SimpleSet() {
        container = new Object[3];
    }

    /**
     * @param e is the type parameter that should be added in the container.
     */
    @Override
    public void add(E e) {
        if ((e != null) && (!contains(e))) {
            if (count == container.length) {
                container = Arrays.copyOf(container, (container.length * 3) / 2 + 1);
            }
            container[count++] = e;
        }
    }

    /**
     * @param obj is the parameter that is checked whether or not it is inside the container.
     * @return true or false.
     */
    private boolean contains(Object obj) {
        boolean result = false;
        for (int i = 0; i < count; i++) {
            if (obj.equals(container[i])) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * @return true or false;
     */
    @Override
    public boolean hasNext() {
        boolean result = false;
        if (index < count) {
            result = true;
        }
        return result;
    }

    /**
     * @return the next element in the container.
     */
    @Override
    public E next() {
        if (hasNext()) {
            return (E) container[index++];
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * @return all the added elements within the container.
     */
    public Object[] getAllElements() {
        Object[] array = new Object[count];
        for (int i = 0; i < count; i++) {
            array[i] = container[i];
        }
        return array;
    }
}