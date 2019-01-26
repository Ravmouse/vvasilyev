package ru.job4j.model;

/**
 * @author Vitaly Vasilyev, date: 18.01.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Seat {
    /**
     * Номер места.
     */
    private int number;
    /**
     * Цена.
     */
    private int price;
    /**
     * Состояние.
     */
    private int status;

    /**
     * @param number номер.
     * @param price цена.
     * @param status состояние.
     */
    public Seat(int number, int price, int status) {
        this.number = number;
        this.price = price;
        this.status = status;
    }

    /**
     * @return номер.
     */
    public int getNumber() {
        return number;
    }

    /**
     * @return цену.
     */
    public int getPrice() {
        return price;
    }

    /**
     * @return состояние.
     */
    public int getStatus() {
        return status;
    }

    /**
     * @return строковое представление.
     */
    @Override
    public String toString() {
        return String.format("number = %s, price = %s", number, price);
    }
}