package ru.job4j.h7testtask;

import org.apache.log4j.Logger;
import ru.job4j.h7testtask.input.ValidateInput;
import ru.job4j.utils.Utils;
import java.io.IOException;

/**
 * @author Vitaly Vasilyev, date: 03.07.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
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
     * @throws IOException при появлении ошибок ввода-вывода.
     */
    public void launch() throws IOException {
        try (final ValidateInput input = new ValidateInput()) {
            LOG.info("** Крестики-нолики **");
            int amount = input.ask("Введите размер поля для игры: ", 5);
            board = new Board(amount);
            String symbol = input.ask("Выберите символ ['x' или 'o']: ", "x", "o");
            if ("x".equals(symbol)) {
                ai = new AI("x", "o", board);
            } else {
                ai = new AI("o", "x", board);
            }
            String player = input.ask("Кто начинает первым? ['с': computer или 'u': user]: ", "c", "u");
            if ("c".equals(player)) {
                do {
                    if (computer() == -1 || user(input, amount) == -1) {
                        break;
                    }
                } while (true);
            } else {
                do {
                    if (user(input, amount) == -1 || computer() == -1) {
                        break;
                    }
                } while (true);
            }
        }
    }

    /**
     * @return -1, если найден победитель, или 0, если победитель не найден.
     */
    private int checkWinner() {
        int result = 0;
        String win = ai.findWinner();
        if ("u".equals(win)) {
            board.showBoard();
            LOG.info("ПОЗДРАВЛЯЕМ!!! Вы выиграли!");
            result = -1;
        } else if ("c".equals(win)) {
            board.showBoard();
            LOG.info("...ДОСАДА. Компьютер выиграл!");
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
     * @param input ввод пользователя.
     * @param amount кол-во клеток, введеное пользователем.
     * @return ответ пользователя.
     * Ответ пользователя нужен в основном для проверки, ввел польз. -1 или нет для выхода из игры.
     */
    private int user(final ValidateInput input, int amount) {
        boolean check;
        int answer;
        System.out.println();
        LOG.info("Вводите номер в соответствии с номерами, расположенными справа.");
        LOG.info("Для выхода из игры введите '-1'.");
        board.showBoard();
        do {
            answer = input.askMove("Ваш ход: ", amount * amount);
            if (answer == -1) {
                return answer;
            }
            check = ai.isValidMove(answer);
            if (!check) {
                LOG.info("Неверный ход - выбранная Вами клетка уже занята!");
            }
        } while (!check);
        ai.userMove(answer);
        if (checkWinner() == -1 || checkFull() == -1) {
            answer = -1;
        }
        return answer;
    }

    /**
     * Ход компьютера.
     */
    private int computer() {
        int result = 0;
        int move = ai.aiMove();
        LOG.info("Ход компьютера = " + move);
        if (checkWinner() == -1 || checkFull() == -1) {
            result = -1;
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        new StartUI().launch();
    }
}