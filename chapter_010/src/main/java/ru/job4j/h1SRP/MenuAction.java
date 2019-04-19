package ru.job4j.h1srp;

import ru.job4j.h1srp.action.Action;
import ru.job4j.h1srp.action.Add;
import ru.job4j.h1srp.action.Div;
import ru.job4j.h1srp.action.Mult;
import ru.job4j.h1srp.action.Sub;
import ru.job4j.h2ocp.EngineerCalc;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vitaly Vasilyev, date: 07.04.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class MenuAction {
    /**
     * Карта.
     */
    protected final Map<Integer, Action> actions = new LinkedHashMap<>();
    /**
     * Список со строками. Если одно из этих значений будет совпадать, то операция является тригонометрической.
     */
    private static final List<String> NAMES = Arrays.asList("Косинус", "Синус", "Тангенс", "Котангенс");
    /**
     * Инженерный калькулятор.
     */
    protected final EngineerCalc calc = new EngineerCalc();

    /**
     * Конструктор.
     */
    public MenuAction() {
        actions.put(1, new Add(" 1. Сложение", calc));
        actions.put(2, new Sub(" 2. Вычитание", calc));
        actions.put(3, new Div(" 3. Деление", calc));
        actions.put(4, new Mult(" 4. Умножение", calc));
    }

    /**
     * @return ссылку на карту.
     */
    public Map<Integer, Action> actions() {
        return this.actions;
    }

    /**
     * @param number число, введенное пользователем.
     * @return true, если число соответствует триг.операции, и false, если - нет.
     */
    public boolean isTrigonom(int number) {
        boolean result = false;
        for (String name : NAMES) {
            if (actions.get(number).getName().contains(name)) {
                result = true;
                break;
            }
        }
        return result;
    }
}