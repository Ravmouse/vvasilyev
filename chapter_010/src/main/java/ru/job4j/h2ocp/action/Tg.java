package ru.job4j.h2ocp.action;

import ru.job4j.h1srp.action.Action;
import ru.job4j.h2ocp.EngineerCalc;

/**
 * @author Vitaly Vasilyev, date: 16.04.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Tg extends Action {
    /**
     * Инженерный калькулятор.
     */
    private final EngineerCalc calc;

    /**
     * @param name имя.
     * @param calc ссылка на калькулятор.
     */
    public Tg(String name, final EngineerCalc calc) {
        super(name);
        this.calc = calc;
    }

    /**
     * @param one 1-й операнд.
     * @param two 2-й операнд.
     * @return результат.
     */
    @Override
    public Double operation(Double one, Double two) {
        return calc.tg(one);
    }
}