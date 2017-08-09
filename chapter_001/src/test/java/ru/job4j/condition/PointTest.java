package ru.job4j.condition;

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
public class PointTest {
    /**
     * Test add.
     */
    @Test
    public void whenArgsAreSetThenIsPointOnLine() {
        Point point = new Point(2, 7);
        boolean result = point.is(2, 3);
        assertThat(result, is(true));
    }
}
