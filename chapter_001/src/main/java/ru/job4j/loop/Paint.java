package ru.job4j.loop;
/**
 * Class Paint.
 * @author Vitaly Vasilyev
 * @version $Id$
 * @since 0.1
 */
public class Paint {
    /**
     * The method draws a pyramid with an input-number-column length.
     * @param h The length of the pyramid.
     * @return The string representation of the pyramid in one line.
     */
    public String pyramid(int h) {
        int z = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < h; i++) {
            for (int j = 1; j <= 2 * h - 1; j++) {
                if ((j >= h - z) & (j <= h + z)) {
                    sb.append("^");
                } else {
                    sb.append(" ");
                }
            }
            z++;
            if (i < h - 1) {
                sb.append("\r\n");
            }
        }
        return sb.toString();
    }
}

