package ru.job4j.h1srp.action;

import ru.job4j.h1srp.Calculator;

/**
 * @author Vitaly Vasilyev, date: 08.04.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Sub extends Action {
    /**
     * Калькулятор.
     */
    private final Calculator calc = new Calculator();

    /**
     * @param name имя.
     */
    public Sub(final String name) {
        super(name);
    }

    /**
     * @param one 1-й операнд.
     * @param two 2-й операнд.
     * @return результат.
     */
    @Override
    public Double operation(final Double one, final Double two) {
        calc.sub(one, two);
        return calc.getResult();
    }
}