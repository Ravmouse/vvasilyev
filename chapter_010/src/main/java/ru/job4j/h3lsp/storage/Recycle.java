package ru.job4j.h3lsp.storage;

import ru.job4j.h3lsp.food.Food;
import ru.job4j.h3lsp.food.ReproduceFood;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vitaly Vasilyev, date: 29.05.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Recycle extends StorageDecorator {
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
    public Recycle(Storage storage) {
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
     * Не смотря на то, что параметр метода Food, этот класс и этот метод для продуктов типа ReproduceFood,
     * список которых (вместо Food) будет направляться в ControlQuality.
     */
    @Override
    public boolean isAppropriate(Food food) {
        boolean result = false;
        if (super.isAppropriate(food) && ((ReproduceFood) food).isCanReproduce()) {
            result = true;
        }
        return result;
    }

    /**
     * @param foods список продуктов.
     * Цикл - обычный, чтобы не возникало ConcurrentModificationException, т.к. здесь осуществляется удаление из
     * коллекции, чтобы продукты для переработки не оказались в Trash в дальнейшем.
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
        System.out.println("Recycle:");
        this.foods.forEach(System.out::println);
        System.out.println();
    }
}