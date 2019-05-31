package ru.job4j.h3lsp;

import ru.job4j.h3lsp.food.*;
import ru.job4j.h3lsp.storage.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Vitaly Vasilyev, date: 25.05.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Test {
    /**
     * @param args аргс.
     */
    public static void main(String[] args) {
        List<Food> list = new ArrayList<>();
        list.add(new SpecialFood("Milk", 2019, 5, 20, 2019, 6, 9, 45, 0, false, false));
        list.add(new SpecialFood("Tea", 2019, 5, 1, 2019, 6, 6, 95, 0, false, false));
        list.add(new SpecialFood("Chicken", 2019, 5, 30, 2019, 6, 17, 190, 0, false, true));
        list.add(new SpecialFood("Cheese", 2019, 5, 13, 2019, 5, 22, 300, 0, false, false));
        list.add(new SpecialFood("Bread", 2019, 5, 18, 2019, 5, 23, 35, 0, false, false));
        list.add(new SpecialFood("Apple", 2019, 3, 10, 2019, 5, 25, 65, 0, false, false));
        list.add(new SpecialFood("Fish", 2019, 3, 10, 2019, 5, 25, 65, 0, true, true));
        list.add(new SpecialFood("Product", 2019, 4, 20, 2019, 5, 15, 165, 0, true, false));
        list.add(new SpecialFood("Rise", 2019, 5, 30, 2019, 8, 15, 78, 0, false, false));

        final Storage lowTemp = new LowTempWarehouse(new Warehouse());
        final Storage large = new LargeWarehouse(new Warehouse());
        final Storage shop = new Shop();
        final Storage trash = new Trash();
        final Storage rec = new Recycle(trash);
        lowTemp.setNextStorage(large);
        large.setNextStorage(shop);
        shop.setNextStorage(rec);
        rec.setNextStorage(trash);
        List<Storage> storageList = Arrays.asList(lowTemp, large, shop, rec, trash);

        ControlQuality c = new ControlQuality(storageList);
        c.distribute(list);
        c.show();
    }
}