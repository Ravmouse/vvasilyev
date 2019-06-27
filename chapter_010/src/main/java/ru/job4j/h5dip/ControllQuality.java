package ru.job4j.h5dip;

import ru.job4j.h3lsp.ControlQuality;
import ru.job4j.h3lsp.food.Food;
import ru.job4j.h3lsp.storage.Storage;
import java.util.List;

/**
 * @author Vitaly Vasilyev, date: 26.06.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class ControllQuality {
    /**
     * Объект, куда направляется список продуктов для распределения.
     */
    private final ControlQuality control;

    /**
     * Конструктор.
     * @param control объект, где уже произошло первое распределение продуктов.
     */
    public ControllQuality(final ControlQuality control) {
        this.control = control;
    }

    /**
     * У каждого хранилища по ссылке на ControlQuality проверяет список продуктов на предмет появления
     * таких продуктов, которые не должны быть в данном хранилище. Такие продукты удаляются.
     */
    public void resort() {
        final List<Storage> storages = this.control.getStorages();
        for (int i = 0; i < storages.size(); i++) {
            final List<Food> foods = storages.get(i).getFoods();
            for (int j = 0; j < foods.size(); j++) {
                if (!storages.get(i).isAppropriate(foods.get(j))) {
                    storages.get(i).delete(foods.get(j));
                }
            }
        }
    }
}