package ru.job4j.h2stream.t5matrixtolist;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Vitaly Vasilyev, date: 21.09.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class TransformMatrix {
    /**
     * @param matrix массив массивов целых значений.
     * @return список целых значений.
     */
    public static List<Integer> collectFromMatrixToList(Integer[][] matrix) {
        return Stream.of(matrix).flatMap(Arrays::stream).collect(Collectors.toList());
    }
}