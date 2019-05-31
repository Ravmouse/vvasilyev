package ru.job4j.h3lsp.food;

/**
 * @author Vitaly Vasilyev, date: 31.05.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class SpecialFood extends ReproduceFood {
    /**
     * Должен ли храниться с низкой температурой.
     */
    private final boolean lowTempStore;

    /**
     * @param name         имя.
     * @param crYear       год производства.
     * @param crMonth      месяц производства.
     * @param crDay        день производства.
     * @param expYear      год истечения срока.
     * @param expMonth     месяц истечения срока.
     * @param expDay       день истечения срока.
     * @param price        цена.
     * @param discount     скидка.
     * @param canReproduce может ли перерабатываться.
     */
    public SpecialFood(String name, int crYear, int crMonth, int crDay, int expYear, int expMonth, int expDay,
                       double price, int discount, boolean canReproduce, boolean lowTempStore) {
        super(name, crYear, crMonth, crDay, expYear, expMonth, expDay, price, discount, canReproduce);
        this.lowTempStore = lowTempStore;
    }

    /**
     * @return true или false.
     */
    public boolean isLowTempStore() {
        return this.lowTempStore;
    }

    /**
     * @return строк.представление.
     */
    @Override
    public String toString() {
        return super.toString() + "; lowTempStore = " + this.lowTempStore;
    }
}