package ru.job4j.h1srp.action;

/**
 * @author Vitaly Vasilyev, date: 10.04.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Exit extends Action {
    /**
     * @param name имя.
     */
    public Exit(final String name) {
        super(name);
    }

    /**
     * @param one 1-й операнд.
     * @param two 2-й операнд.
     * @return результат.
     */
    @Override
    public Double operation(final Double one, final Double two) {
        return null;
    }
}