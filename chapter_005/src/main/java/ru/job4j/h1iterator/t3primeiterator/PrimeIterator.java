package ru.job4j.h1iterator.t3primeiterator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * PrimeIterator class.
 */
public class PrimeIterator implements Iterator {
    /**
     * The array of integer values that may contain prime numbers.
     * Массив целочисленных значений, который может содержать простые числа.
     */
    private final int[] values;
    /**
     * The element's index that is returned by the iterator.
     * Индекс элемента, возвращаемого итератором.
     */
    private int index;

    /**
     * @param values is the array of integer elements that is passed to the constructor.
     */
    public PrimeIterator(int[] values) {
        this.values = values;
    }

    /**
     * @return true if the array's element is a prime number.
     */
    public boolean hasNext() {
        boolean result = false;
        int i = 2;
        while (values.length > index) {
            if (!isPrime(values[index])) {
                index++;
            } else {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * @return the next array's element only if the element is a prime number.
     */
    public Object next() {
        if (this.hasNext()) {
            return values[index++];
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * @param value is the number to be checked whether it is prime or not.
     * @return true if the value is a prime number, and false otherwise.
     */
    private boolean isPrime(int value) {
        boolean result = true;
        int i = 2;
        for (; i < value; i++) {
            if (value % i == 0) {
                result = false;
                break;
            }
        }
        return result;
    }
}