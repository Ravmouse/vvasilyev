package ru.job4j.h7testtask;

import org.apache.log4j.Logger;
import ru.job4j.h7testtask.input.ValidateInput;
import ru.job4j.utils.Utils;
import java.io.IOException;
import java.util.function.BiFunction;

/**
 * @author Vitaly Vasilyev, date: 15.08.2019, e-mail: rav.energ@rambler.ru
 * @version 1.1
 */
public class StartUI {
    /**
     * Логгер.
     */
    private static final Logger LOG = Logger.getLogger(Utils.getNameOfTheClass());
    /**
     * Компьютер.
     */
    private AI ai;
    /**
     * Поле с ячейками.
     */
    private Board board;
    /**
     * Проверка ввода пользователя.
     */
    private ValidateInput input = new ValidateInput();

    /**
     * @throws IOException при появлении ошибок ввода-вывода.
     */
    public void init() throws IOException {
        final BiFunction<String, String, Integer> funcUser = this::user;
        final BiFunction<String, String, Integer> funcComp = this::computer;

        LOG.info("** Крестики-нолики **");
        int amount = input.ask("Введите размер поля для игры: ", 5);
        board = new Board(amount);

        String playOne = input.askPlayer("Игрок #1: компьютер ('с') или человек ('h')? ", "c", "h");
        String nameOne = input.askS("Задайте имя для Игрока #1: ");
        String symbolOne = input.askSymbol("Выберите символ для Игрока #1: ");

        String playTwo = input.askPlayer("Игрок #2, компьютер ('с') или человек ('h')? ", "c", "h");
        String nameTwo = input.askS("Задайте имя для Игрока #2: ");
        String symbolTwo = input.askSymbol("Выберите символ для Игрока #2: ");

        ai = new AI(board);

        BiFunction<String, String, Integer> one = "c".equals(playOne) ? funcComp : funcUser;
        BiFunction<String, String, Integer> two = "c".equals(playTwo) ? funcComp : funcUser;
        launch(one, two, nameOne, nameTwo, symbolOne, symbolTwo);

        input.close();
    }

    /**
     * @param one 1-я функция.
     * @param two 2-я функция.
     * @param nameOne имя 1-го игрока.
     * @param nameTwo имя 2-го игрока.
     * @param symbolOne символ 1-го игрока.
     * @param symbolTwo символ 2-го игрока.
     */
    private void launch(BiFunction<String, String, Integer> one, BiFunction<String, String, Integer> two,
                        String nameOne, String nameTwo, String symbolOne, String symbolTwo) {
        do {
            if (one.apply(nameOne, symbolOne) == -1 || two.apply(nameTwo, symbolTwo) == -1) {
                break;
            }
        } while (true);
    }

    /**
     * @param name имя.
     * @param symbol символ.
     * @return -1, если найден победитель, или 0, если победитель не найден.
     */
    private int checkWinner(String name, String symbol) {
        int result = 0;
        if (ai.isWinner(symbol)) {
            board.showBoard();
            LOG.info("Победил(-а) " + name + "!");
            result = -1;
        }
        return result;
    }

    /**
     * @return -1, если на поле сделаны все ходы.
     */
    private int checkFull() {
        int result = 0;
        if (ai.isFull()) {
            board.showBoard();
            LOG.info("НИЧЬЯ! - игра закончена.");
            result = -1;
        }
        return result;
    }

    /**
     * @param name имя пользователя.
     * @param symbol символ, который использует пользователь.
     * @return -1, если пользователь прекратил игру, либо если есть победитель или ничья.
     */
    private int user(String name, String symbol) {
        boolean check;
        int move;
        int result = 0;
        System.out.println();
        LOG.info("Вводите номер в соответствии с номерами, расположенными справа.");
        LOG.info("Для выхода из игры введите '-1'.");
        board.showBoard();
        do {
            move = input.askMove("Ваш ход, " + name + ": ", board.getArea());
            if (move == -1) {
                return -1;
            }
            check = ai.isValidMove(move);
            if (!check) {
                LOG.info("Неверный ход, " + name + ", - выбранная Вами клетка уже занята!");
            }
        } while (!check);
        ai.move(move, symbol);
        if (checkWinner(name, symbol) == -1 || checkFull() == -1) {
            result = -1;
        }
        return result;
    }

    /**
     * @param name имя компьютера.
     * @param symbol символ, который использует компьютер.
     * @return -1, если есть победитель или ничья.
     * Метод sleep() - для того, чтобы игра 2-х компьютеров не была молниеносной.
     */
    private int computer(String name, String symbol) {
        int move;
        int result = 0;
        board.showBoard();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if ((move = ai.getEnemyMove(symbol, 2)) != -1) {
            ai.move(move, symbol);
        } else {
            move = ai.getRandom();
            ai.move(move, symbol);
        }
        LOG.info("Ход компьютера (" + name + ") = " + move);
        if (checkWinner(name, symbol) == -1 || checkFull() == -1) {
            result = -1;
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        new StartUI().init();
    }
}