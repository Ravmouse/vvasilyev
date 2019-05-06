package ru.job4j.h3lsp.food;

import ru.job4j.utils.Utils;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Vitaly Vasilyev, date: 01.05.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public abstract class Food {
    /**
     * Имя продукта.
     */
    private final String name;
    /**
     * Дата производства.
     */
    private final LocalDate createDate;
    /**
     * Годен до.
     */
    private final LocalDate expiryDate;
    /**
     * Цена.
     */
    private double price;
    /**
     * Скидка.
     */
    private int discount;
    /**
     * Сколько в проц.соотношении прошло времени с даты производства.
     */
    private double rate;

    /**
     * @param name имя.
     * @param crYear год производства.
     * @param crMonth месяц производства.
     * @param crDay день производства.
     * @param expYear год истечения срока.
     * @param expMonth месяц истечения срока.
     * @param expDay день истечения срока.
     * @param price цена.
     * @param discount скидка.
     */
    public Food(final String name, int crYear, int crMonth, int crDay, int expYear, int expMonth, int expDay,
                double price, int discount) {
        this.name = name;
        this.createDate = LocalDate.of(crYear, crMonth, crDay);
        this.expiryDate = LocalDate.of(expYear, expMonth, expDay);
        this.price = price;
        this.discount = discount;
        evaluate();
    }

    /**
     * @return дату производства.
     */
    public LocalDate getCreateDate() {
        return createDate;
    }

    /**
     * @return дату истечения срока годности.
     */
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    /**
     * @param discount значение скидки.
     */
    public void setDiscount(int discount) {
        this.discount = discount;
    }

    /**
     * @return строк.представление.
     */
    @Override
    public String toString() {
        return String.format("[%s, %s, %s, %f, %d, %f]", name, createDate.toString(), expiryDate.toString(), price,
                discount, rate);
    }

    /**
     * @return хэш-код объекта.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, createDate, expiryDate, price, discount);
    }

    /**
     * @param obj объект для сравнения.
     * @return true, если объекты идентичны, и false, если - нет.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Food)) {
            return false;
        }
        Food food = (Food) obj;
        return name.equals(food.name) && createDate.equals(food.createDate) && expiryDate.equals(food.expiryDate)
                && price == food.price && discount == food.discount;
    }

    /**
     * Подсчет в процентах, сколько прошло времени с даты произодства.
     */
    public void evaluate() {
        if (!expiryDate.isAfter(createDate)) {
            throw new RuntimeException("Неверный срок годности!");
        }
        double use = Utils.daysBetween(createDate, expiryDate);
        double passed = Utils.daysBetween(createDate, LocalDate.now());
        this.rate = passed / use * 100;
    }

    /**
     * @return значение в процентах.
     */
    public double getRate() {
        return rate;
    }
}