package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test class.
 *
 * @author Vitaly Vasilyev (rav.energ@rambler.ru)
 * @version $Id$
 * @since 0.1
 */
public class CounterTest {

    /**
     * Test add.
     */
    @Test
    public void whenAreaSetThreePointsThenTriangleArea() {
        Counter counter = new Counter();
        int result = counter.add(1, 10);
        assertThat(result, is(30));
    }
}
