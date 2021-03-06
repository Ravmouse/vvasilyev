package ru.job4j.h7testtask.input;

import org.apache.log4j.Logger;
import ru.job4j.utils.Utils;

/**
 * @author Vitaly Vasilyev, date: 15.08.2019, e-mail: rav.energ@rambler.ru
 * @version 1.1
 */
public class ValidateInput extends ConsoleInput {
    /**
     * Логгер.
     */
    private static final Logger LOG = Logger.getLogger(Utils.getNameOfTheClass());

    /**
     * @param in строка с предложением.
     * @param limit граница.
     * @return целочисленное значение.
     */
    public int ask(String in, int limit) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = super.askI(in);
                if (value < 3 || value > limit) {
                    System.out.println();
                    LOG.warn("Размер поля должен быть равен 3, 4 или " + limit + "!");
                } else {
                    invalid = false;
                }
            } catch (NumberFormatException nbe) {
                System.out.println();
                LOG.warn("Вы должны вводить только числа!");
            }
        } while (invalid);
        return value;
    }

    /**
     * @param in строка с предложением.
     * @return вещественное значение.
     */
    public String askPlayer(String in, String one, String two) {
        boolean invalid = true;
        String result;
        do {
            result = super.askS(in);
            if (one.equals(result) || two.equals(result)) {
                invalid = false;
            } else {
                LOG.warn("Вы должны ввести либо символ '" + one + "', либо символ '" + two + "'!");
            }

        } while (invalid);
        return result;
    }

    /**
     * @param in строка с предложением.
     * @return строку с символом.
     */
    public String askSymbol(String in) {
        boolean invalid = true;
        String result;
        do {
            result = super.askS(in);
            if (result.length() == 1) {
                invalid = false;
            } else {
                LOG.warn("Вы должны ввести только 1 символ!");
            }
        } while (invalid);
        return result;
    }

    /**
     * @param in строка.
     * @param limit граница.
     * @return целое число.
     */
    public int askMove(String in, int limit) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = super.askI(in);
                if (value == -1) {
                    return value;
                }
                if (value < 0 || value >= limit) {
                    System.out.println();
                    LOG.warn("Вы должны вводить значения от 0 и до " + limit + "!");
                } else {
                    invalid = false;
                }
            } catch (NumberFormatException nbe) {
                System.out.println();
                LOG.warn("Вы должны вводить только числа!");
            }
        } while (invalid);
        return value;
    }
}