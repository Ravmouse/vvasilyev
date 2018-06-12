package ru.job4j.h6testtask.t1bomberman;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.UnaryOperator;

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
     * Ссылка на сканер.
     */
    private Scanner scanner = new Scanner(System.in);

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
     * @param source ячейка, откуда нужно "ходить".
     * @param dest ячейка, куда нужно "пойти".
     * @return true, если на ячейку dest можно "пойти", и false - если нет.
     * @throws InterruptedException в случае возникновения исключения.
     */
    public boolean move(Cell source, Cell dest) throws InterruptedException {
        boolean result = false;
            if (board[dest.x][dest.y].tryLock(500, TimeUnit.MILLISECONDS)) {
                try {
                    dest.changeDestAndSource(source); //Меняется имя у dest, а потом - имя у source.
                    result = true;
                } finally {
                    board[source.x][source.y].unlock();
                }
                Thread.sleep(1300);
            }
        return result;
    }

    /**
     * Рисует все ячейки (а точнее, их имена). Т.о., на поле появляются герои.
     */
    private void drawTheBoard() {
        int i, j;
        for (i = 0; i < cells.length; i++) {
            for (j = 0; j < cells.length; j++) {
                System.out.print(cells[i][j].name);
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
     * @param xPos положение по оси x.
     * @param yPos положение по оси y.
     * @param name имя.
     */
    private void init(int xPos, int yPos, String name) {
        Cell source = cells[xPos][yPos];
        board[xPos][yPos].lock();
        source.changeName(name);
        if (name.equals("B")) {
            manual(xPos, yPos, source);
        } else {
            auto(xPos, yPos, source);
        }
    }

    /**
     * @param x положение по оси x.
     * @param y положение по оси y.
     * @param source ссылка на ячейку, на которой расположен герой.
     */
    private void manual(int x, int y, Cell source) {
        Cell dest;
        while (true) {
            switch (scanner.nextLine()) {
                case "w": x--;
                          x = x < 0 ? 0 : x;
                          break;
                case "s": x++;
                          x = x == cells.length ? --x : x;
                          break;
                case "a": y--;
                          y = y < 0 ? 0 : y;
                          break;
                case "d": y++;
                          y = y == cells.length ? --y : y;
                default:
            }
            dest = cells[x][y];
            try {
                if ((source != dest) && move(source, dest)) { //1-я часть условия = если пользователь нажал на кнопку
                    source = dest;                            //выхода за пределы поля, то тогда source и dest могут
                }                                             //иметь одну ссылку, и в положении героя вместо буквы
            } catch (InterruptedException e) {                //появляется точка.
                e.printStackTrace();
            }

        }
    }

    /**
     * @param x положение по оси x.
     * @param y положение по оси y.
     * @param source ссылка на ячейку, на которой расположен герой.
     */
    private void auto(int x, int y, Cell source) {
        int dX = 1;
        int dY = 1;
        Cell dest;
        while (true) {
            if (System.currentTimeMillis() % 2 == 0) {
                x += dX;
                if (!isItSafe(x, y)) {
                    dX = -dX;
                    x += 2 * dX;
                }
            } else {
                y += dY;
                if (!isItSafe(x, y)) {
                    dY = -dY;
                    y += 2 * dY;
                }
            }
            dest = cells[x][y];
            try {
                if (move(source, dest)) {
                    source = dest;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param args .
     * @throws InterruptedException .
     */
    public static void main(String[] args) throws InterruptedException {
        int size = 5;
        Board b = new Board(size);
        Thread bomber = new Thread("B") {
            @Override
            public void run() {
                b.init(0, 0, this.getName());
            }
        };
        Thread monster = new Thread("M") {
            @Override
            public void run() {
                b.init(4, 4, this.getName());
            }
        };
        Thread monster2 = new Thread("M") {
            @Override
            public void run() {
                b.init(0, 4, this.getName());
            }
        };
        bomber.start();
        monster.start();
        monster2.start();
        while (true) {
            Thread.sleep(1000);
            b.drawTheBoard();
        }
    }


    /**
     * Класс Cell.
     */
    private class Cell {
        /**
         * Положение по оси x.
         */
        final int x;
        /**
         * Положение по оси y.
         */
        final int y;
        /**
         * Атомарная ссылка на строку - имя.
         */
        final AtomicReference<String> name = new AtomicReference<>();

        /**
         * @param x положение по оси x при создании объекта.
         * @param y положение по оси y при создании объекта.
         */
        Cell(int x, int y) {
            this.x = x;
            this.y = y;
            this.name.set(".");
        }

        /**
         * @param name имя для замены.
         */
        void changeName(String name) {
            if (name != null) {
                this.name.set(name);
            }
        }

        /**
         * Здесь 2 атомарные операции с тем, чтобы println() не успевал рисовать между заменой точки на букву.
         * @param source источник - та ячейка, где герой уже находится.
         */
        void changeDestAndSource(Cell source) {
            this.name.updateAndGet(new UnaryOperator<String>() {
                @Override
                public String apply(String s) {
                    return source.name.get();
                }
            });
            source.name.updateAndGet(new UnaryOperator<String>() {
                @Override
                public String apply(String s) {
                    return ".";
                }
            });
        }
    }
}