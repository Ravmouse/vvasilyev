package ru.job4j.h5dip;

import ru.job4j.h3lsp.food.Food;
import ru.job4j.h3lsp.storage.Storage;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vitaly Vasilyev, date: 26.06.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Warehouse implements Storage {
    /**
     * Список продуктов.
     */
    private final List<Food> foods = new ArrayList<>();
    /**
     * Нижний уровень по сроку годности.
     */
    private static final int LOW_RATE = 25;
    /**
     * Другое хранилище.
     */
    private Storage nextStorage;

    /**
     * Конструктор.
     */
    public Warehouse() {
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
     * @return список продуктов из хранилища.
     */
    @Override
    public List<Food> getFoods() {
        return this.foods;
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
        System.out.println("Warehouse:");
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
        if (food.getRate() < LOW_RATE) {
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