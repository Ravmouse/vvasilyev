package ru.job4j.h1iterator.eveniterator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * EvenIterator class.
 */
public class EvenIterator implements Iterator {
    /**
     * The ref to an array.
     */
    private final int[] values;
    /**
     * The position of current element.
     */
    private int index;

    /**
     * @param values for the constructor.
     */
    public EvenIterator(int[] values) {
        this.values = values;
    }

    /**
     * @return true if there are even numbers in the values field and false when the end of an array is reached.
     */
    @Override
    public boolean hasNext() {
        boolean result = false;
        while (values.length > index) {
            if (values[index] % 2 != 0) {
                index++;
            } else {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * @return the next element of values field.
     */
    @Override
    public Object next() {
        if (this.hasNext()) {
            return values[index++];
        } else {
            throw new NoSuchElementException();
        }
    }
}