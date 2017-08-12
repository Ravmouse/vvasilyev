package ru.job4j.array;

/**
 * Class BubbleSort.
 * @author Vitaly Vasilyev
 * @version $Id$
 * @since 0.1
 */
public class BubbleSort {
    /**
     * The method sorts an array that is passed as a parameter.
     * @param array Array of integer elements.
     * @return The reference to the same object.
     */
    public int[] sort(int[] array) {
        int i, j, tmp = 0;
        for (i = 0; i < array.length; i++) {
            for (j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
        return array;
    }
}