package ru.job4j.drawfigure;

/**
 * Class Triangle.
 */
public class Triangle implements Shape {
    /**
     * Draws a triangle using StringBuilder.
     * @return The String representation of StringBuilder object.
     */
    @Override
    public String pic() {
        StringBuilder sb = new StringBuilder();
        sb.append("    ^\r\n");
        sb.append("   ^ ^\r\n");
        sb.append("  ^   ^\r\n");
        sb.append(" ^     ^\r\n");
        sb.append("^^^^^^^^^");
        return sb.toString();
    }
}