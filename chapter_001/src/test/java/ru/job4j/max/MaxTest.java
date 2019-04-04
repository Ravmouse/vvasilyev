package ru.job4j.max;

import org.junit.Test;
import ru.job4j.h4operator.t2max.Max;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test class.
*
* @author Vitaly Vasilyev (rav.energ@rambler.ru)
* @version $Id$
* @since 0.1
*/
public class MaxTest {
    /**
    * Test add.
    */
    @Test
    public void whenAddFirstAndSecondThenMax() {
        Max maxim = new Max();
        int result = maxim.max(1, 2);
        assertThat(result, is(2));
        result = maxim.max(-4, 10, 0);
        assertThat(result, is(10));
    }
}