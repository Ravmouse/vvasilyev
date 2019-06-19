package ru.job4j.h4isp;

import org.apache.log4j.Logger;
import ru.job4j.h1srp.input.ConsoleInput;
import ru.job4j.utils.Utils;
import java.util.List;

/**
 * @author Vitaly Vasilyev, date: 14.06.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class InteractMenu {
    /**
     * Экз.класса, реализующий интерфейс Tree.
     */
    final Tree<MenuItem> structure = new MenuStructure<>();
    /**
     * Логгер.
     */
    private static final Logger LOG = Logger.getLogger(Utils.getNameOfTheClass());

    /**
     *
     */
    public void launch() {
        try (final MenuInput input = new MenuInput()) {
            loopMenu(StringsInput.UI_LIST, input);
        }
    }

    /**
     * @param menu список строк, которые будут отображаться пользователю.
     * @param input ввод пользователя.
     */
    private void loopMenu(List<String> menu, ConsoleInput input) {
        int ans;
        do {
            menu.forEach(LOG::info);
            ans = input.askI("Выберите пункт меню: ");
            if (ans == 1) {
                structure.add(new MenuItem(input.askS("Добавить задачу под #: ")));
            } else if (ans == 2) {
                MenuItem fItem = structure.findByNumber(input.askS("Получить задачу под #: "));
                LOG.info(fItem);
            } else if (ans == 3) {
                structure.showAll();
            }
        } while (ans != menu.size() - 1);
    }
}