package ru.job4j.h6testtask.t1bomberman;
import java.util.Scanner;

/**
 * Класс героя.
 */
public class Hero extends Thread {
    /**
     * Ссылка на экз.класса Board.
     */
    private final Board mainBoard;
    /**
     * Ссылка на экз.класса Cell, как на то место, где расположен герой.
     */
    private volatile Cell source;
    /**
     * Ссылка на экз.класса Cell, как на то место, куда должен пойти герой.
     */
    private Cell dest;
    /**
     * Шаг перемещения по оси x.
     */
    private int dX = 1;
    /**
     * Шаг перемещения по оси y.
     */
    private int dY = 1;
    /**
     * Ссылка на сканнер, для ввода пользователя.
     */
    private final Scanner scanner = new Scanner(System.in);

    /**
     * @param name имя при создании.
     * @param x положение по оси x.
     * @param y положение по оси y.
     * @param mainBoard ссылка на экз.класса Board.
     */
    public Hero(String name, int x, int y, Board mainBoard) {
        super(name);
        this.mainBoard = mainBoard;
        this.source = this.mainBoard.getCells()[x][y];
    }

    /**
     * @return ссылку на ту ячейку, где находится экз.калсса Hero.
     * Нужен для потока main, чтобы на одном поле рисовать все имеющиеся потоки.
     */
    public Cell getSource() {
        return this.source;
    }

    /**
     * Работа потока.
     */
    @Override
    public void run() {
        if (super.getName().equals("M")) { //Если имя экз.класса - "B", то поток создается с управлением для польз.
            executeAuto(source.getX(), source.getY());
        } else {
            executeManual(source.getX(), source.getY());
        }
    }

    /**
     * @param x начальное положение по оси x при создании.
     * @param y начальное положение по оси y при создании.
     */
    private void executeAuto(int x, int y) {
        mainBoard.getBoard()[source.getX()][source.getY()].lock(); //Сделать лок того положения, где создались.
        while (true) {
            if (System.currentTimeMillis() % 2 == 0) { //Если время - четное, то
                x += dX;
                if (mainBoard.isItSafe(x, y)) {        //Проверяется не вышел ли x за пределы диапазона.
                    dest = mainBoard.getCells()[x][y]; //Если - нет, то присваивается.
                } else {
                    dX = -dX;                 //Если x вышел за пределы диапазона, то dX меняет знак на противоположный.
                    x += 2 * dX;              //x меняет свое положение на 2 клетки назад.
                    dest = mainBoard.getCells()[x][y];
                }
            } else {
                y += dY;
                if (mainBoard.isItSafe(x, y)) {
                    dest = mainBoard.getCells()[x][y];
                } else {
                    dY = -dY;
                    y += 2 * dY;
                    dest = mainBoard.getCells()[x][y];
                }
            }
            try {
                if (mainBoard.move(source, dest)) {
                    source = dest;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param x начальное положение по оси x при создании объекта.
     * @param y начальное положение по оси y при создании объекта.
     */
    public void executeManual(int x, int y) {
        mainBoard.getBoard()[source.getX()][source.getY()].lock();
        while (true) {
            switch (scanner.nextLine()) {
                case "w": x--;
                          break;
                case "s": x++;
                          break;
                case "a": y--;
                          break;
                case "d": y++;
                default:
            }
            if (mainBoard.isItSafe(x, y)) {
                dest = mainBoard.getCells()[x][y];
            } else {
                continue;
            }
            try {
                if (mainBoard.move(source, dest)) {
                    source = dest;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}