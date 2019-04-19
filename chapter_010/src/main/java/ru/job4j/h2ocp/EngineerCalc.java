package ru.job4j.h2ocp;

import ru.job4j.h1srp.Calculator;

/**
 * @author Vitaly Vasilyev, date: 16.04.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class EngineerCalc extends Calculator {
    /**
     * @param value значение для расчета косинуса.
     */
    public double cos(double value) {
        return Math.cos(value);
    }

    /**
     * @param value значение для расчета синуса.
     */
    public double sin(double value) {
        return Math.sin(value);
    }

    /**
     * @param value значение для расчета тангенса.
     */
    public double tg(double value) {
        return Math.tan(value);
    }

    /**
     * @param value значение для расчета котангенса.
     */
    public double ctg(double value) {
        return 1.0 / Math.tan(value);
    }
}