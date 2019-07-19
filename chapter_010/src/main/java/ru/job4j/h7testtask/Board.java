package ru.job4j.h7testtask;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vitaly Vasilyev, date: 03.07.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Board {
    /**
     * Площадь.
     */
    private final int area;
    /**
     * Кол-во клеток по одной стороне.
     */
    private final int side;
    /**
     * Список ячеек.
     */
    private final List<Cell> cells = new ArrayList<>();

    /**
     * @param in кол-во клеток для одной из сторон.
     */
    public Board(int in) {
        this.side = in;
        this.area = (int) Math.pow(in, 2);
        for (int i = 0; i < area; i++) {
            cells.add(new Cell(i));
        }
    }

    /**
     * Отобразить доску с ходами.
     */
    public void showBoard() {
        int x = 0;
        int y = 0;
        for (int number = 0; number < side; number++) {
            for (int i = 0; i < side; i++) {
                System.out.print("\t" + cells.get(x++));
            }
            System.out.print("\t\t");
            for (int i = 0; i < side; i++) {
                System.out.print("\t" + cells.get(y++).getPosition());
            }
            System.out.println();
        }
    }

    /**
     * @param pos позиция.
     * @param symbol символ.
     */
    public void changeCell(int pos, String symbol) {
        cells.get(pos).changeView(symbol);
    }

    /**
     * @return площадь доски.
     */
    public int getArea() {
        return area;
    }

    /**
     * @return кол-во клеток по одной стороне.
     */
    public int getSide() {
        return side;
    }

    /**
     * @return список ячеек.
     */
    public List<Cell> getCells() {
        return cells;
    }
}