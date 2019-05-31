package ru.job4j.h3lsp.storage;

import ru.job4j.h3lsp.food.Food;
import ru.job4j.h3lsp.food.SpecialFood;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vitaly Vasilyev, date: 31.05.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class LowTempWarehouse extends StorageDecorator {
    /**
     * Список продуктов.
     */
    private final List<Food> foods = new ArrayList<>();
    /**
     * Другое хранилище.
     */
    private Storage nextStorage;

    /**
     * @param storage класс, реализующий интерфейс Store.
     */
    public LowTempWarehouse(Storage storage) {
        super(storage);
    }

    /**
     * @param food продукт.
     */
    @Override
    public void add(Food food) {
        this.foods.add(food);
    }

    /**
     * @param food продукт, который должен быть проверен: подходит ли он для данного хранилища или нет?
     * @return true или false.
     */
    @Override
    public boolean isAppropriate(Food food) {
        boolean result = false;
        if (super.isAppropriate(food) && ((SpecialFood) food).isLowTempStore()) {
            result = true;
        }
        return result;
    }

    /**
     * @param foods список продуктов.
     */
    @Override
    public void init(List<Food> foods) {
        for (int i = 0; i < foods.size(); i++) {
            if (isAppropriate(foods.get(i))) {
                add(foods.get(i));
                foods.remove(i--);
            }
        }
        if (this.nextStorage != null) {
            this.nextStorage.init(foods);
        }
    }

    /**
     * @param nextStorage хранилище, ссылка на которое будет сохранена в объекте.
     */
    @Override
    public void setNextStorage(Storage nextStorage) {
        this.nextStorage = nextStorage;
    }

    /**
     * Показывает список продуктов.
     */
    @Override
    public void show() {
        System.out.println("LowTempWarehouse:");
        this.foods.forEach(System.out::println);
        System.out.println();
    }
}