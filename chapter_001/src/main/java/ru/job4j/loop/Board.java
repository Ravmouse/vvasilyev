package ru.job4j.loop;
/**
 * Class Board.
 * @author Vitaly Vasilyev
 * @version $Id$
 * @since 0.1
 */
public class Board {
    /**
     * The method draws a chess board.
     * @param width The width of the board
     * @param height The height of the board
     * @return The string line of 'x' and space symbols
     */
    public String paint(int width, int height) {
        int j = 0;
        int counter = width;
        StringBuilder sbuilder = new StringBuilder();
        for (int i = 0; i < height; i++) {
            for (; j < counter; j++) {
                if (j % 2 == 0) {
                    sbuilder.append("x");
                    continue;
                }
                sbuilder.append(" ");
            }
            counter += width;
            if (i < height - 1) {
                sbuilder.append("\r\n");
            }
        }
        return sbuilder.toString();
    }
}