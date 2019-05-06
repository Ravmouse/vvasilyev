package ru.job4j.h3lsp.storage;

import ru.job4j.h3lsp.food.Food;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vitaly Vasilyev, date: 01.05.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Shop implements Storage {
    /**
     * Список продуктов.
     */
    private final List<Food> foods = new ArrayList<>();
    /**
     * Нижний уровень по сроку годности.
     */
    private static final int LOW_RATE = 25;
    /**
     * Средний уровень по сроку годности.
     */
    private static final int MIDDLE_RATE = 75;
    /**
     * Высокий уровень по сроку годности.
     */
    private static final int HIGH_RATE = 100;
    /**
     * Скидка.
     */
    private static final int DISCOUNT = 50;
    /**
     * Другое хранилище.
     */
    private Storage nextStorage;

    /**
     * Конструктор.
     */
    public Shop() {
    }

    /**
     * @param food продукт.
     */
    @Override
    public void add(Food food) {
        this.foods.add(food);
    }

    /**
     * @param existed сущ. продукт.
     * @param newOne  новый продукт.
     */
    @Override
    public void update(Food existed, Food newOne) {
        int index = this.foods.indexOf(existed);
        if (index == -1) {
            throw new RuntimeException("Искомый продукт для замены не найден!");
        }
        this.foods.set(index, newOne);
    }

    /**
     * @param food продукт, который нужно удалить.
     */
    @Override
    public void delete(Food food) {
        if (!this.foods.contains(food)) {
            throw new RuntimeException("Удаление невозможно - продукт не найден!");
        }
        this.foods.remove(food);
    }

    /**
     * Показывает список продуктов.
     */
    @Override
    public void show() {
        System.out.println("Shop:");
        this.foods.forEach(System.out::println);
        System.out.println();
    }

    /**
     * @param food продукт, который должен быть проверен: подходит ли он для данного хранилища или нет?
     * @return true, если продукт подходит, и false, если - нет.
     */
    @Override
    public boolean isAppropriate(Food food) {
        boolean result = false;
        if (food.getRate() >= LOW_RATE && food.getRate() <= MIDDLE_RATE) {
            result = true;
        } else if (food.getRate() > MIDDLE_RATE && food.getRate() <= HIGH_RATE) {
            food.setDiscount(DISCOUNT);
            result = true;
        }
        return result;
    }

    /**
     * @param foods список продуктов для проверки по отношению к текущему хранилищу.
     */
    @Override
    public void init(List<Food> foods) {
        foods.forEach(food -> {
            if (isAppropriate(food)) {
                add(food);
            }
        });
        if (nextStorage != null) {
            nextStorage.init(foods);
        }
    }

    /**
     * @param nextStorage хранилище, ссылка на которое будет сохранена в объекте.
     */
    @Override
    public void setNextStorage(Storage nextStorage) {
        this.nextStorage = nextStorage;
    }
}