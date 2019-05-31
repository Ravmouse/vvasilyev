package ru.job4j.h3lsp.storage;

import ru.job4j.h3lsp.food.Food;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vitaly Vasilyev, date: 06.05.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class LargeWarehouse extends StorageDecorator {
    /**
     * Список продуктов "повышенного" объема.
     */
    private final List<Food> foods = new ArrayList<>();
    /**
     * Другое хранилище.
     */
    private Storage nextStorage;

    /**
     * @param storage класс, реализующий интерфейс Store.
     */
    public LargeWarehouse(Storage storage) {
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
     * Показывает список продуктов.
     */
    @Override
    public void show() {
        System.out.println("LargeWarehouse:");
        this.foods.forEach(System.out::println);
        System.out.println();
    }

    /**
     * @param foods список продуктов.
     * Для каждого продукта проверка осуществляется по тому, как это определено в суперклассе. А в суперклассе
     * есть делегирование полю типа Storage. Но после проверки add() происходит по методу этого класса.
     */
    @Override
    public void init(List<Food> foods) {
        foods.forEach(food -> {
            if (isAppropriate(food)) {
                add(food);
            }
        });
        if (this.nextStorage != null) {
            this.nextStorage.init(foods);
        }
    }

    /**
     * @param nextStorage хранилище, ссылка на которое будет сохранена в объекте.
     * Ссылка на след.хранилище устанавливается в этом классе.
     */
    @Override
    public void setNextStorage(Storage nextStorage) {
        this.nextStorage = nextStorage;
    }
}