package ru.job4j.h1srp.input;

import org.apache.log4j.Logger;
import ru.job4j.h1srp.MenuAction;
import ru.job4j.utils.Utils;

/**
 * @author Vitaly Vasilyev, date: 09.04.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class ValidateInput extends ConsoleInput {
    /**
     * Логгер.
     */
    private static final Logger LOG = Logger.getLogger(Utils.getNameOfTheClass());
    /**
     * Нужен, чтобы получить доступ до экз.класса Result (#count - 1), который хранит результат пред.вычисления.
     */
    private final MenuAction menu;

    /**
     * @param menu меню.
     */
    public ValidateInput(MenuAction menu) {
        this.menu = menu;
    }

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
                if (value < 1 || value > limit) {
                    LOG.warn("Вы должны ввести число от 1 до " + limit + "!");
                } else {
                    invalid = false;
                }
            } catch (NumberFormatException nbe) {
                LOG.warn("Вы должны вводить только числа!");
            }
        } while (invalid);
        return value;
    }

    /**
     * @param in строка с предложением.
     * @return вещественное значение.
     */
    public double ask(final String in) {
        boolean invalid = true;
        double value = -1;
        String str;
        do {
            try {
                str = super.askS(in);
                if ("z".equals(str)) {
                    value = menu.actions().get(menu.actions().size() - 1).getResult();
                } else {
                    value = Double.parseDouble(str);
                }
                invalid = false;
            } catch (NumberFormatException nbe) {
                LOG.warn("Вы должны вводить либо 'z', либо только целые или дробные числа!");
            }
        } while (invalid);
        return value;
    }
}