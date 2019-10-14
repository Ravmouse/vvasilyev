package ru.job4j.robot;

/**
 * @author Vitaly Vasilyev, date: 14.10.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Cell {
    /**
     * Позиция по оси X.
     */
    private final int x;
    /**
     * Позиция по оси Y.
     */
    private final int y;
    /**
     * Значение - 0 или 1.
     */
    private final int value;
    /**
     * Счетчик.
     */
    private int count;
    /**
     * Посещена или нет.
     */
    private boolean visited;

    /**
     * @param x позиция по оси X.
     * @param y позиция по оси Y.
     * @param value значение - 0 или 1.
     */
    public Cell(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    /**
     * @return позицию по оси X.
     */
    public int getX() {
        return x;
    }

    /**
     * @return позицию по оси Y.
     */
    public int getY() {
        return y;
    }

    /**
     * @return значение.
     */
    public int getValue() {
        return value;
    }

    /**
     * @param count число для счетчика.
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * @return счетчик.
     */
    public int getCount() {
        return count;
    }

    /**
     * @return посещена или нет.
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * @param visited значение.
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     * @return представление.
     */
    @Override
    public String toString() {
        return String.format("{x=%d, y=%d, %d, %b}", x, y, value, visited);
    }
}