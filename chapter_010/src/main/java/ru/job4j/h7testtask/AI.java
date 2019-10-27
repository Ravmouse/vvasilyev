package ru.job4j.h7testtask;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author Vitaly Vasilyev, date: 21.08.2019, e-mail: rav.energ@rambler.ru
 * @version 1.2
 */
public class AI {
    /**
     * Список с ходами 1-го игрока.
     */
    private List<Integer> listOne = new ArrayList<>();
    /**
     * Список с ходами 2-го игрока.
     */
    private List<Integer> listTwo = new ArrayList<>();
    /**
     * Поле.
     */
    private final Board board;
    /**
     * Флаг переключения между двумя List'ами.
     */
    private boolean flag;
    /**
     * Выигрышные комбинации.
     */
    private final List<List<Integer>> winners;

    /**
     * @param board поле.
     */
    public AI(final Board board) {
        this.board = board;
        winners = construct(board);
    }

    /**
     * @return список со списками целочисленных значений.
     */
    public List<List<Integer>> getWinners() {
        return winners;
    }

    /**
     * @param board поле.
     * @return список со списками целочисленных значений - выигрышные комбинации.
     * 1. Внеш.цикл продолжается на величину [(длина поля * 2) + 2]. Если 3, то 8 раз. Если 5, то 12 раз и т.д.
     * 2. Внутр.цикл продолжается на величину длины поля (3 раза, 5 раз и т.д.).
     * 3. Счетчик i также используется для подсчета 3 промежутков:
     * 4. когда будут перебраны все горизонтали в кол-ве длины поля (value нужно увеличивать на 1)
     * 5. когда будут перебраны все вертикали в кол-ве длины поля (value нужно увеличивать на величину side)
     * 6. когда будут перебраны 2 диагонали (в одном случает value нужно увеличивать на side + 1),
     * в другом - на side - 1.
     */
    private List<List<Integer>> construct(final Board board) {
        int count = 0;
        int value = 0;
        int side = board.getSide();
        int adder = 0;
        int tmp = 0;
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> inner = new ArrayList<>();
        for (int i = 0; i < (side * 2) + 2; i++) { //1.
            while (count < side) { //2.
                if (i < side) { //3.
                    inner.add(value++);
                } else if (i >= side && i < side * 2) {
                    if (count == 0) {
                        value = tmp++;
                    }
                    inner.add(value);
                    value += side; //5.
                } else {
                    inner.add(value);
                    value += adder; //6.
                }
                count++;
            }
            result.add(new ArrayList<>(inner));
            inner.clear();
            count = 0;
            value = (i == side - 1) ? 0 : value;
            if (i == side * 2 - 1) {
                value = 0;
                adder = side + 1;
            } else if (i == side * 2) {
                value = side - 1;
                adder = side - 1;
            }
        }
        return result;
    }

    /**
     * @param symbol символ.
     * @return true, если кол-во symbol среди выигрышных комбинаций равно длине поля, и false, если - нет.
     */
    public boolean isWinner(final String symbol) {
        boolean result = false;
        int count = 0;
        for (List<Integer> list : winners) {
            for (Integer i : list) {
                if (symbol.equals(board.getCells().get(i).toString())) {
                    count++;
                }
            }
            if (result = count == board.getSide()) {
                break;
            }
            count = 0;
        }
        return result;
    }

    /**
     * @param symbol символ.
     * @param amount кол-во ходов оппонента в одной из выигрышных комбинаций.
     * @return позицию на поле, куда нужно поставить свой символ, чтобы не дать возможность оппоненту.
     * 1. Просмотреть все целые значения каждого списка из winners.
     * 2. Если amount == 2, то любые 2 значения из 3 должны иметь символы, отличные от symbol и от ".",
     *    это и будут 2 хода оппонента, 3-й ход которого нужно занять, чтобы не дать выиграть.
     */
    public int getEnemyMove(final String symbol, int amount) {
        int result = -1;
        int count = 0;
        List<Cell> cells = board.getCells();
        for (List<Integer> list : winners) {
            for (Integer i : list) {
                if (!symbol.equals(cells.get(i).toString()) && !".".equals(cells.get(i).toString())) {
                    count++;
                } else {
                    result = i;
                }
            }
            if (count == amount && isValidMove(result)) {
                break;
            }
            count = 0;
            result = -1;
        }
        return result;
    }

    /**
     * @return произвольное значение из списка, в котором все элементы типа Cell имеют вид ".".
     */
    public int getRandom() {
        final List<Integer> list = board.getCells().stream()
                .filter(cell -> ".".equals(cell.toString()))
                .map(Cell::getPosition)
                .collect(Collectors.toList());
        return list.get(new Random().nextInt(list.size()));
    }

    /**
     * @param move ход.
     * @param symbol символ.
     */
    public void move(int move, final String symbol) {
        if (!flag) {
            listOne.add(move);
            flag = true;
        } else {
            listTwo.add(move);
            flag = false;
        }
        board.changeCell(move, symbol);
    }

    /**
     * @param move ход, который нужно проверить.
     * @return true, если ход move присутствует в одном из списков, и false, если - нет.
     */
    public boolean isValidMove(int move) {
        boolean result = true;
        if (listOne.contains(move) || listTwo.contains(move)) {
            result = false;
        }
        return result;
    }

    /**
     * @return true, если кол-во ходов в обоих списках равно площади board, и false, если - нет.
     */
    public boolean isFull() {
        return (listOne.size() + listTwo.size()) == board.getArea();
    }
}