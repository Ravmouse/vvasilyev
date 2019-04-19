package ru.job4j.h1srp.action;

/**
 * @author Vitaly Vasilyev, date: 08.04.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public abstract class Action {
    /**
     * Имя.
     */
    protected final String name;
    /**
     * Результат.
     */
    private double result;

    /**
     * @param name имя.
     */
    public Action(final String name) {
        this.name = name;
    }

    /**
     * Выводит имя на экран.
     */
    public void info() {
        System.out.println(name);
    }

    /**
     * @param one 1-й операнд.
     * @param two 2-й операнд.
     * @return результат.
     */
    public abstract Double operation(Double one, Double two);

    /**
     * @param result результат.
     */
    public void setResult(double result) {
        this.result = result;
    }

    /**
     * @return результат.
     */
    public double getResult() {
        return result;
    }

    /**
     * @return имя действия.
     */
    public String getName() {
        return name;
    }
}