package ru.job4j.primeiterator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * PrimeIterator class.
 */
public class PrimeIterator implements Iterator {
    /**
     * The ref to an array.
     */
    private final int[] values;
    /**
     * The position of current value in the array.
     */
    private int index;

    /**
     * @param values for the constructor.
     */
    public PrimeIterator(int[] values) {
        this.values = values;
    }

    /**
     * @return true if the array's element is the prime number.
     */
    public boolean hasNext() {
        boolean result = false;
        while (values.length > index) {
            if (values[index] > 7) {
                if ((values[index] % 2 != 0) && (values[index] % 3 != 0) && (values[index] % 5 != 0)
                        && (values[index] % 7 != 0)) {
                    result = true;
                    break;
                } else {
                    index++;
                }
            } else {
                if (values[index] % 2 != 0) {
                    result = true;
                    break;
                } else {
                    index++;
                }
            }
        }
        return result;
    }

    /**
     * @return the next array's element.
     */
    public Object next() {
        try {
            return values[index++];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoSuchElementException();
        }
    }
}