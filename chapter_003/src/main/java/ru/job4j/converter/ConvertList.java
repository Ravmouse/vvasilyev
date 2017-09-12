package ru.job4j.converter;
import java.util.List;
import java.util.ArrayList;

/**
 * There is both a conversion of integer numbers from two-dimensional array into List interface & conversion from
 * List interface into two-dimensional array with an amount of rows.
 * В классе осуществляется конвертация целочисленных значений из двумерного массива в экземпляр класса, реализующего
 * инт. List и, наоборот, из List в двумерный массив с указанием кол-ва строк.
 */
public class ConvertList {

    /**
     * Converts a two-dimensional array into List interface.
     * @param array of int elements to convert.
     * @return List of Integer elements.
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> result = new ArrayList<>();
        for (int[] outer : array) {
            for (int inner : outer) {
                result.add(inner);
            }
        }
        return result;
    }

    /**
     * Converts List interface into two-dimensional array with an amount of rows.
     * @param list - class implementing List interface.
     * @param rows - how many rows will be contained in array.
     * @return two-dimensional array.
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int[][] result = new int[rows][];
        int i = 0;
        int j = 0;
        int count;
        if (list.size() % rows == 0) {
            count = list.size() / rows;
        } else {
            count = (int) Math.ceil(((double) list.size()) / ((double) rows));
        }
        result[i] = new int[count];
        for (Integer value : list) {
            result[i][j++] = (value == null) ? 0 : value;
            if ((j == count) && (i < rows - 1)) {
                j = 0;
                i++;
                result[i] = new int[count];
            }
        }
        if (j != count - 1) {
            for (; j < count - 1; j++) {
                result[i][j] = 0;
            }
        }
        if (i != rows - 1) {
            j = 0;
            for (; j < count - 1; j++) {
                result[i][j] = 0;
            }
        }
        return result;
    }
}