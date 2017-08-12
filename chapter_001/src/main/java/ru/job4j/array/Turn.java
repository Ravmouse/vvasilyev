package ru.job4j.array;

/**
 * Class Turn.
 * @author Vitaly Vasilyev
 * @version $Id$
 * @since 0.1
 */
public class Turn {
    /**
     * The method takes an array of integer values as a passed parameter and reverses it.
     * @param array A sequence of integer values.
     * @return A reference to the same object.
     */
    public int[] back(int[] array) {
        int tmp = 0;
        for (int i = 0; i < array.length / 2; i++) {
            tmp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = tmp;
        }
        return array;
    }
}