package ru.job4j.array;
/**
 * Class RotateArray.
 * @author Vitaly Vasilyev
 * @version $Id$
 * @since 0.1
 */
public class RotateArray {
    /**
     * The method rotates an array of integer values in clockwise order.
     * @param array Array of integer values.
     * @return The reference to rotated array.
     */
    public int[][] rotate(int[][] array) {
        int pass = array.length - 1;
        int start = 0;
        int end = pass;
        while (start != pass) {
            int tmp = array[0][start];
            array[0][start] = array[end][0];
            array[end][0] = array[pass][end];
            array[pass][end] = array[start][pass];
            array[start][pass] = tmp;
            start++; end--;
        }
        return array;
    }
}
