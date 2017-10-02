package ru.job4j.testtaskbankmap;
import java.util.Random;

/**
 * Class Account.
 */
public class Account {
    /**
     * The amount of Account's money.
     */
    private double value;
    /**
     * The number of account.
     */
    private int requisites;

    /**
     * The constructor.
     */
    public Account() {
        this.value = generateVal();
        this.requisites = generateReq();
    }

    /**
     * @param value to be assigned.
     * @param requisites to be assigned.
     */
    public Account(double value, int requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    /**
     * @return value of class Account object.
     */
    public double getValue() {
        return this.value;
    }

    /**
     * @return requisites of class Account object.
     */
    public int getRequisites() {
        return this.requisites;
    }

    /**
     * @param amount - a number that must be added to value of class Account object.
     */
    public void deposit(double amount) {
        this.value += amount;
    }

    /**
     * @param amount - a number that must be subtracted from value of class Account object.
     */
    public void withdraw(double amount) {
        this.value -= amount;
    }

    /**
     * @param amount of money.
     * @return true if the value of class Account object is greater or equal to amount and false otherwise.
     */
    public boolean checkValue(double amount) {
        return (this.value >= amount);
    }

    /**
     * @return random double value.
     */
    private double generateVal() {
        return new Random().nextDouble() * 100;
    }

    /**
     * @return random int value.
     */
    private int generateReq() {
        return Math.abs(new Random().nextInt() + (int) System.currentTimeMillis());
    }

    /**
     * @return string representation of the Account object.
     */
    public String toString() {
        return String.format("value: %f, requisites: %d", value, requisites);
    }
}