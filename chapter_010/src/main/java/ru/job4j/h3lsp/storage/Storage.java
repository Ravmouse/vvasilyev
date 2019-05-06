package ru.job4j.h3lsp.storage;

import ru.job4j.h3lsp.food.Food;
import java.util.List;

/**
 * @author Vitaly Vasilyev, date: 01.05.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public interface Storage {
    /**
     * @param food продукт.
     */
    void add(Food food);
    /**
     * @param food продукт, который нужно удалить.
     */
    void delete(Food food);
    /**
     * @param existed сущ. продукт.
     * @param newOne новый продукт.
     */
    void update(Food existed, Food newOne);
    /**
     * Показывает список продуктов.
     */
    void show();
    /**
     * @param food продукт, который должен быть проверен: подходит ли он для данного хранилища или нет?
     * @return true, если продукт подходит, и false, если - нет.
     */
    boolean isAppropriate(Food food);
    /**
     * @param foods список продуктов.
     */
    void init(List<Food> foods);
    /**
     * @param nextStorage хранилище, ссылка на которое будет сохранена в объекте.
     */
    void setNextStorage(Storage nextStorage);
}