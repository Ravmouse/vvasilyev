package ru.job4j.h7testtask;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Vitaly Vasilyev, date: 04.07.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class AI {
    /**
     * Символ пользователя.
     */
    private final String uSymbol;
    /**
     * Символ компьютера.
     */
    private final String aiSymbol;
    /**
     * Список с ходами пользователя.
     */
    private List<Integer> user = new ArrayList<>();
    /**
     * Список с ходами компьютера.
     */
    private List<Integer> ai = new ArrayList<>();
    /**
     * Поле.
     */
    private final Board board;

    /**
     * @param uSymbol символ пользователя.
     * @param aiSymbol символ компьютера.
     * @param board поле.
     */
    public AI(String uSymbol, String aiSymbol, final Board board) {
        this.uSymbol = uSymbol;
        this.aiSymbol = aiSymbol;
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
                return "u";
            } else if (number == -side) {
                return "c";
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
     * @return если символ пользователя найден, то возвращается инкрементированное значение number, а иначе -
     * декрементированное number.
     */
    private int condition(int index, List<Cell> cells, int number) {
        if (uSymbol.equals(cells.get(index).toString())) {
            number++;
        } else if (aiSymbol.equals(cells.get(index).toString())) {
            number--;
        }
        return number;
    }

    /**
     * @return произвольное значение из списка, в котором все элементы типа Cell имеют вид ".".
     */
    private int getRandom() {
        List<Integer> list = new ArrayList<>();
        board.getCells().forEach(cell -> {
            if (".".equals(cell.toString())) {
                list.add(cell.getPosition());
            }
        });
        return list.get(new Random().nextInt(list.size()));
    }

    /**
     * @param usrMove ход пользователя.
     */
    public void userMove(int usrMove) {
        user.add(usrMove);
        board.changeCell(usrMove, uSymbol);
    }

    /**
     * @return ход компьютера.
     */
    public int aiMove() {
        int aMove = getRandom();
        ai.add(aMove);
        board.changeCell(aMove, aiSymbol);
        return aMove;
    }

    /**
     * @param move ход, который нужно проверить.
     * @return true, если ход move присутствует в одном из списков, и false, если - нет.
     */
    public boolean isValidMove(int move) {
        boolean result = true;
        if (user.contains(move) || ai.contains(move)) {
            result = false;
        }
        return result;
    }

    /**
     * @return true, если кол-во ходов в обоих списках равно площади board, и false, если - нет.
     */
    public boolean isFull() {
        return (user.size() + ai.size()) == board.getArea();
    }
}