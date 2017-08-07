package ru.job4j.calculator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test.
*
* @author Vitaly Vasilyev (rav.energ@rambler.ru)
* @version $Id$
* @since 0.1
*/
public class CalculatorTest {
    /**
    * Test add.
    */
    @Test
    public void whenAddOnePlusOneThenTwo() {
        Calculator calc = new Calculator();
        calc.add(1D, 1D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));

        calc.sub(8D, 3D);
        result = calc.getResult();
        expected = 5D;
        assertThat(result, is(expected));

        calc.div(15D, 3D);
        result = calc.getResult();
        expected = 5D;
        assertThat(result, is(expected));

        calc.mult(3D, 4D);
        result = calc.getResult();
        expected = 12D;
        assertThat(result, is(expected));
    }
}