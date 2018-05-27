package ru.job4j.h6testtask.t1bomberman;

/**
 * Класс героя.
 */
public class Hero implements Comparable<Hero> {
    /**
     * Положение по оси X.
     */
    private volatile int x;
    /**
     * Положение по оси Y.
     */
    private volatile int y;
    /**
     * Имя.
     */
    private final String name;

    /**
     * @param pos одномерный массив со значениями положения героя по осям X и Y.
     * @param name имя.
     */
    public Hero(int[] pos, String name) {
        this.x = pos[0];
        this.y = pos[1];
        this.name = name;
    }

    /**
     * @return положение по оси X.
     */
    public int getX() {
        return this.x;
    }

    /**
     * @return положение по оси Y.
     */
    public int getY() {
        return this.y;
    }

    /**
     * @param x значение для установки нового положения по оси X.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @param y значение для установки нового положения по оси Y.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @param h ссылка на экземпляр объекта класса Hero для сравнения.
     * @return 0, если экземпляры равны и 1, если - нет.
     */
    @Override
    public int compareTo(Hero h) {
        return ((this.x == h.x) && (this.y == h.y)) ? 0 : 1;
    }

    /**
     * @return строковое представление.
     */
    public String toString() {
        return String.format("%s [%d %d]", name, x, y);
    }
}