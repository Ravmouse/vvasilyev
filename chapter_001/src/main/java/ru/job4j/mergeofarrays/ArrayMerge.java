package ru.job4j.mergeofarrays;
/**
 * Class ArrayMerge.
 * @author Vitaly Vasilyev
 * @version $Id$
 * @since 0.1
 */
public class ArrayMerge {
    /**
     * The method merges two sorted arrays into one.
     *
     * @param first  The first arrays of integers.
     * @param second The second array of integers.
     * @return The result array contains two passed arrays.
     */
    public int[] mergeOfSortedArrays(int[] first, int[] second) {
        int i = 0, j = 0, k = 0;
        int[] result = new int[first.length + second.length];
        while ((i < first.length) && (j < second.length)) {
            if (first[i] < second[j]) {
                result[k++] = first[i++];
            } else {
                result[k++] = second[j++];
            }
        }
        while (i < first.length) {
            result[k++] = first[i++];
        }
        while (j < second.length) {
            result[k++] = second[j++];
        }
        return result;
    }
}