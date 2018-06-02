package ru.job4j.h6testtask.t1bomberman;
import java.util.concurrent.ThreadLocalRandom;
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
     * @param size размер поля.
     */
    public Board(int size) {
        this.board = new ReentrantLock[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
    }

    public boolean move(Cell source, Cell dest) throws InterruptedException {
        boolean rsl = false;
            if (board[dest.x][dest.y].tryLock(500, TimeUnit.MILLISECONDS)) {
                try {
                    rsl = true;
                } finally {
                    board[source.x][source.y].unlock();
                }
                Thread.sleep(1000);
            }
        return rsl;
    }

    public static void main(String[] args) throws InterruptedException {
        int size = 100;
        Board b = new Board(size);
        Cell[][] cells = new Cell[size][size];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
        Thread t1 = new Thread(() -> {
            int rand;
            int cX = 40;
            int cY = 40;
            Cell source = cells[cX][cY];
            Cell dest;
            b.board[source.x][source.y].lock();
            while (true) {
                rand = ThreadLocalRandom.current().nextInt(2);
                if (System.currentTimeMillis() % 2 == 0) {
                    cX += rand;
                    dest = cells[cX][cY];
                } else {
                    cY += rand;
                    dest = cells[cX][cY];
                }
                try {
                    if (b.move(source, dest)) {
                        source = dest;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t1.join();
    }
}

class Cell {
    public final int x;
    public final int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }
}