package ru.job4j.h3lsp.storage;

import ru.job4j.h3lsp.food.Food;
import java.util.List;

/**
 * @author Vitaly Vasilyev, date: 06.05.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class StorageDecorator implements Storage {
    /**
     * Класс, реализующий интерфейс Store.
     */
    private final Storage storage;

    /**
     * @param storage класс, реализующий интерфейс Store.
     */
    public StorageDecorator(Storage storage) {
        this.storage = storage;
    }

    /**
     * @param food продукт.
     */
    @Override
    public void add(Food food) {
        this.storage.add(food);
    }

    /**
     * @param food продукт, который нужно удалить.
     */
    @Override
    public void delete(Food food) {
        this.storage.delete(food);
    }

    /**
     * @param existed сущ. продукт.
     * @param newOne  новый продукт.
     */
    @Override
    public void update(Food existed, Food newOne) {
        this.storage.update(existed, newOne);
    }

    /**
     * Показывает список продуктов.
     */
    @Override
    public void show() {
        this.storage.show();
    }

    /**
     * @param food продукт, который должен быть проверен: подходит ли он для данного хранилища или нет?
     * @return результат вызова метода instance-переменной типа Storage.
     */
    @Override
    public boolean isAppropriate(Food food) {
        return this.storage.isAppropriate(food);
    }

    /**
     * @param foods список продуктов.
     */
    @Override
    public void init(List<Food> foods) {
        this.storage.init(foods);
    }

    /**
     * @param nextStorage хранилище, ссылка на которое будет сохранена в объекте.
     */
    @Override
    public void setNextStorage(Storage nextStorage) {
        this.storage.setNextStorage(nextStorage);
    }
}