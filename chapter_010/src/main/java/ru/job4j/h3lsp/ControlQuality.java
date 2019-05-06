package ru.job4j.h3lsp;

import ru.job4j.h3lsp.food.Food;
import ru.job4j.h3lsp.storage.Storage;
import java.util.List;

/**
 * @author Vitaly Vasilyev, date: 01.05.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class ControlQuality {
    /**
     * Список хранилищ.
     */
    private final List<Storage> list;

    /**
     * Конструктор.
     */
    public ControlQuality(final List<Storage> list) {
        this.list = list;
    }

    /**
     * @param foods список с продуктами.
     */
    public void distribute(List<Food> foods) {
        list.get(0).init(foods);
    }

    /**
     * Выводит на печать каждое хранилище с продуктами.
     */
    public void show() {
        this.list.forEach(Storage::show);
    }
}