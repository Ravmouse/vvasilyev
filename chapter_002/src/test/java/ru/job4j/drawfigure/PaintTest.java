package ru.job4j.drawfigure;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Class PaintTest.
 */
public class PaintTest {
    /**
     * Checks if the method draw() returns the same String as in the expected variable.
     */
    @Test
    public void whenTriangleThenDrawTriangle() {
        final String line = System.getProperty("line.separator");
        String expected = String.format("    ^%s   ^ ^%s  ^   ^%s ^     ^%s^^^^^^^^^", line, line, line, line);
        assertThat(new Paint().draw(new Triangle()), is(expected));
    }

    /**
     * Checks if the method draw() returns the same String as in the expected variable.
     */
    @Test
    public void whenSquareThenDrawSquare() {
        final String line = System.getProperty("line.separator");
        String expected = String.format("#########%s#       #%s#       #%s#       #%s#########", line, line, line, line);
        assertThat(new Paint().draw(new Square()), is(expected));
    }
}