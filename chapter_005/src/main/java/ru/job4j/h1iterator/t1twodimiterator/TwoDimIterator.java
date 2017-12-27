package ru.job4j.h1iterator.t1twodimiterator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * TwoDimIterator class.
 */
public class TwoDimIterator implements Iterator {
    /**
     * Two-Dimensional array.
     */
    private final int[][] array;
    /**
     * The element's index of the first dimension.
     */
    private int indexOut = 0;
    /**
     * The element's index of the second dimension.
     */
    private int indexIn = 0;

    /**
     * @param array for the constructor.
     */
    public TwoDimIterator(final int[][] array) {
        this.array = array;
    }

    /**
     * @return true or false.
     */
    @Override
    public boolean hasNext() {
        boolean result = true;
        if (indexIn == array[indexOut].length) {
            if (indexOut == array.length - 1) {
                result = false;
            } else {
                indexOut++;
                indexIn = 0;
                result = true;
            }
        }
        return result;
    }

    /**
     * @return the next element in this array.
     */
    @Override
    public Object next() {
        if (this.hasNext()) {
            return array[indexOut][indexIn++];
        } else {
            throw new NoSuchElementException();
        }
    }
}