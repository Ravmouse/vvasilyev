package ru.job4j.h3lsp.storage;

import ru.job4j.h3lsp.food.ReproduceFood;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vitaly Vasilyev, date: 20.06.2019, e-mail: rav.energ@rambler.ru
 * @version 1.1
 */
public class Recycle {
    /**
     * Список перерабатываемых продуктов.
     */
    private final List<ReproduceFood> foods = new ArrayList<>();
    /**
     * Ссылка на следующее хранилище.
     */
    private Storage nextStorage;
    /**
     * Ссылка на хранилище-мусорку.
     * Там реализован метод isAppropriate().
     */
    private final Storage trash;

    /**
     * @param trash мусорка.
     */
    public Recycle(final Storage trash) {
        this.trash = trash;
    }

    /**
     * @param food перерабатываемый продукт.
     */
    public void add(ReproduceFood food) {
        this.foods.add(food);
    }

    /**
     * @param food продукт, который должен быть проверен: подходит ли он для данного хранилища или нет?
     * @return true или false.
     */
    public boolean isAppropriate(ReproduceFood food) {
        boolean result = false;
        if (trash.isAppropriate(food) && food.isCanReproduce()) {
            result = true;
        }
        return result;
    }

    /**
     * @param foods список продуктов.
     * Цикл - обычный, чтобы не возникало ConcurrentModificationException, т.к. здесь осуществляется удаление из
     * коллекции, чтобы продукты для переработки не оказались в Trash в дальнейшем.
     */
    public void init(List<ReproduceFood> foods) {
        for (int i = 0; i < foods.size(); i++) {
            if (isAppropriate(foods.get(i))) {
                add(foods.get(i));
                foods.remove(i--);
            }
        }
    }

    /**
     * @param nextStorage хранилище, ссылка на которое будет сохранена в объекте.
     */
    public void setNextStorage(Storage nextStorage) {
        this.nextStorage.setNextStorage(nextStorage);
    }

    /**
     * Показывает список продуктов.
     */
    public void show() {
        System.out.println("Recycle:");
        this.foods.forEach(System.out::println);
        System.out.println();
    }
}