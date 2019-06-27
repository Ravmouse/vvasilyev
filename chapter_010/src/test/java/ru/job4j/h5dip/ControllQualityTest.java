package ru.job4j.h5dip;

import org.junit.Test;
import ru.job4j.h3lsp.ControlQuality;
import ru.job4j.h3lsp.food.Food;
import ru.job4j.h3lsp.food.SpecialFood;
import ru.job4j.h3lsp.storage.Storage;
import java.util.Arrays;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тестовый класс.
 */
public class ControllQualityTest {
    /**
     * Если в хранилище присутствует просроченный продукт, то метод resort() удаляет этот продукт из хранилища.
     */
    @Test
    public void whenFoundExpiredFoodThenDeleteTheFood() {
        final Food milk = new SpecialFood("Milk", 2019, 5, 20, 2019, 6, 9, 45, 0, false, false);
        final Food tea = new SpecialFood("Tea", 2019, 5, 1, 2019, 9, 9, 95, 0, false, false);

        final Storage warehouse = new Warehouse();
        warehouse.add(milk);
        warehouse.add(tea);

        final ControlQuality control = new ControlQuality(Arrays.asList(warehouse));
        final ControllQuality cq = new ControllQuality(control);
        cq.resort();

        assertThat(control.getStorages().get(0).getFoods(), is(Arrays.asList(tea)));
    }
}