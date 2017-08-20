package ru.job4j.drawfigure;

/**
 * Class Square.
 */
public class Square implements Shape {
    /**
     * Draws a square using StringBuilder.
     * @return The String representation of StringBuilder object.
     */
    @Override
    public String pic() {
        StringBuilder sb = new StringBuilder();
        sb.append("#########\r\n");
        sb.append("#       #\r\n");
        sb.append("#       #\r\n");
        sb.append("#       #\r\n");
        sb.append("#########");
        return sb.toString();
    }
}