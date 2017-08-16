package ru.job4j.profession;

/**
 * Class Money.
 */
public class Money {
    /**
     * The amount of money.
     */
    private int amount;
    /**
     * The type of the currency.
     */
    private String currencyType;

    /**
     * The constructor.
     * @param amount amount.
     * @param currencyType currencyType.
     */
    public Money(int amount, String currencyType) {
        this.amount = amount;
        this.currencyType = currencyType;
    }

    /**
     * The getter.
     * @return int.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * The getter.
     * @return String.
     */
    public String getCurrencyType() {
        return currencyType;
    }

    /**
     * The toString method.
     * @return String.
     */
    @Override
    public String toString() {
        return amount + " " + currencyType;
    }
}
