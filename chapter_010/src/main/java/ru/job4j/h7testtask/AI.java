package ru.job4j.h7testtask;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author Vitaly Vasilyev, date: 15.08.2019, e-mail: rav.energ@rambler.ru
 * @version 1.1
 */
public class AI {
    /**
     * Символ 1-го игрока.
     */
    private final String symbolOne;
    /**
     * Символ 2-го игрока.
     */
    private final String symbolTwo;
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
     * @param symbolOne символ 1-го игрока.
     * @param symbolTwo символ 2-го игрока.
     * @param board поле.
     */
    public AI(String symbolOne, String symbolTwo, final Board board) {
        this.symbolOne = symbolOne;
        this.symbolTwo = symbolTwo;
        this.board = board;
    }

    /**
     * @return пустую строку, если победителя нет, "u", если пользователь победил, или "c", если компьютер победил.
     * 1. Внеш.цикл продолжается на величину [(длина поля * 2) + 2]. Если 3, то 8 раз. Если 5, то 12 раз и т.д.
     * 2. Внутр.цикл продолжается на величину поля (3 раза, 5 раз и т.д.).
     * 3. Счетчик i также используется для подсчета 3 промежутков:
     * 4. когда будут перебраны все горизонтали в кол-ве величины поля (value нужно увеличивать на 1)
     * 5. когда будут перебраны все вертикали в кол-ве величины поля (value нужно увеличивать на величину side)
     * 6. когда будут перебраны 2 диагонали (в одном случает value нужно увеличивать на side + 1),
     * в другом - на side - 1.
     */
    public String findWinner() {
        String result = "";
        int count = 0;
        int value = 0;
        int side = board.getSide();
        int adder = 0;
        int tmp = 0;
        List<Cell> cells = board.getCells();
        int number = 0;

        for (int i = 0; i < (side * 2) + 2; i++) { //1.
            while (count < side) { //2.
                if (i < side) { //3.
                    number = condition(value, cells, number);
                    value++; //4.
                } else if (i >= side && i < side * 2) {
                    if (count == 0) {
                        value = tmp++;
                    }
                    number = condition(value, cells, number);
                    value += side; //5.
                } else {
                    number = condition(value, cells, number);
                    value += adder; //6.
                }
                count++;
            }

            if (number == side) {
                return symbolOne;
            } else if (number == -side) {
                return symbolTwo;
            }
            number = 0;
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
     * @param index позиция элемента типа Cell в списке.
     * @param cells список.
     * @param number число для подсчета кол-ва совпавших символов.
     * @return если символ 1-го игрока найден, то возвращается инкрементированное значение number,
     *         если символ 2-го игрока найден, то возвращается декрементированное значение number, а иначе - без изменений.
     */
    private int condition(int index, List<Cell> cells, int number) {
        if (symbolOne.equals(cells.get(index).toString())) {
            number++;
        } else if (symbolTwo.equals(cells.get(index).toString())) {
            number--;
        }
        return number;
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