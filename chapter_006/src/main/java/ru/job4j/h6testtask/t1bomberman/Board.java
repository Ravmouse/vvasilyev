package ru.job4j.h6testtask.t1bomberman;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Класс Board.
 */
public class Board {
    /**
     * Массив локов.
     */
    private final ReentrantLock[][] board;
    /**
     * Массив ячеек.
     */
    private volatile Cell[][] cells;

    /**
     * В конструкторе происходит создание двух массивов: массива ReentrantLock и массива Cell.
     * @param size размер поля.
     */
    public Board(int size) {
        this.board = new ReentrantLock[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
        this.cells = new Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    /**
     * @return ссылку на массив ячеек. Нужен для класса Hero.
     */
    public Cell[][] getCells() {
        return cells;
    }

    /**
     * @return ссылку на массив локов. Нужен для класса Hero.
     */
    public ReentrantLock[][] getBoard() {
        return board;
    }

    /**
     * @param source ячейка, откуда нужно "ходить".
     * @param dest ячейка, куда нужно "пойти".
     * @return true, если на ячейку dest можно "пойти", и false - если нет.
     * @throws InterruptedException в случае возникновения исключения.
     */
    public boolean move(Cell source, Cell dest) throws InterruptedException {
        boolean result = false;
            if (board[dest.getX()][dest.getY()].tryLock(500, TimeUnit.MILLISECONDS)) {
                try {
                    result = true;
                } finally {
                    board[source.getX()][source.getY()].unlock();
                }
                Thread.sleep(3100);
            }
        return result;
    }

    /**
     * @param size размер поля.
     * @param heroes ссылка на переменное число экземпляров класса Hero.
     */
    private void drawTheBoard(int size, Hero... heroes) {
        int i, j, k, empty;
        for (i = 0; i < size; i++) {
            for (j = 0; j < size; j++) {
                empty = 0;
                for (k = 0; k < heroes.length; k++) {
                    if ((heroes[k].getSource().getX() == i) && (heroes[k].getSource().getY() == j)) {
                        System.out.print(heroes[k].getName());
                        break;
                    } else {
                        empty++; //Если на клетке [i, j] нет ни одного объекта Hero, то увеличить переменную.
                    }
                }
                if (empty == heroes.length) { //Если переменная равна числу аргументов heroes, то нарисовать ОДИН раз, а
                    System.out.print(".");  //не столько, сколько аргументов в heroes. Чтобы размер поля не увеличивался
                }                           // в 2-3 раза.
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * @param x значение для проверки.
     * @param y значение для проверки.
     * @return true, если x или y не выходят за пределы поля и false, если - выходят.
     */
    public boolean isItSafe(int x, int y) {
        boolean result = true;
        if ((x < 0) || (x >= board.length) || (y < 0) || (y >= board.length)) {
            result = false;
        }
        return result;
    }

    /**
     * @param args .
     * @throws InterruptedException .
     */
    public static void main(String[] args) throws InterruptedException {
        int size = 5;
        Board b = new Board(size);
        Hero h1 = new Hero("B", 0, 0, b);
        h1.start();
        Hero h2 = new Hero("M", 4, 4, b);
        h2.start();
        Hero h3 = new Hero("M", 4, 0, b);
        h3.start();
        while (true) {
            b.drawTheBoard(size, h1, h2, h3);
            Thread.sleep(3000);
        }
    }
}

/**
 * Класс Cell.
 */
class Cell {
    /**
     * Положение по оси x.
     */
    private final int x;
    /**
     * Положение по оси y.
     */
    private final int y;

    /**
     * @param x положение по оси x при создании объекта.
     * @param y положение по оси y при создании объекта.
     */
    Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return положение по оси x.
     */
    public int getX() {
        return x;
    }

    /**
     * @return положение по оси y.
     */
    public int getY() {
        return y;
    }
}