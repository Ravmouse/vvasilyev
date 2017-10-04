package ru.job4j.twodimiterator;
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
     * The index of the first dimension.
     */
    private int indexOut = 0;
    /**
     * The index of the second dimension.
     */
    private int indexIn = 0;

    /**
     * @param array for the constructor.
     */
    public TwoDimIterator(final int[][] array) {
        this.array = array;
    }

    /**
     * @return the ref to private field.
     */
    public int[][] getArray() {
        return this.array;
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
     * @return the next element in the array.
     */
    @Override
    public Object next() {
        try {
            return array[indexOut][indexIn++];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoSuchElementException();
        }
    }
}