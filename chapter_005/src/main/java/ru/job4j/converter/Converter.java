package ru.job4j.converter;
import java.util.Iterator;

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
            @Override
            public boolean hasNext() {
                while (it.hasNext()) {
                    if (it.next().hasNext()) {
                        return true;
                    } else {
                        it.next();
                    }
                }
                return false;
            }
            @Override
            public Integer next() {
                return it.next().next();
            }
        };
    }
}