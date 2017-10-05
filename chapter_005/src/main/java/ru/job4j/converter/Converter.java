package ru.job4j.converter;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Converter class.
 */
public class Converter {
    /**
     * @param it - an iterator of an iterator of Integer.
     * @return the iterator of all Integer elements that are contained in the passed parameter.
     */
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            private Iterator<Integer> itr = it.next();
            private boolean hasNextChanged;

            @Override
            public boolean hasNext() {
                boolean result = false;
                if (itr.hasNext()) {
                    result = true;
                    hasNextChanged = true;
                } else {
                    if (it.hasNext()) {
                        itr = it.next();
                        result = true;
                        hasNextChanged = true;
                    }
                }
                return result;
            }
            @Override
            public Integer next() {
                Integer result;
                if (hasNextChanged) {
                    result = itr.next();
                } else {
                    if (hasNext()) {
                        hasNextChanged = false;
                        result = itr.next();
                    } else {
                        throw new NoSuchElementException();
                    }
                }
                return result;
            }
        };
    }
}