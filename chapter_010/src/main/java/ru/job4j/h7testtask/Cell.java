package ru.job4j.h7testtask;

/**
 * @author Vitaly Vasilyev, date: 03.07.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Cell {
    /**
     * Позиция.
     */
    private final int position;
    /**
     * Вид.
     */
    private String view = ".";

    /**
     * @param position позиция.
     */
    public Cell(int position) {
        this.position = position;
    }

    /**
     * @return позицию ячейки.
     */
    public int getPosition() {
        return position;
    }

    /**
     * @param view вид.
     */
    public void changeView(String view) {
        this.view = view;
    }

    /**
     * @return строк.представление.
     */
    @Override
    public String toString() {
        return this.view;
    }
}