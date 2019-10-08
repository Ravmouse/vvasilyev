package ru.job4j.h2stream.t6banktostream;

import java.util.Random;

/**
 * @author Vitaly Vasilyev, date: 07.10.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Account {
    /**
     * Количество денежных средств.
     */
    private double value;
    /**
     * Реквизиты.
     */
    private String requisites;

    /**
     * Конструктор.
     */
    public Account() {
        this.value = 0D;
        this.requisites = "";
    }

    /**
     * Конструктор.
     */
    public Account(double value) {
        this.value = value;
        this.requisites = generateReq();
    }

    /**
     * @return количество денежных средств.
     */
    public double getValue() {
        return this.value;
    }

    /**
     * @return реквизиты.
     */
    public String getRequisites() {
        return this.requisites;
    }

    /**
     * @param amount добавление денежных средств.
     */
    public void deposit(double amount) {
        this.value += amount;
    }

    /**
     * @param amount снятие денежных средств.
     */
    public void withdraw(double amount) {
        this.value -= amount;
    }

    /**
     * @param amount количество денежных средств.
     * @return true, если количество денег на счету больше или равно amount.
     */
    public boolean isEnoughMoney(double amount) {
        return (this.value >= amount);
    }

    /**
     * @return сгенерированные реквизиты.
     */
    private String generateReq() {
        return Integer.toString(Math.abs(new Random().nextInt() + (int) System.currentTimeMillis()));
    }

    /**
     * @return строковое представление.
     */
    @Override
    public String toString() {
        return String.format("Value: %f, requisites: %s", value, requisites);
    }
}