package ru.job4j.array;

import java.util.Arrays;
/**
 * Class ArrayDuplicate.
 * @author Vitaly Vasilyev
 * @version $Id$
 * @since 0.1
 */
public class ArrayDuplicate {
    /**
     * The method removes any duplicate string-values from an array of strings.
     * @param array Array of string elements.
     * @return The reference to the same object w/out duplicate elements.
     */
    public String[] remove(String[] array) {
        int i, j, k, end = 0;
        String tmp;
        for (i = 0; i < array.length - 1; i++) {
            for (j = i + 1; j < array.length - end; j++) {
                if (array[i].equals(array[j])) {
                    tmp = array[j];
                    k = j;
                    while (k < array.length - 1) {
                        array[k] = array[k + 1];
                        k++;
                    }
                    array[k] = tmp;
                    end++;
                    j--;
                }
            }
        }
        return Arrays.copyOf(array, array.length - end);
    }
}