package ru.job4j.h1srp;

/**
 * @author Vitaly Vasilyev, date: 05.04.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
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
    public void add(double first, double second) {
        this.result = first + second;
    }

    /**
     * Метод вычитания.
     * @param first - first
     * @param second - second
     */
    public void sub(double first, double second) {
        this.result = first - second;
    }

    /**
     * Метод деления.
     * @param first - first
     * @param second - second
     */
    public void div(double first, double second) {
        this.result = first / second;
    }

    /**
     * Метод умножения.
     * @param first - first
     * @param second - second
     */
    public void mult(double first, double second) {
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