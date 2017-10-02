package ru.job4j.eveniterator;
import java.util.Iterator;

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
     * @return true if there is even numbers in the values field and false when the end of an array is reached.
     */
    @Override
    public boolean hasNext() {
        while (values.length > index) {
            if (values[index] % 2 != 0) {
                index++;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * @return the next element of values field.
     */
    @Override
    public Object next() {
        return values[index++];
    }
}