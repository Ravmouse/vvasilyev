package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Test class.
 * @author Vitaly Vasilyev (rav.energ@rambler.ru)
 * @version $Id$
 * @since 0.1
 */
public class PaintTest {
    /**
     * Test of the pyramid that has two-column length.
     */
    @Test
    public void whenPyramidWithHeightTwoThenStringWithTwoRows() {
        Paint paint = new Paint();
        String result = paint.pyramid(2);
        String expected = String.format(" ^ %s^^^", System.getProperty("line.separator"));
        assertThat(result, is(expected));
    }
    /**
     * Test of the pyramid that has three-column length.
     */
    @Test
    public void whenPyramidWithHeightThreeThenStringWithThreeRows() {
        Paint paint = new Paint();
        String result = paint.pyramid(3);
        final String line = System.getProperty("line.separator");
        String expected = String.format("  ^  %s ^^^ %s^^^^^", line, line);
        assertThat(result, is(expected));
    }
}