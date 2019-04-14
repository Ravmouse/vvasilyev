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
public class BoardTest {
    /**
     * Test of 3*3 board.
     */
    @Test
    public void whenPaintBoardWithWidthThreeAndHeightThreeThenStringWithThreeColsAndThreeRows() {
        Board board = new Board();
        String result = board.paint(3, 3);
//        final String line = System.getProperty("line.separator");
        final String line = "\r\n";
        String expected = String.format("x x%s x %sx x", line, line);
        assertThat(result, is(expected));
    }
    /**
     * Test of 5*4 board.
     */
    @Test
    public void whenPaintBoardWithWidthFiveAndHeightFourThenStringWithFiveColsAndFourRows() {
        Board board = new Board();
        String result = board.paint(5, 4);
//        final String line = System.getProperty("line.separator");
        final String line = "\r\n";
        String expected = String.format("x x x%s x x %sx x x%s x x ", line, line, line);
        assertThat(result, is(expected));
    }
}