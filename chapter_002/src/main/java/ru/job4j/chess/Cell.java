package ru.job4j.chess;

/**
 * Class Cell.
 */
public class Cell {
    /**
     * Координата х ячейки.
     */
    private int x;
    /**
     * Координата y ячейки.
     */
    private int y;
    /**
     * Значение b линейной функции типа y = a*x + b.
     */
    private int b1;
    /**
     * Значение b линейной функции типа y = -a*x + b.
     */
    private int b2;

//    enum ABC {
//        A(1), B(2), C(3), D(4), E(5), F(6), G(7), H(8);
//        ABC(int val) { }
//    }

    /** Конструктор.
     * @param x - координата по оси х.
     * @param y - координата по оси y.
     */
    public Cell(int x, int y) {
        if ((x > 0 && x < 9) && (y > 0 && y < 9)) {
            this.x = x;
            this.y = y;
            findFirstFunc();
            findSecondFunc();
        }
    }

    /**
     * Находит для координат x и y этой ячейки значение b линейной функции типа y = a*x + b.
     * @return значение b линейной функции y = a*x + b.
     */
    public int findFirstFunc() {
        int result = 0;
        for (int i = -7; i < 8; i++) {
            if (this.y == this.x + i) {
                result = i;
                break;
            }
        }
        this.b1 = result;
        return result;
    }

    /**
     * Находит для координат x и y этой ячейки значение b линейной функции типа y = -a*x + b.
     * @return значение b линейной функции типа y = -a*x + b.
     */
    public int findSecondFunc() {
        int result = 0;
        for (int i = 2; i < 17; i++) {
            if (this.y == i - this.x) {
                result = i;
                break;
            }
        }
        this.b2 = result;
        return result;
    }

    /**
     * Геттер для координаты х.
     * @return int.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Геттер для координаты y.
     * @return int.
     */
    public int getY() {
        return this.y;
    }

    /**
     * Вычисляет массив ячеек Cell от текущей ячейки до другой ячейки, но только для линейной функции типа y = a*x + b
     * и возвращает этот массив.
     * @param dist - другая ячейка, до которой должен быть вычислен массив Cell.
     * @return массив Cell.
     */
    public Cell[] wayForFirstFunc(Cell dist) {
        int min = Integer.min(this.x, dist.x);
        int max = Integer.max(this.x, dist.x);
        Cell[] result = new Cell[max - min];
        int pos = 0;
        if (this.x == min) {
            for (int i = min + 1; i <= max; i++) {
                result[pos++] = new Cell(i, i + this.b1);
            }
        } else {
            for (int i = max - 1; i >= min; i--) {
                result[pos++] = new Cell(i, i + this.b1);
            }
        }
        return result;
    }

    /**
     * Вычисляет массив ячеек Cell от текущей ячейки до другой ячейки, но только для линейной функции типа y = -a*x + b
     * и возвращает этот массив.
     * @param dist - другая ячейка, до которой должен быть вычислен массив Cell.
     * @return массив Cell.
     */
    public Cell[] wayForSecondFunc(Cell dist) {
        int min = Integer.min(this.x, dist.x);
        int max = Integer.max(this.x, dist.x);
        Cell[] result = new Cell[max - min];
        int pos = 0;
        if (this.x == min) {
            for (int i = min + 1; i <= max; i++) {
                result[pos++] = new Cell(i, this.b2 - i);
            }
        } else {
            for (int i = max - 1; i >= min; i--) {
                result[pos++] = new Cell(i, this.b2 - i);
            }
        }
        return result;
    }

    /**
     * Вычисляет, лежит ли другая ячейка (cell) на одной из двух линейных функциях текущей ячейки.
     * Если лежит на ф-ии типа y = a*x + b, то возвращает right.
     * Если лежит на ф-ии типа y = -a*x + b, то возвращает left.
     * Если не лежит ни там, ни там, то возвращает null.
     * @param cell - другая ячейка, которую нужно проверить, лежит ли она хотя бы на одной функции.
     * @return Объект типа String.
     */
    public String find(Cell cell) {
        if (cell.y == cell.x + this.b1) {
            return "right";
        } else if (cell.y == this.b2 - cell.x) {
            return "left";
        }
        return "none";
    }

    /**
     * Переопределение toString() для отображения экземпляра класса.
     * @return Объект типа String.
     */
    public String toString() {
        return "[" + this.x + "; " + this.y + "]";
    }
}