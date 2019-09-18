package ru.job4j.h1lambda.t1countfunction;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Rav, date: 18.09.2019
 * @version 1.0
 */
public class CountFunctionTest {
    /**
     * Тест линейной функции.
     */
    @Test
    public void rangeTestLinear() {
        CountFunction f = new CountFunction();
        List<Double> list = f.range(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D, 17D);
        assertThat(list, is(expected));
    }

    /**
     * Тест квадратичной функции.
     */
    @Test
    public void rangeTestQuadratic() {
        CountFunction f = new CountFunction();
        List<Double> list = f.range(5, 8, x -> 2 * Math.pow(x, 2) + 2 * x + 1);
        List<Double> expected = Arrays.asList(61D, 85D, 113D, 145D);
        assertThat(list, is(expected));
    }

    /**
     * Тест логарифмической функции.
     */
    @Test
    public void rangeTestLogarithmic() {
        CountFunction f = new CountFunction();
        List<Double> list = f.range(100, 102, Math::log10);
        List<Double> expected = Arrays.asList(2D, 2.0043213737826426, 2.0086001717619175);
        assertThat(list, is(expected));
    }
}