package ru.job4j.h1srp;

import ru.job4j.h1srp.action.Action;
import ru.job4j.h1srp.action.Add;
import ru.job4j.h1srp.action.Div;
import ru.job4j.h1srp.action.Exit;
import ru.job4j.h1srp.action.Mult;
import ru.job4j.h1srp.action.Result;
import ru.job4j.h1srp.action.Sub;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Vitaly Vasilyev, date: 07.04.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class MenuAction {
    /**
     * Карта.
     */
    private final Map<Integer, Action> actions = new LinkedHashMap<>();

    /**
     * Конструктор.
     */
    public MenuAction() {
        actions.put(1, new Add("1. Сложение"));
        actions.put(2, new Sub("2. Вычитание"));
        actions.put(3, new Div("3. Деление"));
        actions.put(4, new Mult("4. Умножение"));
        actions.put(5, new Result("5. Пред.результат: "));
        actions.put(6, new Exit("6. Выход"));
    }

    /**
     * @return ссылку на карту.
     */
    public Map<Integer, Action> actions() {
        return this.actions;
    }
}