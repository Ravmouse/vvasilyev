package ru.job4j.h1lambda.t1countfunction;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 *
 * @author Vitaly Vasilyev, date: 18.09.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class CountFunction {
    /**
     * @param start начальное значение.
     * @param end конечное значение.
     * @param func реализация интерфейса Function.
     * @return список вещественных чисел - результатов выполнения функции.
     */
    public List<Double> range(int start, int end, final Function<Double, Double> func) {
        final List<Double> result = new ArrayList<>();
        for (int index = start; index <= end; index++) {
            result.add(func.apply((double) index));
        }
        return result;
    }
}