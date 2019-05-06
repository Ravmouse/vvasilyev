package ru.job4j.h3lsp.food;

/**
 * @author Vitaly Vasilyev, date: 05.05.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Chicken extends Food {
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
    public Chicken(String name, int crYear, int crMonth, int crDay, int expYear, int expMonth, int expDay, double price, int discount) {
        super(name, crYear, crMonth, crDay, expYear, expMonth, expDay, price, discount);
    }
}