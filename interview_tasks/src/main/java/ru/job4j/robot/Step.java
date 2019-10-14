package ru.job4j.robot;

/**
 * @author Vitaly Vasilyev, date: 14.10.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Step {
    /**
     * Шаг по оси X.
     */
    private final int x;
    /**
     * Шаг по оси Y.
     */
    private final int y;

    /**
     * @param x x.
     * @param y y.
     */
    public Step(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return шаг по оси X.
     */
    public int getX() {
        return x;
    }

    /**
     * @return шаг по оси Y.
     */
    public int getY() {
        return y;
    }

    /**
     * @return представление.
     */
    @Override
    public String toString() {
        return "[x = " + x + ", y = " + y + "]";
    }
}