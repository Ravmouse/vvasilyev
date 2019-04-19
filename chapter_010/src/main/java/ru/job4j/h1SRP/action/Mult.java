package ru.job4j.h1srp.action;

import ru.job4j.h1srp.Calculator;

/**
 * @author Vitaly Vasilyev, date: 08.04.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Mult extends Action {
    /**
     * Калькулятор.
     */
    private final Calculator calc;

    /**
     * @param name имя.
     * @param calc ссылка на калькулятор.
     */
    public Mult(final String name, final Calculator calc) {
        super(name);
        this.calc = calc;
    }

    /**
     * @param one 1-й операнд.
     * @param two 2-й операнд.
     * @return результат.
     */
    @Override
    public Double operation(final Double one, final Double two) {
        return calc.mult(one, two);
    }
}