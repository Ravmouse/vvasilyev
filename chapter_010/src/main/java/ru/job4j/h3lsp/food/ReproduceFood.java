package ru.job4j.h3lsp.food;

/**
 * @author Vitaly Vasilyev, date: 29.05.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class ReproduceFood extends Food {
    /**
     * Можно ли перерабатывать после истечения срока годности.
     */
    private final boolean canReproduce;

    /**
     * @param name     имя.
     * @param crYear   год производства.
     * @param crMonth  месяц производства.
     * @param crDay    день производства.
     * @param expYear  год истечения срока.
     * @param expMonth месяц истечения срока.
     * @param expDay   день истечения срока.
     * @param price    цена.
     * @param discount скидка.
     * @param canReproduce может ли перерабатываться.
     */
    public ReproduceFood(String name, int crYear, int crMonth, int crDay, int expYear, int expMonth, int expDay,
                         double price, int discount, boolean canReproduce) {
        super(name, crYear, crMonth, crDay, expYear, expMonth, expDay, price, discount);
        this.canReproduce = canReproduce;
    }

    /**
     * @return true или false.
     */
    public boolean isCanReproduce() {
        return this.canReproduce;
    }

    /**
     * @return строк.представление.
     */
    @Override
    public String toString() {
        return super.toString() + "; canReproduce = " + this.canReproduce;
    }
}