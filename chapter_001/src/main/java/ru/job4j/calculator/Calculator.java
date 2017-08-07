package ru.job4j.calculator;
/**
 * Класс Calculator.
 * @author Vitaly Vasilyev (rav.energ@rambler.ru)
 * @version $Id$
 * @since 0.1
 */
public class Calculator {
    /**
     * Переменная для хранения результата вычисления.
     */
    private double result;

    /**
     * Метод сложения.
     * @param first - first
     * @param second - second
     */
    void add(double first, double second) {
        this.result = first + second;
    }

    /**
     * Метод вычитания.
     * @param first - first
     * @param second - second
     */
    void sub(double first, double second) {
        this.result = first - second;
    }

    /**
     * Метод деления.
     * @param first - first
     * @param second - second
     */
    void div(double first, double second) {
        this.result = first / second;
    }

    /**
     * Метод умножения.
     * @param first - first
     * @param second - second
     */
    void mult(double first, double second) {
        this.result = first * second;
    }

    /**
     * Метод получения результата вычисления.
     * @return double
     */
    public double getResult() {
        return this.result;
    }
}