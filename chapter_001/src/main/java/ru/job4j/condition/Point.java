package ru.job4j.condition;
/**
 * Class Point.
 * @author Vitaly Vasilyev
 * @version $Id$
 * @since 0.1
 */
public class Point {
    /**
     * X coordinate of the point.
     */
    private int x;
    /**
     * Y coordinate of the point.
     */
    private int y;

    /**
     * The constructor of the object.
     * @param x X value
     * @param y Y value
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * The getter of X coordinate.
     * @return int
     */
    public int getX() {
        return this.x;
    }

    /**
     * The getter of Y coordinate.
     * @return int
     */
    public int getY() {
        return this.y;
    }

    /**
     * The method returns maximum of two passed parameters.
     * @param a first value
     * @param b second value
     * @return maximum true or false
     */
    public boolean is(int a, int b) {
        return y == a * x + b;
    }
}
