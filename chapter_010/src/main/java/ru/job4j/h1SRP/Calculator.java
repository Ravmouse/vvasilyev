package ru.job4j.h1srp;

/**
 * @author Vitaly Vasilyev, date: 05.04.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Calculator {
    /**
     * Метод сложения.
     * @param first - first
     * @param second - second
     * @return результат операции.
     */
    public double add(double first, double second) {
        return first + second;
    }

    /**
     * Метод вычитания.
     * @param first - first
     * @param second - second
     * @return результат операции.
     */
    public double sub(double first, double second) {
        return first - second;
    }

    /**
     * Метод деления.
     * @param first - first
     * @param second - second
     * @return результат операции.
     */
    public double div(double first, double second) {
        return first / second;
    }

    /**
     * Метод умножения.
     * @param first - first
     * @param second - second
     * @return результат операции.
     */
    public double mult(double first, double second) {
        return first * second;
    }
}